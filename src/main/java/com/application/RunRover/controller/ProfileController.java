package com.application.RunRover.controller;

import com.application.RunRover.dto.request.ProfileRequest;
import com.application.RunRover.dto.response.ProfileResponse;
import com.application.RunRover.exception.PlayerNotFoundException;
import com.application.RunRover.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {


    @Autowired
    ProfileService profileService;

    @PostMapping("/add")
    public ResponseEntity<?> addPlayerProfile(@RequestBody ProfileRequest profileRequest,
                                           @RequestParam("player-id") int playerId) {
        try{
            return new ResponseEntity<ProfileResponse>(profileService.addPlayerProfile(profileRequest,playerId),
                    HttpStatus.CREATED);
        }catch (PlayerNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileRequest profileRequest, @RequestParam int playerId){
        try{
            return new ResponseEntity<String>(profileService.updateProfile(profileRequest, playerId), HttpStatus.OK);
        }
        catch(PlayerNotFoundException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
