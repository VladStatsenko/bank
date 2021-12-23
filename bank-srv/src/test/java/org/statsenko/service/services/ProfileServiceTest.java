package org.statsenko.service.services;

import dto.request.ProfileDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.statsenko.entity.Client;
import org.statsenko.entity.Profile;
import org.statsenko.mapper.ProfileMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProfileServiceTest {

    private static final ProfileMapper REST_MAPPER = Mappers.getMapper(ProfileMapper.class);

    Client client1 = new Client(1,"Vlad","Ivanov","Ivan", null,"33333",
            null,null,null,null, null);
    Client client2 = new Client(2,"alex","alex","Ivan", null,"333333",
            null,null,null,null,null);
    Profile profile1 = new Profile(1,client1,"qwerty","qwerty","vlad","ivanov",
            null,null,null);
    Profile profile2 = new Profile(2,client2,"qwerty123","qwerty123","Alex",
            "petrov",null,null,null);

    @Test
    void getProfileById() {
        ProfileDto profileDto = REST_MAPPER.toDto(profile1);

        assertEquals("qwerty", profileDto.getLogin());
        assertEquals("vlad",profileDto.getFirstName());
    }

    @Test
    void getAllProfile() {
        List<ProfileDto> profileDtoList = REST_MAPPER.toDtoList(List.of(profile1,profile2));

        assertEquals("qwerty", profileDtoList.get(0).getLogin());
        assertEquals("qwerty123", profileDtoList.get(1).getLogin());

    }

    @Test
    void createProfile() {
        ProfileDto profileDto = new ProfileDto("shasha","qwerty123","Alexander","Sidorov",1);
        Profile profile = REST_MAPPER.toEntity(profileDto);

        assertEquals("shasha",profile.getLogin());
        assertEquals("qwerty123",profile.getPassword());

    }

    @Test
    void editProfile() {
        ProfileDto profileDto = new ProfileDto("shasha","qwerty123","Alexander","Sidorov",1);
        profile1 = REST_MAPPER.toEntity(profileDto);

        assertEquals("shasha",profile1.getLogin());
        assertEquals("qwerty123",profile1.getPassword());

    }
}