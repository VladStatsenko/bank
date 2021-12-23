package org.statsenko.mapper;

import dto.request.BranchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.statsenko.entity.Branch;

import java.util.List;

@Mapper
public interface BranchMapper extends ViewMapper<Branch, BranchDto> {

    @Mapping(source = "branchId", target = "id")
    @Mapping(source = "main.bankName", target = "main")
    @Override
    BranchDto toDto(Branch entity);

    @Mapping(source = "branchId", target = "id")
    @Mapping(source = "main.bankName", target = "main")
    @Override
    List<BranchDto> toDtoList(List<Branch> entityList);

    @Mapping(source = "main", target = "main.bankId")
    @Override
    Branch toEntity(BranchDto dto);

    @Override
    List<Branch> toEntityList(List<BranchDto> dtoList);

    @Mapping(target = "branchId", ignore = true)
    @Mapping(source = "main", target = "main.bankId")
    Branch update(BranchDto source, @MappingTarget Branch target);
}
