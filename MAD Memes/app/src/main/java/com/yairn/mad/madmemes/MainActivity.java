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

    @Override
    public void onBackgroundCallComplete(ArrayList<RedditPost> posts) {
        mAdapter.addPosts(posts);
        loadingProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadingProgressBar = findViewById(R.id.memeProgressBar);
        loadingProgressBar.setVisibility(View.VISIBLE);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.memeRecyclerView);
        recyclerView.setHasFixedSize(false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MemeAdapter();
        recyclerView.setAdapter(mAdapter);

        new GetRedditPosts(this, "").execute();

    }

}
