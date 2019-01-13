package com.yairn.mad.madmemes;

public class RedditPost {

    String author;
    String title;
    long score;
    String image_url;

    public RedditPost() {

    }

    public RedditPost(String author, String title, long score, String image_url) {
        this.author = author;
        this.title = title;
        this.score = score;
        this.image_url = image_url;

    }


}
