package com.rithvikredddy.projects.airBnbApp.service;

import com.rithvikredddy.projects.airBnbApp.dto.RoomDto;
import com.rithvikredddy.projects.airBnbApp.entity.Room;

import java.util.List;

public interface RoomService {

    RoomDto createNewRoom(Long hotelId,RoomDto roomDto);

    List<RoomDto> getAllRoomsInHotel(Long hotelId);

    RoomDto getRoomById(Long roomId);

    void deleteRoomById(Long roomId);

    RoomDto updateRoomTypeById(Long roomId, String roomType);

    RoomDto updateRoomById(Long hotelId, Long roomId, RoomDto roomDto);
}
