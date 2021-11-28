package org.statsenko.service.rest;

import controllers.AccountController;
import dto.request.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.statsenko.service.services.AccountService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AccountControllerImpl implements AccountController {

    private final AccountService accountService;

    @Override
    public ResponseEntity getClientAccount(int id) {
        return ResponseEntity.ok(accountService.getClientAccount(id));
    }

    @Override
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }


    @Override
    public ResponseEntity<AccountDto> createAccount(AccountDto accountDto) {
        return ResponseEntity.ok(accountService.createAccount(accountDto));
    }


    @Override
    public ResponseEntity deleteAccount(int id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
