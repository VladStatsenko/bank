package controllers;

import dto.request.AccountDto;
import dto.response.InfoDto;
import dto.response.MessageResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/account")
@Api("account controller")
public interface AccountController {

    @GetMapping
    ResponseEntity<List<AccountDto>> getAllAccounts();

    @GetMapping("client/{id}")
    ResponseEntity getClientAccount(@PathVariable int id);

    @PostMapping
    ResponseEntity<MessageResponse<AccountDto>> createAccount(@RequestBody AccountDto accountDto);

    @DeleteMapping("/{id}")
    ResponseEntity<InfoDto> deleteAccount(@PathVariable int id);
}
