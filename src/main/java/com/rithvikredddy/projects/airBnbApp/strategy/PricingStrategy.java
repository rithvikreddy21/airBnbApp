package com.rithvikredddy.projects.airBnbApp.strategy;

import com.rithvikredddy.projects.airBnbApp.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
