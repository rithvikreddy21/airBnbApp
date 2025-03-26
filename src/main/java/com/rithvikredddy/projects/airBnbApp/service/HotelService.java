package com.rithvikredddy.projects.airBnbApp.service;

import com.rithvikredddy.projects.airBnbApp.dto.HotelInfoDto;
import com.rithvikredddy.projects.airBnbApp.dto.HotelDto;

import java.util.List;

public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id,HotelDto hotelDto);

    void deleteHotelById(Long id);

    void activateHotel(Long id);

    HotelInfoDto getHotelInfoById(Long hotelId);

    List<HotelDto> getAllHotels();
}
