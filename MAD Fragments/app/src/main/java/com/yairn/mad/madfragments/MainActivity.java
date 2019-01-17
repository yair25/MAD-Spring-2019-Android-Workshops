package com.yairn.mad.madfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener,
        PicsFragment.OnPicsFragmentInteractionListener, FullPicFragment.OnFullImageFragmentInteractionListener {

    private ListFragment listFragment;
    private PicsFragment picsFragment;

    private FullPicFragment fullPicFragment;

    private int lastYear = -1;

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

        listFragment = new ListFragment();
        picsFragment = new PicsFragment();

        // Fragments are replacing parts of the screen in activity_main.xml
        getSupportFragmentManager().beginTransaction().replace(R.id.listFrame, listFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.pictureFrame, picsFragment).commit();

        fullPicFragment = null;
    }

    /*
     * A listener for the ListFragment. Depending on what item is tapped on the list, the picsFragment
     * will update to the correct image.
     */
    public void onListFragmentInteraction(int year) {
        lastYear = year;
        picsFragment.setPic(year);
    }

    /*
     * A listener for the PicsFragment. Depending on what image is tapped in the picsFragment, the
     * fullPicFragment will show that image in full screen.
     */
    public void onPicsFragmentInteraction(int id) {
        fullPicFragment = new FullPicFragment().newInstance(id);

        // Remove listFragment and picsFragment so that there is no unwanted interaction in the background
        getSupportFragmentManager().beginTransaction().replace(R.id.FullImageFrame, fullPicFragment).remove(listFragment).remove(picsFragment).commit();
    }

    /*
     * A listener for the FullImageFragment. If the screen is tapped, the fullPicFragment will be
     * removed, and the picsFragment and listFragment will be placed back on like they were before.
     */
    public void onFullImageFragmentInteraction(int id) {
        switch (id) {
            case R.drawable.mad_logo_14:
                lastYear = 0;
                break;
            case R.drawable.mad_logo_15:
                lastYear = 1;
                break;
            case R.drawable.mad_logo_16:
                lastYear = 2;
                break;
            case R.drawable.mad_logo_17:
                lastYear = 3;
                break;
            case R.drawable.mad_logo_18:
                lastYear = 4;
                break;
            default:

        }

        picsFragment = new PicsFragment().newInstance(lastYear);
        getSupportFragmentManager().beginTransaction().replace(R.id.listFrame, listFragment).replace(R.id.pictureFrame, picsFragment).remove(fullPicFragment).commit();

    }
}
