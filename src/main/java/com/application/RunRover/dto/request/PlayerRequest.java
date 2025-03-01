package com.application.RunRover.dto.request;

import com.application.RunRover.model.enums.Gender;
import com.application.RunRover.model.enums.Speciality;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlayerRequest {

    String name;

    int age;

    Gender gender;

    Speciality speciality;

    String email;

}