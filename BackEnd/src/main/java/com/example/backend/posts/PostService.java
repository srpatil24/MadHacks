package com.example.backend.posts;

import com.example.backend.comparators.AlphaComparator;
import com.example.backend.comparators.DatesComparator;
import com.example.backend.utility.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Collections;
import java.util.List;

@Component
/**
 * Maintians posts database and offers services for grabbing posts and getting the database.
 */
public class PostService {

    /**
     * Gets the posts as an array
     *
     * @param format - : separated list formatted as "sort-type:tag1:tag2:tagN:
     * @return
     * @throws JsonProcessingException
     */
    public List<Post> getPosts(String format) throws IOException {
        // Parse format
        String[] splitFormat = format.split(":");

        String[] cmd;
        if (splitFormat.length > 1) {
            cmd = new String[]{"python3", Constants.databasePath, splitFormat[1]};
        } else {
            cmd = new String[]{"python3", Constants.databasePath};
        }

        Runtime.getRuntime().exec(cmd);

        // Deserialize database from json
        List<Post> posts = new ObjectMapper().readValue(new File(Constants.databasePath), new TypeReference<List<Post>>() {
        });

        // Sort
        switch (splitFormat[0]) {
            case "unsorted":
                break;
            case "sorted-dates":
                Collections.sort(posts, new DatesComparator());
                break;
            case "sorted-alpha":
                Collections.sort(posts, new AlphaComparator());
                break;
            default:
                throw new InvalidParameterException("Invalid sort specifier.");
        }

        System.out.println(posts);
        return posts;
    }

}
