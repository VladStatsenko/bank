package org.statsenko.service.services;


import dto.request.BranchDto;
import dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.statsenko.entity.Branch;
import org.statsenko.mapper.BranchMapper;
import org.statsenko.repository.BranchRepository;
import org.statsenko.service.aop.Loggable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BranchService{

    private static final BranchMapper REST_MAPPER = Mappers.getMapper(BranchMapper.class);

    @Autowired
    private final BranchRepository branchRepository;

    @Loggable
    public List<BranchDto> allBranch(){
        List<BranchDto> branchDtoList = REST_MAPPER.toDtoList(branchRepository.findAll());
        return branchDtoList;
    }

    @Loggable
    public MessageResponse<List<BranchDto>> getAllBranchOfBank(int id){
        MessageResponse message = new MessageResponse();
        if (branchRepository.findBranchByBank(id).isEmpty()){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Bank with your ID not found");
        }
        else {
            List<BranchDto> branchDtoList = REST_MAPPER.toDtoList(branchRepository.findBranchByBank(id));
            message.setResponse(branchDtoList);
        }
        return message;
    }

    @Loggable
    public MessageResponse<List<BranchDto>> getBranchOnClient(int id){
        MessageResponse message = new MessageResponse();
        if (branchRepository.findBranchByClient(id).isEmpty()){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Client with your ID not found");
        }
        else {
            List<BranchDto> branchDtoList = REST_MAPPER.toDtoList(branchRepository.findBranchByClient(id));
            message.setResponse(branchDtoList);
        }
        return message;
    }

    @Loggable
    public MessageResponse<BranchDto> getBranchById(int id){
        MessageResponse message = new MessageResponse();
        if (branchRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Branch with your ID not found");
        }
        else {
            BranchDto branchDto = REST_MAPPER.toDto(branchRepository.getById(id));
            message.setResponse(branchDto);
        }
        return message;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse<BranchDto> createBranch(BranchDto branchDto){
        MessageResponse message = new MessageResponse();
        if (branchRepository.existsByBranchName(branchDto.getBranchName())){
            message.setExceptionName("Similar name");
            message.setTechnicalDescription("Bank with this name already exist");
        }
        else {
            Branch branch = REST_MAPPER.toEntity(branchDto);
            branchRepository.save(branch);
            message.setResponse(branchDto);
        }
        return message;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse deleteBranch(int id){
        MessageResponse message = new MessageResponse();
        if (branchRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Branch with your ID not found");
        }
        else {
            branchRepository.deleteById(id);
            message.setResponse("Branch deleted");
        }
        return message;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse<BranchDto> editBranch(BranchDto branchDto, int id){
        MessageResponse message = new MessageResponse();
        if (branchRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Branch with your ID not found");
        }
        else {
            Branch branch = REST_MAPPER.update(branchDto, branchRepository.getById(id));

            branchRepository.save(branch);
        }
        return message;
    }
}
