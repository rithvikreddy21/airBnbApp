package com.rithvikredddy.projects.airBnbApp.controller;

import com.rithvikredddy.projects.airBnbApp.dto.BookingDto;
import com.rithvikredddy.projects.airBnbApp.dto.ProfileUpdateRequestDto;
import com.rithvikredddy.projects.airBnbApp.dto.UserDto;
import com.rithvikredddy.projects.airBnbApp.service.BookingService;
import com.rithvikredddy.projects.airBnbApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final BookingService bookingService;

    @PatchMapping("/profile")
    public ResponseEntity<Void> updateProfile(@RequestBody ProfileUpdateRequestDto profileUpdateRequestDto) {
        userService.updateProfile(profileUpdateRequestDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/myBookings")
    public ResponseEntity<List<BookingDto>> getMyBookings() {
        return ResponseEntity.ok(bookingService.getMyBookings());
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDto> getMyProfile() {
        return ResponseEntity.ok(userService.getMyProfile());
    }

}
