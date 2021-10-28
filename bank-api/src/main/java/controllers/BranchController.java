package controllers;

import dto.request.BranchRequest;
import dto.response.InfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface BranchController {


    @GetMapping(name = "branch")
    ResponseEntity<InfoDto> getAllBranch();

    @GetMapping(name = "/branch/{id}")
    ResponseEntity<InfoDto> getBranchById(@PathVariable int id);

    @PostMapping(name = "/branch")
    ResponseEntity<InfoDto> createBranch(@RequestBody BranchRequest branchRequest);

    @PutMapping(name = "/branch/{id}")
    ResponseEntity<InfoDto> editBranch(@RequestBody BranchRequest branchRequest, @PathVariable int id);

    @DeleteMapping(name = "/branch/{id}")
    ResponseEntity<InfoDto> deleteBranch(@PathVariable int id);
}
