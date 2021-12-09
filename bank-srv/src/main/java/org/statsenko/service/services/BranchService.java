package org.statsenko.service.services;


import dto.request.BranchDto;
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
    public List<BranchDto> getAllBranchOfBank(int id){
        List<BranchDto> branchDtoList = REST_MAPPER.toDtoList(branchRepository.findBranchByBank(id));
        return branchDtoList;
    }

    @Loggable
    public List<BranchDto> getBranchOnClient(int id){
        List<BranchDto> branchDtoList = REST_MAPPER.toDtoList(branchRepository.findBranchByClient(id));
        return branchDtoList;
    }

    @Loggable
    public BranchDto getBranchById(int id){
        BranchDto branchDto = REST_MAPPER.toDto(branchRepository.getById(id));
        return branchDto;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public BranchDto createBranch(BranchDto branchDto){
        Branch branch = REST_MAPPER.toEntity(branchDto);

        branchRepository.save(branch);
        return branchDto;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBranch(int id){
        branchRepository.deleteById(id);
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public BranchDto editBranch(BranchDto branchDto, int id){
        Branch branch = REST_MAPPER.update(branchDto, branchRepository.getById(id));

        branchRepository.save(branch);
        return branchDto;

    }
}
