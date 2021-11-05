package controllers;

import dto.request.BankDto;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/bank")
@Api("bank controller")
public interface BankController {

    @GetMapping
    ResponseEntity<List<BankDto>> getAllBank();

    @GetMapping("/{id}")
    ResponseEntity getBankById(@PathVariable int id);

    @PostMapping
    ResponseEntity createBank(@RequestBody BankDto bankDto);

    @PutMapping("/{id}")
    ResponseEntity editBank(@RequestBody BankDto bankDto, @PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity deleteBank(@PathVariable int id);
}
