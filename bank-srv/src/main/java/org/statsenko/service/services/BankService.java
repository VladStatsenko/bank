package org.statsenko.service.services;

import dto.request.BankDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.statsenko.entity.Bank;
import org.statsenko.mapper.BankMapper;
import org.statsenko.repository.BankRepository;
import org.statsenko.service.aop.Loggable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BankService {

    private static final BankMapper REST_MAPPER = Mappers.getMapper(BankMapper.class);

    private final BankRepository bankRepository;

    @Loggable
    public BankDto getBankById(int id){
        BankDto bank = REST_MAPPER.toDto(bankRepository.getById(id));
        return bank;
    }

    @Loggable
    public List<BankDto> getAllBank(){
        List<BankDto> bankDtoList = REST_MAPPER.toDtoList(bankRepository.findAll());
        return bankDtoList;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public BankDto createBank(BankDto bankDto){
        Bank bank = REST_MAPPER.toEntity(bankDto);
        bankRepository.save(bank);
        return bankDto;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public BankDto editBank(BankDto bankDto, int id){
        Bank bank = REST_MAPPER.update(bankDto, bankRepository.getById(id));
        bankRepository.save(bank);
        return bankDto;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteBank(int id){
        bankRepository.deleteById(id);
    }
}
