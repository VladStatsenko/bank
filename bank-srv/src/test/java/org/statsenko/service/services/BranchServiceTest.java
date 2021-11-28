package org.statsenko.service.services;

import dto.request.BranchDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.statsenko.entity.Bank;
import org.statsenko.entity.Branch;
import org.statsenko.entity.Client;
import org.statsenko.mapper.BranchMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BranchServiceTest {

    private static final BranchMapper REST_MAPPER = Mappers.getMapper(BranchMapper.class);

    Bank bank1 = new Bank(1,"SBER",null,null, null,null);
    Bank bank2 = new Bank(2,"ALFA",null,null, null,null);
    Branch branch1 = new Branch(1,"SBER FILIAL","MOSCOW",null,null,
            bank1,null);
    Branch branch2 = new Branch(2,"ALFA FILIAL","SPB",null,null,bank2,
            null);

    Client client = new Client(1,"Vlad","Ivanov","Ivan", null,"33333",
            null,null,null,null, List.of(branch1,branch2));

    @Test
    void allBranch() {
        List<BranchDto> branchDtoList = REST_MAPPER.toDtoList(List.of(branch1,branch2));

        assertEquals("SBER FILIAL", branchDtoList.get(0).getBranchName());
        assertEquals("SPB",branchDtoList.get(1).getLocation());
    }

    @Test
    void getAllBranchOfBank() {
        List<BranchDto> branchDtoList = REST_MAPPER.toDtoList(List.of(branch1));

        assertEquals("SBER", branchDtoList.get(0).getMain());
    }

    @Test
    void getBranchOnClient() {

        assertEquals("SBER FILIAL", client.getBranch().get(0).getBranchName());
        assertEquals("ALFA FILIAL", client.getBranch().get(1).getBranchName());
    }

    @Test
    void getBranchById() {
        BranchDto branchDto = REST_MAPPER.toDto(branch1);

        assertEquals("SBER FILIAL",branchDto.getBranchName());
    }

    @Test
    void createBranch() {
        BranchDto branchDto = new BranchDto("VTB 1","SMOLENSK","1");
        Branch branch = REST_MAPPER.toEntity(branchDto);

        assertEquals(1,branch.getMain().getBankId());
    }

    @Test
    void editBranch() {
        BranchDto branchDto = new BranchDto("SBERBANK FILIAL","SMOLENSK","1");
        branch1 = REST_MAPPER.toEntity(branchDto);

        assertEquals(1,branch1.getMain().getBankId());
        assertEquals("SBERBANK FILIAL",branch1.getBranchName());
    }
}