package com.yairn.mad.madmemes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MemeAdapter extends RecyclerView.Adapter<RedditPostViewHolder> {

    private ArrayList<RedditPost> mPosts = null;

    public MemeAdapter() {
        mPosts = new ArrayList<>();
    }

    public void addPosts(ArrayList<RedditPost> posts) {
        for(RedditPost post: posts) {
            mPosts.add(post);
        }
        notifyDataSetChanged();
    }

    @Override
    public RedditPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View redditPostView = layoutInflater.inflate(R.layout.reddit_post_item, parent, false);

        return new RedditPostViewHolder(redditPostView);
    }

    @Override
    public void onBindViewHolder(@NonNull RedditPostViewHolder holder, int position) {
        holder.setPost(mPosts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }
}
