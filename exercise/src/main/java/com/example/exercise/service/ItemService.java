package com.example.exercise.service;

import com.example.exercise.dao.ItemDao;
import com.example.exercise.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemDao itemDao;
    @Autowired
    public ItemService (ItemDao itemDao){
        this.itemDao = itemDao;
    }

    public int addItem (Item item){
        Item item1 = itemDao.save(item);
        if(item1 != null){
            return 1;
        }
        return 0;
    }

    public List<Item> getAllItems(){
        List<Item> items = new ArrayList<Item>();
        itemDao.findAll().forEach(item -> items.add(item));
        return items;
    }

    public Optional<Item> getItemById(Long id){ return itemDao.findById(id); }


    public int deleteItemById(Long id){
        itemDao.deleteById(id);
        if(itemDao.existsById(id)){
            return 0;
        }
        return 1;
    }

    public int withdraw(Long id, int amount){
        if(amount < 1){
            return 0;
        }
        Optional<Item> oldItem = itemDao.findById(id);
        if(!oldItem.isPresent()){
            return 0;
        }
        Item item = oldItem.get();
        int i = item.withdraw(amount);
        if(i>0){
            itemDao.save(item);
        }
        return i;
    }

    public int deposit(Long id, int amount){
        Optional<Item> oldItem = itemDao.findById(id);
        if(!oldItem.isPresent()){
            return 0;
        }
        Item item = oldItem.get();
        int i = item.deposit(amount);
        if (i>0) {
            itemDao.save(item);
        }
        return i;
    };

}
