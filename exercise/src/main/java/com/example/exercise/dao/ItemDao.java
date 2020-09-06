package com.example.exercise.dao;

import com.example.exercise.model.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ItemDao extends CrudRepository<Item,Long> {
    //int insertItem (Item item);

    //List<Item> selectAllItems();
    //Optional<Item> selectItemById(Long id);
    //int updateItemById (Long id, Item item);
    //int deleteItemById (Long id);

    //int withdrawQuantity(Long id, int amount);

    //int  depositQuantity (Long id, int amount);


}
