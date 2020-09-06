package com.example.exercise.model;

import com.example.exercise.dao.ItemDao;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;


import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.UUID;
//FakeItemDataAccessService wont work anymore, was used for testing and itemNo was UUID
//switched itemNo from UUID to Long because it looks ugly in the browser navigation bar
//while sending a get req
@Entity
public class Item {
    @Id
    @GeneratedValue
    private Long itemNo;
    @NotBlank
    private String name;
    @Positive
    @Digits(integer = 5, fraction = 0)
    private int amount;
    @Positive
    @Digits(integer = 5, fraction = 0)
    private int inventoryCode;

    protected Item(){};
    /**
     * Create new item or update existing one using the same itemNo (id)
     * itemNo: I.D. of the specific item
     * @param name: a string of the item's name
     * @param amount: current quantity in our inventory
     * @param inventoryCode: inventory code, can be used to categorize items
     */
    public Item(
                @JsonProperty("itemNo") Long itemNo,
                @JsonProperty("name") String name,
                @JsonProperty("amount") int amount,
                @JsonProperty("inventoryCode") int inventoryCode) {
        //this.itemNo = itemNo;
        this.name = name;
        this.amount = amount;
        this.inventoryCode = inventoryCode;
    }




    public Long getItemNo() {
        return itemNo;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getInventoryCode() {
        return inventoryCode;
    }

    public int withdraw(int i){
        if(this.getAmount() == 0 || i <= 0){
            return 0;
        }
        if (this.getAmount() <= i){
            return 0;
        }
        this.amount = this.getAmount() - i;
        return 1;
    }

    public int deposit (int i){
        if (i <= 0){
            return 0;
        }
        this.amount = this.getAmount() + i;
        return 1;
    }
}
