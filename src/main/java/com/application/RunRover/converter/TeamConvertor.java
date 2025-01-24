package com.application.RunRover.converter;

import com.application.RunRover.dto.request.TeamRequest;
import com.application.RunRover.dto.response.TeamResponse;
import com.application.RunRover.model.Team;

public class TeamConvertor {
    public static Team teamRequestToTeam(TeamRequest teamRequest) {
        Team team = new Team();
        team.setName(teamRequest.getName());
        team.setRanking(teamRequest.getRanking());
        team.setIccPoints(teamRequest.getIccPoints());
        team.setCoach(teamRequest.getCoach());
        return team;
    }

    public static TeamResponse teamToTeamResponse(Team team) {
        TeamResponse teamResponse =  new TeamResponse();
        teamResponse.setName(team.getName());
        teamResponse.setRanking(team.getRanking());
        teamResponse.setCoach(team.getCoach());
        return teamResponse;
    }
}
