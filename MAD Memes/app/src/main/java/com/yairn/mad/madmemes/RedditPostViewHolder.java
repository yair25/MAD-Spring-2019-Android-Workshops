package com.yairn.mad.madmemes;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class RedditPostViewHolder extends RecyclerView.ViewHolder {

    private TextView authorTextView;
    private TextView scoreTextView;
    private TextView titleTextView;
    private ImageView memeImageView;
    private ProgressBar memeImageProgressBar;

    /*
     * Constructor that binds the UI to the code, and shows Progress bar
     */
    public RedditPostViewHolder(View itemView) {
        super(itemView);

        authorTextView = itemView.findViewById(R.id.reddit_post_author);
        scoreTextView = itemView.findViewById(R.id.reddit_post_score);
        titleTextView = itemView.findViewById(R.id.reddit_post_title);
        memeImageView = itemView.findViewById(R.id.reddit_post_image);
        memeImageProgressBar = itemView.findViewById(R.id.reddit_post_image_progress_bar);
        memeImageProgressBar.setVisibility(View.VISIBLE);
    }

    /*
     * The setPost method, add a post's data to the View Holder so the data can be displayed
     */
    public void setPost(RedditPost post) {
        authorTextView.setText(post.author);
        scoreTextView.setText("" + post.score);
        titleTextView.setText(post.title);
        if(post.image_url != null) {
            Picasso.get().load(post.image_url).fit().into(memeImageView);
            memeImageProgressBar.setVisibility(View.INVISIBLE);

        }
    }

}
