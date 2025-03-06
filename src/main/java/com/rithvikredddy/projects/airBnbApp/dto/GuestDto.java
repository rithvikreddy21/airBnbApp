package com.rithvikredddy.projects.airBnbApp.dto;

import com.rithvikredddy.projects.airBnbApp.entity.User;
import com.rithvikredddy.projects.airBnbApp.entity.enums.Gender;
import lombok.Data;

@Data
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
