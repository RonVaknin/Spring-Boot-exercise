package com.example.exercise.api;

import com.example.exercise.model.Item;
import com.example.exercise.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/item")
@RestController
@Api(value = "ItemsControllerApi", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @ApiOperation("Add new item to inventory (randomize ID, no 'itemNo' input needed)")
    public void addItem(@Valid @NonNull @RequestBody Item item){
        itemService.addItem(item);
    }

    @GetMapping
    @ApiOperation("Gets a list of the entire inventory")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "JSON items array",
            response = Item.class,
            responseContainer = "List")})
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }


    @GetMapping(path = "{id}")
    @ApiOperation("Get specific item by item id")
    @ApiResponses(value = {@ApiResponse(code = 200,
            message = "JSON item",
            response = Item.class
    )})
    public Item getItemById(@PathVariable Long id){
        return itemService.getItemById(id)
                .orElse(null);
    }

    @DeleteMapping(path= "{id}")
    @ApiOperation("Delete item by id")
    public void deleteItemById(@PathVariable("id") Long id){
        itemService.deleteItemById(id);
    }

    @PutMapping(path = "withdraw/{id}/{amount}")
    @ApiOperation("If available, remove specified amount from item's quantity in the inventory")
    public void withdrawItemById(@PathVariable("id") Long id, @PathVariable("amount") int amount){
        itemService.withdraw(id,amount);
    }

    @PutMapping(path = "deposit/{id}/{amount}")
    @ApiOperation("Add amount to item's quantity")
    public void depositItemById(@PathVariable("id") Long id, @PathVariable("amount") int amount){
        itemService.deposit(id,amount);
    }
}
