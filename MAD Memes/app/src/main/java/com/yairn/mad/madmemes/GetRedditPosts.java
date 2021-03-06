package com.yairn.mad.madmemes;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GetRedditPosts extends AsyncTask<Void, Void, ArrayList<RedditPost>> {

    private static final String reddit_url = "https://www.reddit.com/r/memes/.json";

    // Callback
    private GetRedditPostsCallback callback;

    private String afterPostRef = "";

    /*
     * The constructor that stores the callback and string reference of the last post
     */
    public GetRedditPosts(GetRedditPostsCallback callback, String afterPostRef){
        this.callback = callback;
        this.afterPostRef = afterPostRef;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    /*
     * A thread that gets the reddit posts JSON, parses it, and puts the posts into a container
     */
    @Override
    protected ArrayList<RedditPost> doInBackground(Void... arg0) {

        ArrayList<RedditPost> posts = new ArrayList<>();
        StringBuffer sb = new StringBuffer();

        try {
            URL url = new URL(reddit_url + afterPostRef);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                sb.append(line);
                line = bufferedReader.readLine();
            }

            JSONObject rootJsonObject = new JSONObject(sb.toString());

            JSONObject jsonObjectData = rootJsonObject.getJSONObject("data");

            JSONArray jsonArray = jsonObjectData.getJSONArray("children");
            JSONObject jsonObjectPost, jsonObjectPostData;
            JSONArray jsonArrayImages;
            JSONObject jsonObjectImageSource;
            String postAuthor, postTitle, postURL = null, lastPostRef = null;
            int postScore;

            lastPostRef = jsonObjectData.getString("after");


            // "author", "score", "title", "preview->images[0]->source->url, + width, + height"

            for(int index = 0; index < jsonArray.length(); index++) {
                jsonObjectPost = jsonArray.getJSONObject(index);
                jsonObjectPostData  = jsonObjectPost.getJSONObject("data");

                postTitle = jsonObjectPostData.getString("title");
                postAuthor = jsonObjectPostData.getString("author");
                postScore = jsonObjectPostData.getInt("score");

                jsonArrayImages = jsonObjectPostData.getJSONObject("preview").getJSONArray("images");

                if(jsonArrayImages.length() > 0 && jsonObjectPostData.getString("post_hint").equals("image")) {

                    jsonObjectImageSource = jsonArrayImages.getJSONObject(0).getJSONObject("source");

                    postURL = jsonObjectImageSource.getString("url");

                    postURL = postURL.replace("amp;", "");

                }

                posts.add(new RedditPost(postAuthor, postTitle, postScore, postURL, lastPostRef));

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return posts;
    }

    /*
     * Once the Thread is done, this method is called, and the container with the posts is sent
     * to the call back.
     */
    @Override
    protected void onPostExecute(ArrayList<RedditPost> posts) {
        super.onPostExecute(posts);

        callback.onBackgroundCallComplete(posts);
    }


    /*
     * Callback
     */
    public interface GetRedditPostsCallback {
        public void onBackgroundCallComplete(ArrayList<RedditPost> posts);
    }
}
