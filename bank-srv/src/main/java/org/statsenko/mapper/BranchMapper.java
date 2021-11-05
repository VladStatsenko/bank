package org.statsenko.mapper;

import dto.request.BranchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.statsenko.entity.Branch;

import java.util.List;

@Mapper
public interface BranchMapper extends ViewMapper<Branch, BranchDto> {

    @Mapping(source = "main.bankId", target = "bankId")
    @Override
    BranchDto toDto(Branch entity);

    @Mapping(source = "main.bankId", target = "bankId")
    @Override
    List<BranchDto> toDtoList(List<Branch> entityList);
    @Mapping(source = "bankId", target = "main.bankId")
    @Override
    Branch toEntity(BranchDto dto);

    @Override
    List<Branch> toEntityList(List<BranchDto> dtoList);
}
