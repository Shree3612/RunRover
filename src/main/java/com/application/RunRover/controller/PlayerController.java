package com.application.RunRover.controller;

import com.application.RunRover.dto.request.PlayerRequest;
import com.application.RunRover.dto.response.PlayerResponse;
import com.application.RunRover.exception.PlayerNotFoundException;
import com.application.RunRover.model.Player;
import com.application.RunRover.model.enums.Speciality;
import com.application.RunRover.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/player")
public class PlayerController {

    @Autowired
    PlayerService playerService;

    @PostMapping("/add")
    public ResponseEntity<PlayerResponse> addPlayer(@RequestBody PlayerRequest playerRequest) {
        PlayerResponse playerResponse =  playerService.addPlayer(playerRequest);
        return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<List<PlayerResponse>> getAllByAgeGreaterThan(@PathVariable("age") int age) {
        List<PlayerResponse> list =  playerService.getAllByAgeGreaterThan(age);
        return new ResponseEntity<List<PlayerResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/age/{age}/speciality/{speciality}")
    public ResponseEntity<List<PlayerResponse>> getAllByAgeGreaterThanAndSpeciality(@PathVariable("age") int age,
                                                                    @PathVariable("speciality") Speciality speciality) {
        List<PlayerResponse> list =  playerService.getAllByAgeGreaterThanAndSpeciality(age,speciality);
        return new ResponseEntity<List<PlayerResponse>>(list, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getPlayer(@RequestParam int playerId) {
        try {
            return new ResponseEntity<PlayerResponse>(playerService.getPlayer(playerId), HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/update/{playerId}")
    public ResponseEntity<PlayerResponse> updatePlayer(@RequestBody PlayerRequest playerRequest, @PathVariable int playerId){
        PlayerResponse playerResponse =  playerService.updatePlayer(playerRequest, playerId);
        return new ResponseEntity<PlayerResponse>(playerResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{playerId}")
    public ResponseEntity<?> deletePlayer(@PathVariable int playerId) {
        try {
            return new ResponseEntity<String>(playerService.deletePlayer(playerId), HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/highestRuns")
    public ResponseEntity<Player> getPlayerWithHighestRuns() {
        try {
            return new ResponseEntity(playerService.getPlayerWithHighestRunsScored(), HttpStatus.OK);
        } catch (PlayerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}