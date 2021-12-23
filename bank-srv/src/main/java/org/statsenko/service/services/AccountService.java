package org.statsenko.service.services;

import dto.request.AccountDto;
import dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.statsenko.entity.Account;
import org.statsenko.mapper.AccountMapper;
import org.statsenko.repository.AccountRepository;
import org.statsenko.service.aop.Loggable;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private static final AccountMapper REST_MAPPER = Mappers.getMapper(AccountMapper.class);

    @Autowired
    private final AccountRepository accountRepository;

    @Loggable
    public List<AccountDto> getAllAccounts(){
        List<AccountDto> accountDtoList = REST_MAPPER.toDtoList(accountRepository.findAll());
        return accountDtoList;
    }

    @Loggable
    public MessageResponse<List<AccountDto>> getClientAccount(int id){
        MessageResponse message = new MessageResponse();
        if (accountRepository.findClientByAccount(id).isEmpty()){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Client with your ID not found");
        }
        else {
            List<AccountDto> accountDtoList = REST_MAPPER.toDtoList(accountRepository.findClientByAccount(id));
            message.setResponse(accountDtoList);
        }
        return message;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse<AccountDto> createAccount(AccountDto accountDto){
        MessageResponse message = new MessageResponse();
        if (accountRepository.existsByNumberAccount(accountDto.getNumberAccount())){
            message.setExceptionName("Similar number");
            message.setTechnicalDescription("Bank with this number account already exist");
        }
        else {
            Account account = REST_MAPPER.toEntity(accountDto);
            accountRepository.save(account);
            message.setResponse(accountDto);
        }
        return message;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public MessageResponse deleteAccount(int id){
        MessageResponse message = new MessageResponse();
        if (accountRepository.findById(id).orElse(null)==null){
            message.setExceptionName("Not found");
            message.setTechnicalDescription("Account with your ID not found");
        }
        else {
            accountRepository.deleteById(id);
            message.setResponse("Account deleted");
        }
        return message;
    }
}
