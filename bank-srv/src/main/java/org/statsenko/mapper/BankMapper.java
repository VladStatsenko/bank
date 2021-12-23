package org.statsenko.mapper;

import dto.request.BankDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.statsenko.entity.Bank;

import java.util.List;

@Mapper
public interface BankMapper extends ViewMapper<Bank, BankDto> {

    @Mapping(source = "bankId", target = "id")
    @Mapping(source = "bankName", target = "bankName")
    @Override
    BankDto toDto(Bank entity);

    @Mapping(source = "bankId", target = "id")
    @Mapping(source = "bankName", target = "bankName")
    @Override
    List<BankDto> toDtoList(List<Bank> entityList);

    @Mapping(source = "bankName", target = "bankName")
    @Override
    Bank toEntity(BankDto dto);

    @Override
    List<Bank> toEntityList(List<BankDto> dtoList);

    @Mappings(@Mapping(target = "bankId", ignore = true))
    Bank update(BankDto source, @MappingTarget Bank target);
}
