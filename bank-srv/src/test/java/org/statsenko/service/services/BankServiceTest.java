package org.statsenko.service.services;

import dto.request.BankDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.statsenko.entity.Bank;
import org.statsenko.mapper.BankMapper;
import org.statsenko.repository.BankRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {

    private static final BankMapper REST_MAPPER = Mappers.getMapper(BankMapper.class);

    Bank bank1 = new Bank(1,"SBER",null,null,null,null);
    Bank bank2 = new Bank(2,"ALFA",null,null,null,null);


    @Test
    void getBankById() {
        BankDto bankDto = REST_MAPPER.toDto(bank1);
        assertEquals("SBER",bankDto.getBankName());
    }

    @Test
    void getAllBank() {
        List<BankDto> bankDtoList = REST_MAPPER.toDtoList(List.of(bank1,bank2));
        assertEquals("SBER",bankDtoList.get(0).getBankName());
        assertEquals("ALFA",bankDtoList.get(1).getBankName());

    }

    @Test
    void createBank() {
        BankDto bankDto = new BankDto("Uralsib");
        Bank newBank = REST_MAPPER.toEntity(bankDto);
        assertEquals("Uralsib",newBank.getBankName());
    }

    @Test
    void editBank() {
        BankDto bankDto = new BankDto("SBERBANK");
        bank1 = REST_MAPPER.toEntity(bankDto);
        assertEquals("SBERBANK", bank1.getBankName());
    }
}