/*
package com.example.exercise.dao;

import com.example.exercise.model.Item;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeDao")
public class FakeItemDataAccessService implements ItemDao {

    private static List<Item> DB = new ArrayList<>();

    @Override
    public int insertItem(UUID id, Item item) {
        DB.add(new Item(id, item.getName(), item.getAmount(), item.getInventoryCode()));
        return 1;
    }

    @Override
    public List<Item> selectAllItems() {
        return DB;
    }

    @Override
    public Optional<Item> selectItemById(UUID id) {
        return DB.stream()
                .filter(item -> item.getItemNo().equals(id))
                .findFirst();
    }

    @Override
    public int updateItemById(UUID id, Item item) {
        return selectItemById(id)
                .map(item1 -> {
                    int indexOfItem = DB.indexOf(item1);
                    if (indexOfItem>=0){
                        DB.set(indexOfItem,new Item(id, item.getName(),item.getAmount(), item.getInventoryCode()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }

    @Override
    public int deleteItemById(UUID id) {
        Optional<Item> item = this.selectItemById(id);
        if (item.isPresent()){
            DB.remove(item.get());
            return 1;
        }
        return 0;
    }

    @Override
    public int withdrawQuantity(UUID id, int amount) {
        return selectItemById(id)
                .map(item1 -> {
                    int indexOfItem = DB.indexOf(item1);
                    if (indexOfItem>=0 && item1.getAmount()> amount){
                        DB.set(indexOfItem,new Item(
                                id,
                                item1.getName(),
                                item1.getAmount()-amount,
                                item1.getInventoryCode()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }


    @Override
    public int depositQuantity(UUID id, int amount) {
        if(amount<1){
            return 0;
        }
        return selectItemById(id)
                .map(item1 -> {
                    int indexOfItem = DB.indexOf(item1);
                    if (indexOfItem>=0){
                        DB.set(indexOfItem,new Item(
                                id,
                                item1.getName(),
                                item1.getAmount()+amount,
                                item1.getInventoryCode()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
*/
