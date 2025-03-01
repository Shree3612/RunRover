package com.application.RunRover.service;


import com.application.RunRover.converter.MatchConvertor;
import com.application.RunRover.dto.request.MatchRequest;
import com.application.RunRover.dto.response.MatchResponse;
import com.application.RunRover.exception.TeamNotFoundException;
import com.application.RunRover.model.CricketMatch;
import com.application.RunRover.model.Team;
import com.application.RunRover.repository.CricketMatchRepository;
import com.application.RunRover.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    CricketMatchRepository matchRepository;

    public MatchResponse registerMatch(MatchRequest matchRequest, int teamAId, int teamBId) {
        Optional<Team> teamAOptional = teamRepository.findById(teamAId);
        Optional<Team> teamBOptional = teamRepository.findById(teamBId);
        if(teamAOptional.isEmpty() || teamBOptional.isEmpty()) {
            throw new TeamNotFoundException("One or more team id is not valid");
        }

        Team teamA = teamAOptional.get();
        Team teamB = teamBOptional.get();

        CricketMatch cricketMatch = MatchConvertor.matchRequestToMatch(matchRequest);

        // Ensure the teams list is initialized
        if (cricketMatch.getTeams() == null) {
            cricketMatch.setTeams(new ArrayList<>());
        }

        cricketMatch.getTeams().add(teamA);
        cricketMatch.getTeams().add(teamB);

        teamA.getMatches().add(cricketMatch);
        teamB.getMatches().add(cricketMatch);

        CricketMatch savedMatch = matchRepository.save(cricketMatch);
        teamRepository.save(teamA);
        teamRepository.save(teamB);

        return MatchConvertor.matchToMatchResponse(savedMatch);
    }
}
