package org.statsenko.service.rest;

import controllers.ProfileController;
import dto.request.ProfileDto;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.statsenko.service.services.ProfileService;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProfileServiceImpl implements ProfileController {

    private final ProfileService profileService;

    @Override
    public ResponseEntity<List<ProfileDto>> getAllProfile() {
        return ResponseEntity.ok(profileService.getAllProfile());
    }

    @Override
    public ResponseEntity getProfileById(int id) {
        return ResponseEntity.ok(profileService.getProfileById(id));
    }

    @SneakyThrows
    @Override
    public ResponseEntity createProfile(MultipartFile photo, ProfileDto profileDto){
        return ResponseEntity.ok(profileService.createProfile(photo,profileDto));
    }

    @Override
    public ResponseEntity editProfile(ProfileDto profileDto, int id) {
        return ResponseEntity.ok(profileService.editProfile(profileDto, id));
    }

    @Override
    public ResponseEntity deleteProfile(int id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
