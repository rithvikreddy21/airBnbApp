package com.rithvikredddy.projects.airBnbApp.dto;

import com.rithvikredddy.projects.airBnbApp.entity.Guest;
import com.rithvikredddy.projects.airBnbApp.entity.Hotel;
import com.rithvikredddy.projects.airBnbApp.entity.Room;
import com.rithvikredddy.projects.airBnbApp.entity.User;
import com.rithvikredddy.projects.airBnbApp.entity.enums.BookingStatus;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {

    private Long id;
    private Integer roomsCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;
}
