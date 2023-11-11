package com.example.backend.comparators;

import com.example.backend.posts.Post;

import java.util.Comparator;

public class DatesComparator implements Comparator<Post> {

    @Override
    public int compare(Post o1, Post o2) {
        if (o1.date < o2.date) {
            return 1;
        } else if (o1.date == o2.date) {
            return 0;
        } else {
            return -1;
        }
    }
}
