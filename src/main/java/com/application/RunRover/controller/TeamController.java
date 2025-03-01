package com.application.RunRover.controller;

import com.application.RunRover.dto.request.TeamRequest;
import com.application.RunRover.dto.response.TeamResponse;
import com.application.RunRover.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/team")
public class TeamController {

    @Autowired
    TeamService teamService;

    //adding a new team
    @PostMapping("/add")
    public ResponseEntity<?> addTeam(@RequestBody TeamRequest teamRequest) {
        try{
            return new ResponseEntity<TeamResponse>(teamService.addTeam(teamRequest), HttpStatus.CREATED);
        }catch (DataIntegrityViolationException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //adding a player to a team
    @PostMapping("/add/{teamId}/player/{playerId}")
    public ResponseEntity<?> addPlayerToTeam(
            @PathVariable int teamId,
            @PathVariable int playerId) {
        try{
            return new ResponseEntity<String>(teamService.addPlayerToTeam(playerId, teamId), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    //updating the team of a player
    @PutMapping("/update/{teamId}/player/{playerId}")
    public ResponseEntity<String> updateTeamOfAPlayer(
            @PathVariable int teamId,
            @PathVariable int playerId) {
        try{
            return new ResponseEntity<String>(teamService.updateTeamOfAPlayer(playerId, teamId), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
