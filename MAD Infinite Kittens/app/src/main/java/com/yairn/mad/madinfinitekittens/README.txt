These are some instructions on how to make this application.
There are also some hints/suggestions on what to say to help the understanding.

Steps:
1. Start with app > manifests > AndroidManifest.xml
2. Explain permissions and add internet permission
3. Go to Gradel Scripts > build.grade (Module: app) and add
    implementation 'com.android.support:recyclerview-v7:28.0.0'
    implementation 'com.android.support:cardview-v7:28.0.0'
    implementation 'com.squareup.picasso:picasso:2.71828'
4. Start with app > res > layout > activity_main.xml
5. With ConstraintLayout, add the RecyclerView
6. Next go to app > java > com.________ > MainActivity
7. Code and link to UI

8. Next make KittenImageActivity and go to app > res > layout > activity_kitten_image.xml
9. With ConstraintLayout, add the views
10. Add appropriate strings to app > res > values > strings.xml
11. Next go to app > java > com.________ > KittenImageActivity
12. Code and link to UI

13. Next make kitten_item_card.xml in app > res > layout
14. With LinearLayout, add the views
15. Add the ic_ drawables
16. Next make KittenItemHolder and KittenAdapter
17. Code both (first kittenItemHolder)