package com.example.fetchrewards;

import java.util.Comparator;

public class SortByListId implements Comparator<Result> {
    public int compare(Result a, Result b){
        return a.getItemId() - b.getItemId();
    }
}
