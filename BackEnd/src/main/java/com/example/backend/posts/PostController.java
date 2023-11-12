package com.example.backend.posts;

import com.example.backend.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin(origins = Constants.origin)
    @PostMapping("get")
    public List<Post> getPosts(@RequestBody Format format) {
        try {
            return service.getPosts(format.format).subList(0, 1);
        } catch (IOException e) {
            // Error case
            System.out.println("Could not successfully parse database.");
            return new ArrayList<Post>(0);
        }
    }
}
