package org.statsenko.rest;

import controllers.BankController;
import dto.request.BankDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.statsenko.service.services.BankService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BankControllerImpl implements BankController {

    private final BankService bankService;

    @Override
    public ResponseEntity<List<BankDto>> getAllBank() {
        return ResponseEntity.ok(bankService.getAllBank());
    }

    @Override
    public ResponseEntity getBankById(int id) {
        return ResponseEntity.ok(bankService.getBankById(id));
    }

    @Override
    public ResponseEntity createBank(BankDto bankDto) {
        return ResponseEntity.ok(bankService.createBank(bankDto));
    }

    @Override
    public ResponseEntity editBank(BankDto bankDto, int id) {
        return ResponseEntity.ok(bankService.editBank(bankDto,id));
    }

    @Override
    public ResponseEntity deleteBank(int id) {
        return ResponseEntity.ok(bankService.deleteBank(id));
    }
}
