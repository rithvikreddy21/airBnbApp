package com.rithvikredddy.projects.airBnbApp.strategy;

import com.rithvikredddy.projects.airBnbApp.entity.Inventory;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class SurgePricingStrategy implements PricingStrategy{

    private final PricingStrategy wrapped;

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return wrapped.calculatePrice(inventory).multiply(inventory.getSurgeFactor());
    }
}
