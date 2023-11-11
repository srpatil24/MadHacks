package com.example.backend;

import com.example.backend.database.Post;
import com.example.backend.database.PostService;
import com.example.backend.utility.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class BackEndApplicationTests {

    @Test
    void generateTestDatabase() {
        // Create test post
        ArrayList<Post> posts = new ArrayList<>();
        posts.add(new Post(0, 1699733260, 1699733260, "Madhacks", "Madhacks is at 5:00pm today!", "image"));
        posts.add(new Post(0, 1699733260, 1699733260, "Madhacks", "Madhacks is at 5:00pm today!", "image"));
        posts.add(new Post(0, 1699733260, 1699733260, "Madhacks", "Madhacks is at 5:00pm today!", "image"));
        posts.add(new Post(0, 1699733260, 1699733260, "Madhacks", "Madhacks is at 5:00pm today!", "image"));
        posts.add(new Post(0, 1699733260, 1699733260, "Madhacks", "Madhacks is at 5:00pm today!", "image"));

        try {
            new ObjectMapper().writeValue(new File(Constants.databasePath), posts);
        } catch (IOException e) {
            // Error accessing files
            e.printStackTrace();
        }
    }

    @Test
    void readTestDatabase() {
        PostService service = new PostService();
        List<Post> posts;

        try {
            // Try to get the posts and print it out
            posts = service.getPosts("unsorted");
            System.out.println(posts);
        } catch (IOException e) {
            // Couldn't read
            e.printStackTrace();
        }
    }
}
