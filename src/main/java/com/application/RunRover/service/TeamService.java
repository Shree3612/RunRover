package com.application.RunRover.service;

import com.application.RunRover.converter.TeamConvertor;
import com.application.RunRover.dto.request.TeamRequest;
import com.application.RunRover.dto.response.TeamResponse;
import com.application.RunRover.model.Player;
import com.application.RunRover.model.Team;
import com.application.RunRover.repository.PlayerRepository;
import com.application.RunRover.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerRepository playerRepository;

    public TeamResponse addTeam(TeamRequest teamRequest) {
        try {
            Team team = TeamConvertor.teamRequestToTeam(teamRequest);
            Team savedTeam = teamRepository.save(team);
            return TeamConvertor.teamToTeamResponse(savedTeam);
        } catch (DataIntegrityViolationException  e) {
            throw new DataIntegrityViolationException("The rank is not unique. Please choose a different rank.");
        }
    }

    public String addPlayerToTeam(int playerId, int teamId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        player.setTeam(team);

        Player savedPlayer =  playerRepository.save(player);
        return "Player with ID :"+playerId+" added successfully to the team with ID: "+teamId;
    }

    public String updateTeamOfAPlayer(int playerId, int teamId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new RuntimeException("Player not found"));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        player.setTeam(team);

        Player savedPlayer =  playerRepository.save(player);
        return "Player with ID :"+playerId+" added successfully to the team with ID: "+teamId;
    }
}
