package com.example.backend.comparators;

import com.example.backend.posts.Post;

import java.util.Comparator;

public class AlphaComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        return o1.orgName.compareTo(o2.orgName);
    }
}
