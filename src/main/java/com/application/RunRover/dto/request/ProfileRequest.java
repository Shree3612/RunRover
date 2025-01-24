package com.application.RunRover.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProfileRequest {

    int runsScored;

    int wicketsTaken;

    double battingAvg;

    double bowlingAvg;

    //lombok not working

    public double getBowlingAvg() {
        return bowlingAvg;
    }

    public void setBowlingAvg(double bowlingAvg) {
        this.bowlingAvg = bowlingAvg;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public double getBattingAvg() {
        return battingAvg;
    }

    public void setBattingAvg(double battingAvg) {
        this.battingAvg = battingAvg;
    }
}
