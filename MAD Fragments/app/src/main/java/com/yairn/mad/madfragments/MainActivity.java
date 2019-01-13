package com.yairn.mad.madfragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ListFragment.OnListFragmentInteractionListener,
        PicsFragment.OnPicsFragmentInteractionListener, FullPicFragment.OnFullImageFragmentInteractionListener {

    private ListFragment listFragment;
    private PicsFragment picsFragment;

    private FullPicFragment fullPicFragment;


    private int lastYear = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listFragment = new ListFragment();
        picsFragment = new PicsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.listFrame, listFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.pictureFrame, picsFragment).commit();

        fullPicFragment = null;
    }



    public void onListFragmentInteraction(int year) {
        lastYear = year;
        picsFragment.setPic(year);
    }

    public void onPicsFragmentInteraction(int id) {
        fullPicFragment = new FullPicFragment().newInstance(id);
        getSupportFragmentManager().beginTransaction().replace(R.id.FullImageFrame, fullPicFragment).remove(listFragment).remove(picsFragment).commit();
    }

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
