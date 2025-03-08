package com.rithvikredddy.projects.airBnbApp.service;

import com.rithvikredddy.projects.airBnbApp.entity.Hotel;
import com.rithvikredddy.projects.airBnbApp.entity.HotelMinPrice;
import com.rithvikredddy.projects.airBnbApp.entity.Inventory;
import com.rithvikredddy.projects.airBnbApp.repository.HotelMinPriceRepository;
import com.rithvikredddy.projects.airBnbApp.repository.HotelRepository;
import com.rithvikredddy.projects.airBnbApp.repository.InventoryRepository;
import com.rithvikredddy.projects.airBnbApp.strategy.PricingService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PricingUpdateService {

    private final HotelRepository hotelRepository;
    private final InventoryRepository inventoryRepository;
    private final HotelMinPriceRepository hotelMinPriceRepository;
    private final PricingService pricingService;

    // scheduler to update inventory and HotelMinPrice tables every hour

    @Scheduled(cron = "0 0 * * * *")
    public void updatePrices(){

        int page = 0;
        int batchSize = 100;

        while (true){
            Page<Hotel> hotelPage = hotelRepository.findAll(PageRequest.of(page,batchSize));
            if(hotelPage.isEmpty()){
                break;
            }
            hotelPage.getContent().forEach(this::updateHotelPrices);
            page++;
        }
    }

    private void updateHotelPrices(Hotel hotel){
        log.info("Updating room prices for hotel with Id: {}",hotel.getId());
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(1);

        List<Inventory> inventoryList = inventoryRepository.findByHotelAndDateBetween(hotel,startDate,endDate);
        updateInventoryPrices(inventoryList);
        updateHotelMinPrices(hotel,inventoryList,startDate,endDate);
    }

    private void updateHotelMinPrices(Hotel hotel, List<Inventory> inventoryList, LocalDate startDate, LocalDate endDate) {
        // compute minimum price per day for the hotel
        Map<LocalDate,BigDecimal> dailyMinPrice = inventoryList.stream()
                .collect(Collectors.groupingBy(
                        Inventory::getDate,
                        Collectors.mapping(Inventory::getPrice,Collectors.minBy(Comparator.naturalOrder()))
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e->e.getValue().orElse(BigDecimal.ZERO)));

        // prepare hotel price entities in bulk
        List<HotelMinPrice> hotelPrices = new ArrayList<>();
        dailyMinPrice.forEach((date,price)->{
            HotelMinPrice hotelMinPrice = hotelMinPriceRepository.findByHotelAndDate(hotel,date)
                    .orElse(new HotelMinPrice(hotel,date));
            hotelMinPrice.setPrice(price);
            hotelPrices.add(hotelMinPrice);
        });

        hotelMinPriceRepository.saveAll(hotelPrices);

    }

    private void updateInventoryPrices(List<Inventory> inventoryList){
        inventoryList.forEach(inventory -> {
            BigDecimal dynamicprice = pricingService.calculateDynamicPricing(inventory);
            inventory.setPrice(dynamicprice);
        });
        inventoryRepository.saveAll(inventoryList);
    }

}
