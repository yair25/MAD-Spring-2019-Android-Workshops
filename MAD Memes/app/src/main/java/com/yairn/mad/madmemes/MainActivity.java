package com.yairn.mad.madmemes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetRedditPosts.GetRedditPostsCallback {

    private MemeAdapter mAdapter;

    @Override
    public void onBackgroundCallComplete(ArrayList<RedditPost> posts) {
        mAdapter.addPosts(posts);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        new GetRedditPosts(this).execute();

    }
}
