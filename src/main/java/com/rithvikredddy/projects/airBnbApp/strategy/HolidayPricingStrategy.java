package com.rithvikredddy.projects.airBnbApp.strategy;

import com.rithvikredddy.projects.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;


import java.math.BigDecimal;

@RequiredArgsConstructor
public class HolidayPricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        BigDecimal price = wrapped.calculatePrice(inventory);
        boolean isTodayHoliday = true; // can create a method or an API to check for holiday
        if(isTodayHoliday){
            price = price.multiply(BigDecimal.valueOf(1.25));
        }
        return price;
    }
}
