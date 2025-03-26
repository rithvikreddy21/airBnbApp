package com.rithvikredddy.projects.airBnbApp.service;

import com.rithvikredddy.projects.airBnbApp.dto.HotelInfoDto;
import com.rithvikredddy.projects.airBnbApp.dto.HotelDto;
import com.rithvikredddy.projects.airBnbApp.dto.RoomDto;
import com.rithvikredddy.projects.airBnbApp.entity.Hotel;
import com.rithvikredddy.projects.airBnbApp.entity.Room;
import com.rithvikredddy.projects.airBnbApp.entity.User;
import com.rithvikredddy.projects.airBnbApp.exception.ResourceNotFoundException;
import com.rithvikredddy.projects.airBnbApp.exception.UnAuthorizedException;
import com.rithvikredddy.projects.airBnbApp.repository.HotelRepository;
import com.rithvikredddy.projects.airBnbApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rithvikredddy.projects.airBnbApp.util.AppUtils.getCurrentUser;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("Creating hotel with name: {}",hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto,Hotel.class);
        hotel.setActive(false);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        hotel.setOwner(user);
        hotel = hotelRepository.save(hotel);
        log.info("Created a new hotel with ID: {}",hotelDto.getId());
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("Getting hotel with ID: {}",id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
           throw new UnAuthorizedException("This user does not own this hotel with id: "+id);
        }
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("Updating hotel with ID: {}",id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotel with id: "+id);
        }
        modelMapper.map(hotelDto,hotel);
        hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long id) {
        boolean exists = hotelRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Hotel not found with Id: " +id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotel with id: "+id);
        }
        for (Room room: hotel.getRooms()){
            inventoryService.deleteAllInventories(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void activateHotel(Long id) {
        log.info("Activating hotel with ID: {}",id);
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+id));
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!user.equals(hotel.getOwner())){
            throw new UnAuthorizedException("This user does not own this hotel with id: "+id);
        }
        hotel.setActive(true);
        // assuming you only do it once
        for(Room room: hotel.getRooms()){
            inventoryService.initializeRoomForAYear(room);
        }

    }

    @Override
    public HotelInfoDto getHotelInfoById(Long hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with ID: "+hotelId));
        List<RoomDto> rooms = hotel.getRooms()
                              .stream()
                              .map((element)->modelMapper.map(element,RoomDto.class))
                              .toList();
        return new HotelInfoDto(modelMapper.map(hotel,HotelDto.class),rooms);
    }

    @Override
    public List<HotelDto> getAllHotels() {
        User user = getCurrentUser();
        log.info("Retrieving the hotels for the owner: "+user.getName());
        List<Hotel> hotels = hotelRepository.findByOwner(user);
        return hotels.stream()
                .map(hotel->(modelMapper.map(hotel,HotelDto.class)))
                .collect(Collectors.toList());
    }


}
