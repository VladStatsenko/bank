package org.statsenko.service.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.statsenko.service.services.BranchService;
import controllers.BranchController;
import dto.request.BranchDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class BranchServiceImpl implements BranchController {


    private final BranchService branchService;

    @Override
    public ResponseEntity<List<BranchDto>> getAllBranch() {
        return ResponseEntity.ok(branchService.allBranch());
    }

    @Override
    public ResponseEntity getBranchById(int id) {
        return ResponseEntity.ok(branchService.getBranchById(id));
    }

    @Override
    public ResponseEntity<BranchDto> createBranch(BranchDto branchDto) {
        return ResponseEntity.ok(branchService.createBranch(branchDto));
    }

    @Override
    public ResponseEntity<BranchDto> editBranch(BranchDto branchDto, int id) {
        return ResponseEntity.ok(branchService.editBranch(branchDto, id));
    }

    @Override
    public ResponseEntity deleteBranch(int id) {
        branchService.deleteBranch(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
