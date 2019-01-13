package com.yairn.mad.madmemes;

public class RedditPost {

    String author;
    String title;
    long score;
    String image_url;
    String lastPostRef;

    public RedditPost() {

    }

    public RedditPost(String author, String title, long score, String image_url, String lastPostRef) {
        this.author = author;
        this.title = title;
        this.score = score;
        this.image_url = image_url;
        this.lastPostRef = lastPostRef;
    }


}
