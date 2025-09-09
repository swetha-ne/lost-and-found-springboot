package com.example.lostandfound.service;

import com.example.lostandfound.entity.Item;
import com.example.lostandfound.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    public Item save(Item item) {
        logger.info("Saving item: {}", item.getName());
        return itemRepository.save(item);
    }

    public List<Item> findAll() {
        logger.info("Fetching all items");
        return itemRepository.findAll();
    }

    public Optional<Item> findById(Long id) {
        logger.info("Fetching item with ID: {}", id);
        return itemRepository.findById(id);
    }

    public void deleteById(Long id) {
        logger.info("Deleting item with ID: {}", id);
        itemRepository.deleteById(id);
    }
}