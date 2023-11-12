package com.example.backend.comparators;

import com.example.backend.posts.Post;

import java.util.Comparator;
import java.util.Date;

public class DatesComparator implements Comparator<Post> {

    @Override
    public int compare(Post o1, Post o2) {
        String[] o1DateSplice = o1.date.substring(0, 10).split("-");
        String[] o2DateSplice = o2.date.substring(0, 10).split("-");

        Date date1 = new Date(Integer.parseInt(o1DateSplice[0]), Integer.parseInt(o1DateSplice[1]) - 1, Integer.parseInt(o1DateSplice[2]));
        Date date2 = new Date(Integer.parseInt(o2DateSplice[0]), Integer.parseInt(o2DateSplice[1]) - 1, Integer.parseInt(o2DateSplice[2]));

        return date2.compareTo(date1);
    }
}
