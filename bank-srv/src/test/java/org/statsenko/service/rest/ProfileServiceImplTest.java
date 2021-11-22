package org.statsenko.service.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.statsenko.entity.Client;
import org.statsenko.entity.Profile;
import org.statsenko.repository.ProfileRepository;
import org.statsenko.service.services.ProfileService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ContextConfiguration(classes = {ProfileServiceImpl.class, ProfileService.class})
@WebMvcTest
class ProfileServiceImplTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    ProfileRepository profileRepository;

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
        Mockito.when(profileRepository.findAll()).thenReturn(profiles);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/profile")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].login", is("qwerty")));
    }

    @Test
    void getProfileById() throws Exception {
        Mockito.when(profileRepository.getById(profile1.getProfileId())).thenReturn(profile1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/profile/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.firstName", is("vlad")))
                .andExpect(jsonPath("$.login",is("qwerty")));
    }

    @Test
    void createProfile() throws Exception{
        Profile profile3 = new Profile(3,null,"login","password","Alex","petrov",null,null,null);

        Mockito.when(profileRepository.save(profile3)).thenReturn(profile3);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(profile3));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.firstName", is("Alex")))
                .andExpect(jsonPath("$.login",is("login")));
    }

    @Test
    void editProfile() throws Exception{
        Profile updateProfile = new Profile(1,null,"login","password","Alex","petrov",null,null,null);

        Mockito.when(profileRepository.save(updateProfile)).thenReturn(updateProfile);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .put("/profile/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(updateProfile));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.firstName", is("Alex")))
                .andExpect(jsonPath("$.login",is("login")));
    }

    @Test
    void deleteProfile() throws Exception{
        Mockito.when(profileRepository.getById(profile1.getProfileId())).thenReturn(profile1);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/profile/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}