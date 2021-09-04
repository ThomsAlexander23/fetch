package com.example.fetchrewards.Models;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("name")
    public String itemName;

    @SerializedName("listId")
    public int listId;

    @SerializedName("id")
    public int id;

    public Result(int id, int listId, String name){
        this.itemName = name;
        this.listId = listId;
        this.id = id;
    }

    public String getName(){
        return this.itemName;
    }

    public int getListId(){
        return this.listId;
    }

    public int getId() {
        return this.id;
    }

}
