package com.yairn.mad.madmemes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetRedditPosts.GetRedditPostsCallback {

    private ProgressBar loadingProgressBar;
    private MemeAdapter mAdapter;

    /*
     * The onBackgroundCallComplete method, is part of the callback from the Async Task class GetRedditPosts.
     * This method is called once the reddit posts have arrived and are stored in the posts ArrayList
     * parameter. The new posts are added to the adapter so they are displayed in the RecyclerView, and
     * the loading progress bar is hidden.
     */
    @Override
    public void onBackgroundCallComplete(ArrayList<RedditPost> posts) {
        mAdapter.addPosts(posts);
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    /*
     * onCreate is an Android method that is called when the activity is first created.
     * This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
     * Always followed by onStart().
     *
     * In this method, the contentView, or in other words the UI layout named activity_main
     * will be associated with this code, and it will create/display it.
     * Additionally, this is where the code is being bounded to the XML/UI, by using findViewByID.
     * This is where listeners can bounded to most UIs, or just use the OnClick
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingProgressBar = findViewById(R.id.memeProgressBar);
        loadingProgressBar.setVisibility(View.VISIBLE);
        setupRecyclerView();
    }

    /*
     * The setupRecyclerView method is a helper method that sets up the recycler view
     */
    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.memeRecyclerView);
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MemeAdapter();
        recyclerView.setAdapter(mAdapter);

        // Starts the Async Task to get the Reddit posts
        new GetRedditPosts(this, "").execute();

    }

}
