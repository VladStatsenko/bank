package controllers;

import dto.request.ProfileDto;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/profile")
@Api("profile controller")
public interface ProfileController {

    @GetMapping
    ResponseEntity<List<ProfileDto>> getAllProfile();

    @GetMapping("/{id}")
    ResponseEntity getProfileById(@PathVariable int id);

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    ResponseEntity createProfile(@RequestPart("photo") MultipartFile photo,@ModelAttribute ProfileDto profileDto);

    @PutMapping("/{id}")
    ResponseEntity editProfile(@RequestBody ProfileDto profileDto, @PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity deleteProfile(@PathVariable int id);
}
