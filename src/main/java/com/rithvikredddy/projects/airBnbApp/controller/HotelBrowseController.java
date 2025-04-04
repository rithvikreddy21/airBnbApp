package com.rithvikredddy.projects.airBnbApp.controller;

import com.rithvikredddy.projects.airBnbApp.dto.HotelDto;
import com.rithvikredddy.projects.airBnbApp.dto.HotelInfoDto;
import com.rithvikredddy.projects.airBnbApp.dto.HotelPriceDto;
import com.rithvikredddy.projects.airBnbApp.dto.HotelSearchRequest;
import com.rithvikredddy.projects.airBnbApp.service.HotelService;
import com.rithvikredddy.projects.airBnbApp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){
        Page<HotelPriceDto> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
    }
}
