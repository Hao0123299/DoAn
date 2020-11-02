package com.example.simplegallery;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ImageAdapterCustom extends BaseAdapter {
    /** The context. */
    private Activity context;
    private ArrayList<String> images;
    private ArrayList<String> al_images;


    /**
     * Instantiates a new image adapter.
     *
     * @param localContext
     *            the local context
     */
    public ImageAdapterCustom(Activity localContext, ArrayList<String> _al_images) {
        context = localContext;
        al_images = _al_images;
        images = getAllShownImagesPath(context);
    }

    public int getCount() {
        return images.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView,
                        ViewGroup parent) {
        ImageView picturesView;
        if (convertView == null) {
            picturesView = new ImageView(context);
            picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            picturesView
                    .setLayoutParams(new GridView.LayoutParams(350, 350));

        } else {
            picturesView = (ImageView) convertView;
        }

        Glide.with(context).load(images.get(position))
                .placeholder(R.drawable.ic_launcher_background).centerCrop()
                .into(picturesView);

        return picturesView;
    }

    /**
     * Getting All Images Path.
     *
     * @param activity
     *            the activity
     * @return ArrayList with images Path
     */
    private ArrayList<String> getAllShownImagesPath(Activity activity) {
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        String absolutePathOfImage = null;

        for (int i = 0; i < al_images.size(); i++) {
            absolutePathOfImage = al_images.get(i);
            listOfAllImages.add(absolutePathOfImage);
        }
        return listOfAllImages;
    }
}
