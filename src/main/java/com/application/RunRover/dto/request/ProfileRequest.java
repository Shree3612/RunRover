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

}
