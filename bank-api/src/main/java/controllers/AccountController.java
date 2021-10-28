package controllers;

import dto.request.AccountRequest;
import dto.request.BranchRequest;
import dto.response.InfoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public interface AccountController {


    @GetMapping(name = "/account")
    ResponseEntity<InfoDto> getAllAccounts();

    @GetMapping(name = "/account/{id}")
    ResponseEntity<InfoDto> getAccountById(@PathVariable int id);

    @PostMapping(name = "/account")
    ResponseEntity<InfoDto> createAccount(@RequestBody AccountRequest accountRequest);

    @PutMapping(name = "/account/{id}")
    ResponseEntity<InfoDto> editAccount(@RequestBody AccountRequest accountRequest, @PathVariable int id);

    @DeleteMapping(name = "/account/{id}")
    ResponseEntity<InfoDto> deleteAccount(@PathVariable int id);
}
