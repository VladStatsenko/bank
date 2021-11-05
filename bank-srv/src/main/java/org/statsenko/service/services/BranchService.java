package org.statsenko.service.services;


import dto.request.BranchDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Branch;
import org.statsenko.mapper.BranchMapper;
import org.statsenko.repository.BranchRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BranchService{

    private static final BranchMapper REST_MAPPER = Mappers.getMapper(BranchMapper.class);

    @Autowired
    private final BranchRepository branchRepository;

    public List<BranchDto> allBranch(){
        List<BranchDto> branchDtoList = REST_MAPPER.toDtoList(branchRepository.findAll());
        return branchDtoList;
    }

    public BranchDto getBranchById(int id){
        BranchDto branchDto = REST_MAPPER.toDto(branchRepository.getById(id));
        return branchDto;
    }

    public BranchDto createBranch(BranchDto branchDto){
        Branch branch = REST_MAPPER.toEntity(branchDto);

        branchRepository.save(branch);
        return branchDto;
    }

    public void deleteBranch(int id){
        branchRepository.deleteById(id);
    }

    public BranchDto editBranch(BranchDto branchDto, int id){
        Branch branch = REST_MAPPER.toEntity(branchDto);
        branch.setBranchId(id);

        branchRepository.save(branch);
        return branchDto;

    }



}
