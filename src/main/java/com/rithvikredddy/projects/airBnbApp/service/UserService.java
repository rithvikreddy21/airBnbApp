package com.rithvikredddy.projects.airBnbApp.service;

import com.rithvikredddy.projects.airBnbApp.dto.ProfileUpdateRequestDto;
import com.rithvikredddy.projects.airBnbApp.dto.UserDto;
import com.rithvikredddy.projects.airBnbApp.entity.User;

public interface UserService {
    User getUserById(Long id);

    void updateProfile(ProfileUpdateRequestDto profileUpdateRequestDto);

    UserDto getMyProfile();
}
