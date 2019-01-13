package com.yairn.mad.madmemes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RedditPostViewHolder extends RecyclerView.ViewHolder {

    private TextView authorTextView;
    private TextView scoreTextView;
    private TextView titleTextView;
    private ImageView memeImageView;

    public RedditPostViewHolder(View itemView) {
        super(itemView);

        authorTextView = itemView.findViewById(R.id.reddit_post_author);
        scoreTextView = itemView.findViewById(R.id.reddit_post_score);
        titleTextView = itemView.findViewById(R.id.reddit_post_title);
        memeImageView = itemView.findViewById(R.id.reddit_post_image);
    }

    public void setPost(RedditPost post) {
        authorTextView.setText(post.author);
        scoreTextView.setText("" + post.score);
        titleTextView.setText(post.title);
        if(post.image_url != null) {
            Picasso.get().load(post.image_url).fit().into(memeImageView);
        }
    }

}