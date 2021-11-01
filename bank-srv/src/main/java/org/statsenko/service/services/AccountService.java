package org.statsenko.service.services;

import dto.request.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.statsenko.entity.Account;
import org.statsenko.entity.AccountType;
import org.statsenko.entity.Bank;
import org.statsenko.repository.AccountRepository;
import org.statsenko.repository.AccountTypeRepository;
import org.statsenko.repository.BankRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AccountService {

    @Autowired
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final BankRepository bankRepository;

    public List<AccountDto> getAllAccounts(){
        List<AccountDto> accountDtoList = accountRepository.findAll()
                .stream().map(AccountDto::new).collect(Collectors.toList());
        return accountDtoList;
    }

    public AccountDto createAccount(AccountDto accountDto){
        Account account = new Account();
        AccountType type = accountTypeRepository.findById(accountDto.getAccountType()).orElse(null);
        account.setType(type);
        account.setNumberAccount("Random");
        Bank bank = bankRepository.findById(accountDto.getBankId()).orElse(null);
        account.setBankAccounts(bank);

        accountRepository.save(account);

        return accountDto;
    }

    public void deleteAccount(int id){
        accountRepository.deleteById(id);
    }
}
