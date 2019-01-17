package com.yairn.mad.madmemes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

public class MemeAdapter extends RecyclerView.Adapter<RedditPostViewHolder> implements GetRedditPosts.GetRedditPostsCallback {

    /*
     * Container that stores the Reddit Posts
     */
    private ArrayList<RedditPost> mPosts = null;

    /*
     * The onBackgroundCallComplete method, is part of the callback from the Async Task class GetRedditPosts.
     * This method is called once the reddit posts have arrived and are stored in the posts ArrayList
     * parameter. The new posts are added to the adapter so they are displayed in the RecyclerView.
     */
    @Override
    public void onBackgroundCallComplete(ArrayList<RedditPost> posts) {
        addPosts(posts);
    }

    /*
     * The Adapter's constructor, it only initializes the container .
     */
    public MemeAdapter() {
        mPosts = new ArrayList<>();
    }

    /*
     * The addPosts method, adds all of the new posts to the container.
     */
    public void addPosts(ArrayList<RedditPost> posts) {
        for(RedditPost post: posts) {
            mPosts.add(post);
        }
        notifyDataSetChanged();
    }

    /*
     * The onCreateViewHolder method, inflates/creates the Reddit posts items based on the
     * reddit_post_item layout
     */
    @Override
    public RedditPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View redditPostView = layoutInflater.inflate(R.layout.reddit_post_item, parent, false);

        return new RedditPostViewHolder(redditPostView);
    }

    /*
     * The onBindViewHolder method, binds the position of the item/holder in the RecycleView with its
     * own data/posts. This method also gets more reddit posts once the user arrives to the last 2 posts
     * in the container.
     */
    @Override
    public void onBindViewHolder(@NonNull RedditPostViewHolder holder, int position) {
        holder.setPost(mPosts.get(position));

        if(position + 2 > mPosts.size()) {
            new GetRedditPosts(this, "?after=" + mPosts.get(position).lastPostRef).execute();

        }
    }

    /*
     * The getItemCount method, returns the number of posts in the container.
     */
    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}
