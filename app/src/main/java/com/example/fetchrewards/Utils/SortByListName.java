package com.example.fetchrewards.Utils;

import com.example.fetchrewards.Models.Result;
import java.util.Comparator;

public class SortByListName implements Comparator<Result> {
    public int compare(Result a, Result b) {
        return extractInt(a.getName()) - extractInt(b.getName());
    }

    int extractInt(String s) {
        String num = s.replaceAll("\\D", "");
        // return 0 if no digits found
        return num.isEmpty() ? 0 : Integer.parseInt(num);
    }
};
