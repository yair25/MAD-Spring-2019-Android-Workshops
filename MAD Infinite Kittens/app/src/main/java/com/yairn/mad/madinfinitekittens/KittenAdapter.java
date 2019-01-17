package com.yairn.mad.madinfinitekittens;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class KittenAdapter extends RecyclerView.Adapter<KittenItemHolder> {

    private int size;

    /*
     * THe constructor of the adapter requires the number of items in it
     */
    public KittenAdapter(int size) {
        this.size = size;
    }

    /*
     * The KittenItemHolder method inflates/creates the Kitten Items based on the kitten_item_card layout
     */
    @Override
    public KittenItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View kittenView = layoutInflater.inflate(R.layout.kitten_item_card, parent, false);
        return new KittenItemHolder(kittenView);
    }

    /*
     * The onBindViewHolder method, binds the position of the item in the RecycleView with its own data.
     */
    @Override
    public void onBindViewHolder(@NonNull KittenItemHolder holder, int position) {
        String url1 = "https://images.unsplash.com/photo-1529933037705-0d537317ae7b?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=2524b7a67224f19e9a89dfbd33365c39&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb";
        String url2 = "https://images.unsplash.com/photo-1524213216828-963a6a3cf9d0?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=537c9ace277bdad3ed5dfa73fcb60df1&dpr=1&auto=format&fit=crop&w=1000&q=80&cs=tinysrgb";

        String kittenUrl = position % 2  == 0 ? url1 : url2;

        holder.setKittenImageView(kittenUrl);
        holder.setKittenImageViewTap(kittenUrl);
        holder.setKittenFavoriteButton();
        holder.setKittenBrowserButton(kittenUrl);
    }

    /* The getItemCount returns the number of items in the Adapter */
    @Override
    public int getItemCount() {
        return size;
    }
}
