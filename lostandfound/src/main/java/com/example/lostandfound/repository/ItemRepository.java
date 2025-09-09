package com.example.lostandfound.repository;

import com.example.lostandfound.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}