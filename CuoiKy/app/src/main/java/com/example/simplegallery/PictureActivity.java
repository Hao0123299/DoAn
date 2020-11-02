package com.example.simplegallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.io.File;

public class PictureActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.picture);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Intent intent = getIntent();
        String image = intent.getStringExtra("image_uri");
        ImageView picturesView;
        picturesView = (ImageView) findViewById(R.id.picture);
            picturesView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        System.out.print(image);
        picturesView.setMaxHeight(height - 80);
        picturesView.setMinimumHeight(height - 80);
        Glide.with(this).load(image)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(picturesView);
    }
}
