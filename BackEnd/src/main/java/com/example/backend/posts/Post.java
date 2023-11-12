package com.example.backend.posts;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a single post.
 */
public class Post {
    @JsonProperty("image")
    public String image;

    @JsonProperty("caption")
    public String caption;

    @JsonProperty("clubName")
    public String clubName;

    @JsonProperty("date")
    public String date;

    @JsonProperty("clubDescription")
    public String clubDescription;

    @JsonProperty("twitter")
    public String twitter;

    @JsonProperty("facebook")
    public String facebook;

    @JsonProperty("instagram")
    public String instagram;

    @JsonProperty("category")
    public String category;

    public Post() {

    }

    public Post(String image, String caption, String clubName, String date, String clubDescription, String twitter, String facebook, String instagram, String category) {
        this.image = image;
        this.caption = caption;
        this.clubName = clubName;
        this.date = date;
        this.clubDescription = clubDescription;
        this.twitter = twitter;
        this.facebook = facebook;
        this.instagram = instagram;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Image: " + image + "\nCaption: " + caption + "\nClubName: " + clubName + "\nDate: " + date + "\nclubDescription: " + clubDescription + "\n" +
                "Twitter: " + twitter + "\nFacebook: " + facebook + "\nInstagram: " + instagram;
    }
}
