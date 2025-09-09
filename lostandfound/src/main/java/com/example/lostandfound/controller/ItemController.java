package com.example.lostandfound.controller;

import com.example.lostandfound.entity.Item;
import com.example.lostandfound.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private static final Logger logger = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAll() {
        logger.info("Fetching all items");
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable Long id) {
        logger.info("Fetching item with ID: {}", id);
        return itemService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Item not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public Item create(@RequestBody Item item) {
        logger.info("Creating item: {}", item.getName());
        return itemService.save(item);
    }

    @PutMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item) {
        logger.info("Updating item with ID: {}", id);
        return itemService.findById(id)
                .map(existing -> {
                    item.setId(id);
                    return ResponseEntity.ok(itemService.save(item));
                })
                .orElseGet(() -> {
                    logger.warn("Item not found with ID: {}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        logger.info("Deleting item with ID: {}", id);
        itemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}