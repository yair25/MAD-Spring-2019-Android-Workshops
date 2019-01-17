These are some instructions on how to make this application.
There are also some hints/suggestions on what to say to help the understanding.

Steps:
1. Start with app > manifests > AndroidManifest.xml
2. Explain permissions and add internet permission
3. Go to Gradel Scripts > build.grade (Module: app) and add
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'com.squareup.picasso:picasso:2.71828'
4. Start with app > res > layout > activity_main.xml
5. With RelativeLayout, add the RecyclerView & ProgressBar views
6. Next go to app > java > com.________ > MainActivity
7. Code and link to UI

8. Next make RedditPost class in app > java > com._____
9. Code

10. Make reddit_post_item.xml in app > res > layout
11. With RelativeLayout, add the views and attributes
12. Make RedditPostViewHolder & MemeAdapter classes in app > java > com._____
13. Code both (first RedditPostViewHolder)

14. Make GetRedditPosts class (Async Task) in app > java > com._____
15. Code