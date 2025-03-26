package com.rithvikredddy.projects.airBnbApp.service;

import com.rithvikredddy.projects.airBnbApp.dto.HotelPriceDto;
import com.rithvikredddy.projects.airBnbApp.dto.HotelSearchRequest;
import com.rithvikredddy.projects.airBnbApp.dto.InventoryDto;
import com.rithvikredddy.projects.airBnbApp.dto.UpdateInventoryRequestDto;
import com.rithvikredddy.projects.airBnbApp.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDto> getAllInventoryByRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);
}
