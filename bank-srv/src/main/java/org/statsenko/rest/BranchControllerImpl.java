package org.statsenko.rest;

import dto.response.MessageResponse;
import org.statsenko.service.services.BranchService;
import controllers.BranchController;
import dto.request.BranchDto;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class BranchControllerImpl implements BranchController {


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
    public ResponseEntity<MessageResponse<BranchDto>> createBranch(BranchDto branchDto) {
        return ResponseEntity.ok(branchService.createBranch(branchDto));
    }

    @Override
    public ResponseEntity<MessageResponse<BranchDto>> editBranch(BranchDto branchDto, int id) {
        return ResponseEntity.ok(branchService.editBranch(branchDto, id));
    }

    @Override
    public ResponseEntity deleteBranch(int id) {
        return ResponseEntity.ok(branchService.deleteBranch(id));
    }

    @Override
    public ResponseEntity getBranchOnClient(int id) {
        return ResponseEntity.ok(branchService.getBranchOnClient(id));
    }

    @Override
    public ResponseEntity getAllBranchOfBank(int id) {
        return ResponseEntity.ok(branchService.getAllBranchOfBank(id));
    }
}
