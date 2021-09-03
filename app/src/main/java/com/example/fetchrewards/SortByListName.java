package com.example.fetchrewards;

import java.util.Comparator;

public class SortByListName implements Comparator<Result> {
    public int compare(Result a, Result b){
        return a.getName().compareTo(b.getName());
    }
}
