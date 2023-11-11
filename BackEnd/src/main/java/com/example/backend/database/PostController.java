package com.example.backend.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {
    private final PostService service;

    public PostController(@Autowired PostService service) {
        this.service = service;
    }

    @PostMapping("get")
    public List<Post> getPosts(@RequestBody String format) {
        try {
            return service.getPosts(format);
        } catch (IOException e) {
            // Error case
            System.out.println("Could not successfully parse database.");
            return new ArrayList<Post>(0);
        }
    }
}
