package com.application.RunRover.converter;

import com.application.RunRover.dto.request.PlayerRequest;
import com.application.RunRover.dto.response.PlayerResponse;
import com.application.RunRover.model.Player;

public class PlayerConvertor {
    public static Player playerRequestToPlayer(PlayerRequest playerRequest) {
        Player player = new Player();
        player.setName(playerRequest.getName());
        player.setAge(playerRequest.getAge());
        player.setGender(playerRequest.getGender());
        player.setSpeciality(playerRequest.getSpeciality());
        player.setEmail(playerRequest.getEmail());
       return player;
    }

    public static PlayerResponse playerToPlayerResponse(Player player) {
        PlayerResponse response = new PlayerResponse();
        response.setName(player.getName());
        response.setSpeciality(player.getSpeciality());
        return response;
    }
}
