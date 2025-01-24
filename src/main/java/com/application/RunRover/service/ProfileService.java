package com.application.RunRover.service;

import com.application.RunRover.converter.ProfileConvertor;
import com.application.RunRover.dto.request.ProfileRequest;
import com.application.RunRover.dto.response.ProfileResponse;
import com.application.RunRover.exception.PlayerNotFoundException;
import com.application.RunRover.model.Player;
import com.application.RunRover.model.PlayerProfile;
import com.application.RunRover.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    PlayerRepository playerRepository;

    public ProfileResponse addPlayerProfile(ProfileRequest profileRequest,
                                            int playerId) {
//        Player player = playerRepository.findById(playerId)
//                .orElseThrow(() -> new PlayerNotFoundException("Invalid Player id"));
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isEmpty()) {
            throw new PlayerNotFoundException("Invalid player Id");
        }
        Player player = optionalPlayer.get();
        PlayerProfile playerProfile = ProfileConvertor.profileRequstToProfile(profileRequest);
        player.setProfile(playerProfile);
        playerProfile.setPlayer(player);
        Player savedPlayer = playerRepository.save(player); // player + playerprofile
        return ProfileConvertor.profileToProfileResponse(savedPlayer.getProfile());
    }

    public String updateProfile(ProfileRequest profileRequest, int playerId){
        // Step 1: Retrieve the player by ID
        Optional<Player> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isEmpty()) {
            throw new PlayerNotFoundException("Invalid player Id");
        }

        // Step 2: Get the existing player
        Player player = optionalPlayer.get();

        // Step 3: Retrieve the existing PlayerProfile, or create a new one if it doesn't exist
        PlayerProfile existingProfile = player.getProfile();
        if (existingProfile == null) {
            // If there's no existing profile, create a new one
            existingProfile = new PlayerProfile();
            existingProfile.setPlayer(player); // Link the profile to the player
        }

        // Step 4: Update the existing profile with new data from ProfileRequest
        existingProfile.setRunsScored(profileRequest.getRunsScored());
        existingProfile.setWicketsTaken(profileRequest.getWicketsTaken());
        existingProfile.setBattingAvg(profileRequest.getBattingAvg());
        existingProfile.setBowlingAvg(profileRequest.getBowlingAvg());

        // Step 5: Save the updated player (this will update the profile because of cascading)
        playerRepository.save(player);

        // Step 6: Return success message
        return "Profile updated successfully.";
    }
}
