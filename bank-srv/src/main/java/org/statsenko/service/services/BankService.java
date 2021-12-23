package org.statsenko.service.services;

import dto.request.BankDto;
import dto.response.MessageResponse;
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
    public MessageResponse<BankDto> getBankById(int id){
        MessageResponse message = new MessageResponse();
        if (bankRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found id");
            message.setTechnicalDescription("Bank with your ID not found");
        }
        else {
            BankDto bank = REST_MAPPER.toDto(bankRepository.getById(id));
            message.setResponse(bank);
        }
        return message;
    }

    @Loggable
    public List<BankDto> getAllBank(){
        List<BankDto> bankDtoList = REST_MAPPER.toDtoList(bankRepository.findAll());
        return bankDtoList;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse<BankDto> createBank(BankDto bankDto){
        MessageResponse message = new MessageResponse();

        if (bankRepository.existsByBankName(bankDto.getBankName())){
            message.setExceptionName("Similar name");
            message.setTechnicalDescription("Bank with this name already exist");
        }
        else {
            Bank bank = REST_MAPPER.toEntity(bankDto);
            bankRepository.save(bank);
            message.setResponse(bankDto);
        }
        return message;
    }

    @Loggable
    @Transactional
    public BankDto editBank(BankDto bankDto, int id){
        Bank bank = REST_MAPPER.update(bankDto, bankRepository.getById(id));
        bankRepository.save(bank);
        return bankDto;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse deleteBank(int id){
        MessageResponse message = new MessageResponse();
        if (bankRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Bank with your ID not found");
        }
        else {
            bankRepository.deleteById(id);
            message.setResponse("Bank deleted");
        }
        return message;
    }
}
