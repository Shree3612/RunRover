package com.application.RunRover.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Column(name="ranking",unique = true)
    int ranking;

    int iccPoints;

    String coach;

    @OneToMany(mappedBy = "team")
    @JsonBackReference
    List<Player> players;

    @ManyToMany(mappedBy = "teams")
    @JsonBackReference
    List<CricketMatch> matches;

    //lombok not working

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getIccPoints() {
        return iccPoints;
    }

    public void setIccPoints(int iccPoints) {
        this.iccPoints = iccPoints;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<CricketMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<CricketMatch> matches) {
        this.matches = matches;
    }
}