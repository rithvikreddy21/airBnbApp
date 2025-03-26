package com.rithvikredddy.projects.airBnbApp.controller;

import com.rithvikredddy.projects.airBnbApp.dto.InventoryDto;
import com.rithvikredddy.projects.airBnbApp.dto.UpdateInventoryRequestDto;
import com.rithvikredddy.projects.airBnbApp.repository.InventoryRepository;
import com.rithvikredddy.projects.airBnbApp.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/inventory")
@RequiredArgsConstructor
@Slf4j
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<List<InventoryDto>> getAllInventoryByRoom(@PathVariable Long roomId){
        return ResponseEntity.ok(inventoryService.getAllInventoryByRoom(roomId));
    }

    @PatchMapping("/rooms/{roomId}")
    public ResponseEntity<Void> updateInventory(@PathVariable Long roomId,
                                                @RequestBody UpdateInventoryRequestDto updateInventoryRequestDto) {
        inventoryService.updateInventory(roomId, updateInventoryRequestDto);
        return ResponseEntity.noContent().build();
    }
}
