package com.example.fetchrewards;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.List;

public class Results {

    @SerializedName("name")
    private String itemName;

    @SerializedName("listId")
    private int itemId;

    @SerializedName("id")
    private int id;

    public void Results(String name, int itemId, int id){
        this.itemName = name;
        this.itemId = itemId;
        this.id = id;
    }

    public String getName(){
        return itemName;
    }

    public int getlistId(){
        return itemId;
    }

    public int getId() {
        return id;
    }
}
