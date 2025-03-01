package com.application.RunRover.service;

import com.application.RunRover.converter.PlayerConvertor;
import com.application.RunRover.dto.request.PlayerRequest;
import com.application.RunRover.dto.response.PlayerResponse;
import com.application.RunRover.exception.PlayerNotFoundException;
import com.application.RunRover.model.Player;
import com.application.RunRover.model.enums.Speciality;
import com.application.RunRover.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    JavaMailSender javaMailSender;

    private boolean isSmtpEnabled = false; // Temporary flag to disable SMTP

    public PlayerResponse addPlayer(PlayerRequest playerRequest) {
        Player player = PlayerConvertor.playerRequestToPlayer(playerRequest);
        Player savedPlayer = playerRepository.save(player);
        if(isSmtpEnabled){
            sendEmail(savedPlayer);
        } else {
            System.out.println("SMTP is temporarily disabled. Email not sent.");
        }
        return PlayerConvertor.playerToPlayerResponse(savedPlayer);
    }

    private void sendEmail(Player player) {

        String text = "Congrats!! " + player.getName() + ". You have been registered on our cricbuzz portal" +
                ". Your speciality is " + player.getSpeciality();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("acciojobspring@gmail.com");
        message.setTo(player.getEmail());
        message.setSubject("Registration successfull");
        message.setText(text);
        javaMailSender.send(message);

    }

    public List<PlayerResponse> getAllByAgeGreaterThan(int age) {

        List<Player> players = playerRepository.findByAgeGreaterThanEqual(age);
        List<PlayerResponse> playerResponses = new ArrayList<>();
        for(Player player: players) {
            playerResponses.add(PlayerConvertor.playerToPlayerResponse(player));
        }

        return playerResponses;
    }

    public List<PlayerResponse> getAllByAgeGreaterThanAndSpeciality(int age, Speciality speciality) {
        List<Player> players = playerRepository.getAllByAgeGreaterThanAndSpeciality(age,speciality);
        List<PlayerResponse> playerResponses = new ArrayList<>();
        for(Player player: players) {
            playerResponses.add(PlayerConvertor.playerToPlayerResponse(player));
        }

        return playerResponses;
    }

    public PlayerResponse getPlayer(int playerId) {
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with id: " + playerId));
        PlayerResponse playerResponse = PlayerConvertor.playerToPlayerResponse(player);
        return playerResponse;
    }

    public PlayerResponse updatePlayer(PlayerRequest playerRequest, int playerId){
        Player existingPlayer = playerRepository.findById(playerId)
                .orElseThrow(() -> new PlayerNotFoundException("Player not found with id: " + playerId));
        existingPlayer.setName(playerRequest.getName());
        existingPlayer.setAge(playerRequest.getAge());
        existingPlayer.setGender(playerRequest.getGender());
        existingPlayer.setSpeciality(playerRequest.getSpeciality());
        existingPlayer.setEmail(playerRequest.getEmail());
        Player savedPlayer = playerRepository.save(existingPlayer);
        return PlayerConvertor.playerToPlayerResponse(savedPlayer);
    }

    public String deletePlayer(int playerId){
        Player existingOPlayer = playerRepository.findById(playerId)
                                .orElseThrow(() ->new PlayerNotFoundException("Player with this ID does not exist."));
        playerRepository.deleteById(playerId);
        return "Player deleted successfully.";
    }

    public Player getPlayerWithHighestRunsScored() {
        Player player =  playerRepository.findPlayerWithHighestRunsScored()
                .orElseThrow(() -> new PlayerNotFoundException("No player present in the database"));
        return player;
    }

    public Player getPlayerWithMinimumRunsScored() {
        List<Player> players = playerRepository.findAll();
        if (players.isEmpty()) {
            throw new PlayerNotFoundException("No player present in the database");
        }
        Player minRunsPlayer = null;
        int minRuns = Integer.MAX_VALUE;
        for (Player player : players) {
            if (player.getProfile() != null && player.getProfile().getRunsScored() < minRuns) {
                minRuns = player.getProfile().getRunsScored();
                minRunsPlayer = player;
            }
        }
        if (minRunsPlayer == null) {
            throw new PlayerNotFoundException("No player with valid runs data found");
        }
        return minRunsPlayer;
    }

    public Player maximumWickets() {
        List<Player> players = playerRepository.findAll();
        if(players==null) {
             throw new PlayerNotFoundException("Player not present in the database");
        }
        Player player = null;
        int maximumWickets = 0;
        for(Player p : players) {
            if(p.getProfile()!=null && p.getProfile().getWicketsTaken()>maximumWickets) {
                player = p;
                maximumWickets = p.getProfile().getWicketsTaken();
            }
        }
        return player;
    }

    public List<Player> playerNameContainingWords(String str){
        List<Player> players = playerRepository.findAll();
        if(players.isEmpty()) {
            throw new PlayerNotFoundException("Players not present in Database");
        }
        List<Player> result = new ArrayList<>();
        for(Player p : players) {
            String name = p.getName();
            if(name.contains(str)) {
                result.add(p);
            }
        }
        if(result.isEmpty()) {
            throw new PlayerNotFoundException("Players not present in Database");
        }
        return result;
    }

}