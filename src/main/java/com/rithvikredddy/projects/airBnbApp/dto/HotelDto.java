package com.rithvikredddy.projects.airBnbApp.dto;

import com.rithvikredddy.projects.airBnbApp.entity.HotelContactInfo;
import lombok.Data;

@Data
public class HotelDto {

    private Long Id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;
    private Boolean active;

}
