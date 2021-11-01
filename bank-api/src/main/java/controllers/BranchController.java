package controllers;

import dto.request.BranchDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("branch")
public interface BranchController {


    @GetMapping
    ResponseEntity<List<BranchDto>> getAllBranch();

    @GetMapping("/{id}")
    ResponseEntity getBranchById(@PathVariable int id);

    @PostMapping
    ResponseEntity createBranch(@RequestBody BranchDto branchDto);

    @PutMapping("/{id}")
    ResponseEntity editBranch(@RequestBody BranchDto branchDto, @PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity deleteBranch(@PathVariable int id);
}
