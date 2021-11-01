package org.statsenko.service.services;


import dto.request.BranchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.statsenko.entity.Bank;
import org.statsenko.entity.Branch;
import org.statsenko.repository.BankRepository;
import org.statsenko.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BranchService{

    @Autowired
    private final BranchRepository branchRepository;
    private final BankRepository bankRepository;


    public List<BranchDto> allBranch(){
        List<BranchDto> branchDtoList = branchRepository.findAll()
                .stream().map(BranchDto::new).collect(Collectors.toList());
        return branchDtoList;
    }

    public BranchDto getBranchById(int id){
        BranchDto branchDto = branchRepository.findById(id).map(BranchDto::new).get();
        return branchDto;
    }

    public BranchDto createBranch(BranchDto branchDto){
        Branch branch = new Branch();
        branch.setBranchName(branchDto.getName());
        branch.setLocation(branchDto.getLocation());
        Bank bank = bankRepository.findById(branchDto.getBankId()).orElse(null);
        branch.setMain(bank);

        branchRepository.save(branch);
        return branchDto;
    }

    public void deleteBranch(int id){
        branchRepository.deleteById(id);
    }

    public BranchDto editBranch(BranchDto branchDto, int id){
        Branch branch = branchRepository.findById(id).orElse(null);

        branch.setBranchName(branchDto.getName());
        branch.setLocation(branchDto.getLocation());

        branchRepository.save(branch);
        return branchDto;

    }



}
