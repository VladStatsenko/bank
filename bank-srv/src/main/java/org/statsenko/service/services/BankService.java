package org.statsenko.service.services;

import dto.request.BankDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Bank;
import org.statsenko.mapper.BankMapper;
import org.statsenko.repository.BankRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BankService {

    private static final BankMapper REST_MAPPER = Mappers.getMapper(BankMapper.class);

    private final BankRepository bankRepository;

    public BankDto getBankById(int id){
        BankDto bank = REST_MAPPER.toDto(bankRepository.getById(id));
        return bank;
    }

    public List<BankDto> getAllBank(){
        List<BankDto> bankDtoList = REST_MAPPER.toDtoList(bankRepository.findAll());
        return bankDtoList;
    }

    public BankDto createBank(BankDto bankDto){
        Bank bank = REST_MAPPER.toEntity(bankDto);
        bankRepository.save(bank);
        return bankDto;
    }

    public BankDto editBank(BankDto bankDto, int id){
        Bank bank = REST_MAPPER.toEntity(bankDto);
        bank.setBankId(id);
        bankRepository.save(bank);
        return bankDto;
    }

    public void deleteBank(int id){
        bankRepository.deleteById(id);
    }
}
