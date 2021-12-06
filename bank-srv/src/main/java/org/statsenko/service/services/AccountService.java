package org.statsenko.service.services;

import dto.request.AccountDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.statsenko.entity.Account;
import org.statsenko.mapper.AccountMapper;
import org.statsenko.repository.AccountRepository;
import org.statsenko.service.aspect.Loggable;

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
    public List<AccountDto> getClientAccount(int id){
        List<AccountDto> accountDtoList = REST_MAPPER.toDtoList(accountRepository.findClientByAccount(id));
        return accountDtoList;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public AccountDto createAccount(AccountDto accountDto){
        Account account = REST_MAPPER.toEntity(accountDto);

        accountRepository.save(account);

        return accountDto;
    }

    @Loggable
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAccount(int id){
        accountRepository.deleteById(id);
    }
}
