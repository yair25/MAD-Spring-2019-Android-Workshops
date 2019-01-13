package com.yairn.mad.madinfinitekittens;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class KittenItemHolder extends RecyclerView.ViewHolder {

    private ImageView kittenImageView;
    private ImageButton kittenItemFavoriteImageButton;
    private ImageButton kittenItemBrowserImageButton;

    private boolean isFavorite = false;

    public KittenItemHolder(View itemView) {
        super(itemView);

        kittenImageView = itemView.findViewById(R.id.kittenItemImageView);
        kittenItemFavoriteImageButton = itemView.findViewById(R.id.kittenItemFavoriteImageButton);
        kittenItemBrowserImageButton = itemView.findViewById(R.id.kittenItemBrowserImageButton);
    }

    public void setKittenImageView(final String url) {
        Picasso.get().load(url).fit().into(kittenImageView);
    }

    public void setKittenImageViewTap(final String url) {

        kittenImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), KittenImageActivity.class);
                intent.putExtra("kittenUrl", url);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                v.getContext().getApplicationContext().startActivity(intent);
            }
        });
    }

    public void setKittenFavoriteButton() {

        kittenItemFavoriteImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = !isFavorite;

                if(isFavorite) {
                    kittenItemFavoriteImageButton.setImageResource(R.drawable.ic_action_star_full);
                } else {
                    kittenItemFavoriteImageButton.setImageResource(R.drawable.ic_action_star_empty);
                }
            }
        });
    }

    public void setKittenBrowserButton(final String url) {

        kittenItemBrowserImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);
                browserIntent.setData(Uri.parse(url));
                browserIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                v.getContext().getApplicationContext().startActivity(browserIntent);
            }
        });
    }

}