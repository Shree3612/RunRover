package com.application.RunRover.controller;


import com.application.RunRover.dto.request.MatchRequest;
import com.application.RunRover.exception.TeamNotFoundException;
import com.application.RunRover.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/match")
@RequiredArgsConstructor
public class MatchController {

    @Autowired
    MatchService matchService;

//    public MatchController(MatchService matchService) {
//        this.matchService = matchService;
//    }

    @PostMapping("/register")
    public ResponseEntity<?> registerMatch(@RequestBody MatchRequest matchRequest,
                                        @RequestParam("teamA-id") int teamAId,
                                        @RequestParam("teamB-id") int teamBId) {
        try{
            return new ResponseEntity<>(matchService.registerMatch(matchRequest,teamAId, teamBId),
                    HttpStatus.CREATED);
        }catch (TeamNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
