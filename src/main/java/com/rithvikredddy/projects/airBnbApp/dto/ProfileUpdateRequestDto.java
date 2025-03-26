package com.rithvikredddy.projects.airBnbApp.dto;

import com.rithvikredddy.projects.airBnbApp.entity.enums.Gender;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ProfileUpdateRequestDto {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;
}
