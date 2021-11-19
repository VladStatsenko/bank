package org.statsenko.service.services;

import dto.request.AccountDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Account;
import org.statsenko.mapper.AccountMapper;
import org.statsenko.repository.AccountRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AccountService {

    private static final AccountMapper REST_MAPPER = Mappers.getMapper(AccountMapper.class);

    @Autowired
    private final AccountRepository accountRepository;

    public List<AccountDto> getAllAccounts(){
        List<AccountDto> accountDtoList = REST_MAPPER.toDtoList(accountRepository.findAll());
        return accountDtoList;
    }

    public List<AccountDto> getClientAccount(int id){
        List<AccountDto> accountDtoList = REST_MAPPER.toDtoList(accountRepository.getClientAccount(id));
        return accountDtoList;
    }

    public AccountDto createAccount(AccountDto accountDto){
        Account account = REST_MAPPER.toEntity(accountDto);

        accountRepository.save(account);

        return accountDto;
    }

    public void deleteAccount(int id){
        accountRepository.deleteById(id);
    }
}
