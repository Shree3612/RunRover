package com.application.RunRover.converter;

import com.application.RunRover.dto.request.ProfileRequest;
import com.application.RunRover.dto.response.PlayerResponse;
import com.application.RunRover.dto.response.ProfileResponse;
import com.application.RunRover.model.PlayerProfile;

public class ProfileConvertor {
    public static PlayerProfile profileRequstToProfile(ProfileRequest profileRequest) {
        PlayerProfile profile = new PlayerProfile();
        profile.setRunsScored(profileRequest.getRunsScored());
        profile.setWicketsTaken(profileRequest.getWicketsTaken());
        profile.setBattingAvg(profileRequest.getBattingAvg());
        profile.setBowlingAvg(profileRequest.getBowlingAvg());
        return profile;
    }

    public static ProfileResponse profileToProfileResponse(PlayerProfile playerProfile) {
        ProfileResponse response = new ProfileResponse();
        response.setRunsScored(playerProfile.getRunsScored());
        response.setWicketsTaken(playerProfile.getWicketsTaken());
        response.setBattingAvg(playerProfile.getBattingAvg());
        response.setBowlingAvg(playerProfile.getBowlingAvg());
        PlayerResponse playerResponse = PlayerConvertor.playerToPlayerResponse(playerProfile.getPlayer());
        response.setPlayer(playerResponse);
        return response;

    }
}
