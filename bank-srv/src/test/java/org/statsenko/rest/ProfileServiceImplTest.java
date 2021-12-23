package org.statsenko.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.response.MessageResponse;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.statsenko.entity.Client;
import org.statsenko.entity.Profile;
import org.statsenko.mapper.ProfileMapper;
import org.statsenko.service.services.ProfileService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {ProfileControllerImpl.class, ProfileService.class})
@WebMvcTest
class ProfileServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    ProfileMapper REST_MAPPER = Mappers.getMapper(ProfileMapper.class);

    @MockBean
    ProfileService profileService;

    Client client1 = new Client(1,"Vlad","Ivanov","Ivan", null,"33333",
            null,null,null,null, null);
    Client client2 = new Client(2,"alex","alex","Ivan", null,"333333",
            null,null,null,null,null);
    Profile profile1 = new Profile(1,client1,"qwerty","qwerty","vlad","ivanov",
            null,null,null);
    Profile profile2 = new Profile(2,client2,"qwerty123","qwerty123","Alex",
            "petrov",null,null,null);

    List<Profile> profiles = new ArrayList<>(List.of(profile1,profile2));


    @Test
    void getAllProfile() throws Exception{
        Mockito.when(profileService.getAllProfile()).thenReturn(REST_MAPPER.toDtoList(profiles));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/profile")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].login", is("qwerty")));
    }

    @Test
    void getProfileById() throws Exception {
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(profile1);
        Mockito.when(profileService.getProfileById(1)).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/profile/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response.firstName", is("vlad")))
                .andExpect(jsonPath("$.response.login",is("qwerty")));
    }

    @Test
    void editProfile() throws Exception{
        Profile updateProfile = new Profile(1,null,"login","password","Alex","petrov",null,null,null);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse(updateProfile);
        Mockito.when(profileService.editProfile(REST_MAPPER.toDto(updateProfile),1)).thenReturn(messageResponse);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/profile/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updateProfile));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.response.firstName", is("Alex")))
                .andExpect(jsonPath("$.response.login",is("login")));
    }

    @Test
    void deleteProfile() throws Exception{
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setResponse("Profile deleted");
        Mockito.when(profileService.deleteProfile(1)).thenReturn(messageResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/profile/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}