package com.application.RunRover.converter;

import com.application.RunRover.dto.request.MatchRequest;
import com.application.RunRover.dto.response.MatchResponse;
import com.application.RunRover.dto.response.TeamResponse;
import com.application.RunRover.model.CricketMatch;
import com.application.RunRover.model.Team;

import java.util.ArrayList;
import java.util.List;


public class MatchConvertor {
    public static CricketMatch matchRequestToMatch(MatchRequest matchRequest) {
        CricketMatch match  = new CricketMatch();
        match.setTitle(matchRequest.getTitle());
        match.setVenue(matchRequest.getVenue());
        match.setNoOfOvers(matchRequest.getNoOfOvers());
        match.setTeams(match.getTeams());
        return match;
    }

    public static MatchResponse matchToMatchResponse(CricketMatch match) {

        Team teamA = match.getTeams().get(0);
        Team teamB = match.getTeams().get(1);
        List<TeamResponse> teamResponses = new ArrayList<>();
        teamResponses.add(TeamConvertor.teamToTeamResponse(teamA));
        teamResponses.add(TeamConvertor.teamToTeamResponse(teamB));

        MatchResponse response = new MatchResponse();
        response.setTitle(match.getTitle());
        response.setNoOfOvers(match.getNoOfOvers());
        response.setVenue(match.getVenue());
        response.setCreatedAt(match.getCreatedAt());
        response.setTeams(teamResponses);
        return response;

    }
}
