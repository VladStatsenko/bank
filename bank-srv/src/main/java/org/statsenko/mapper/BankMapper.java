package org.statsenko.mapper;

import dto.request.BankDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.statsenko.entity.Bank;

import java.util.List;

@Mapper
public interface BankMapper extends ViewMapper<Bank, BankDto> {

    @Mapping(source = "bankName", target = "bankName")
    @Override
    BankDto toDto(Bank entity);
    @Mapping(source = "bankName", target = "bankName")
    @Override
    List<BankDto> toDtoList(List<Bank> entityList);
    @Mapping(source = "bankName", target = "bankName")
    @Override
    Bank toEntity(BankDto dto);

    @Override
    List<Bank> toEntityList(List<BankDto> dtoList);
}
