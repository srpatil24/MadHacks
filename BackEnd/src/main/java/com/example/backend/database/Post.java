package com.example.backend.database;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single post.
 */
public class Post {
    @JsonProperty("uniqueID")
    public int uniqueID;

    @JsonProperty("date")
    public int date;

    @JsonProperty("eventDate")
    public int eventDate;

    @JsonProperty("orgName")
    public String orgName;

    @JsonProperty("postText")
    public String postText;

    @JsonProperty("image")
    public String image;

    public Post() {

    }

    public Post(int uniqueID, int date, int eventDate, String orgName, String postText, String image) {
        this.uniqueID = uniqueID;
        this.date = date;
        this.eventDate = eventDate;
        this.orgName = orgName;
        this.postText = postText;
        this.image = image;
    }

    @Override
    public String toString() {
        return "ID: " + uniqueID + "\nDate: " + date + "\nEvent Date: " + eventDate + "\nOrg Name: " + orgName + "\nPost Text: " + postText + "\nImage: " + image;
    }
}
