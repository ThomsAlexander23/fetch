package com.example.fetchrewards.Models;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("name")
    public String itemName;

    @SerializedName("listId")
    public int itemId;

    @SerializedName("id")
    public int id;

    public Result(int id, int itemId, String name){
        this.itemName = name;
        this.itemId = itemId;
        this.id = id;
    }

    public String getName(){
        return this.itemName;
    }

    public int getItemId(){
        return this.itemId;
    }

    public int getId() {
        return this.id;
    }

}
