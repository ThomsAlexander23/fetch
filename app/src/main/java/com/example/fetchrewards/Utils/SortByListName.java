package com.example.fetchrewards.Utils;

import com.example.fetchrewards.Models.Result;
import java.util.Comparator;

public class SortByListName implements Comparator<Result> {
    public int compare(Result a, Result b){
        return a.getName().compareTo(b.getName());
    }
}
