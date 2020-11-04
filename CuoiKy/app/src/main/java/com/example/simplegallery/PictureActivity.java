package com.example.simplegallery;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
        final String image = intent.getStringExtra("image_uri");
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

        Button btnMore = (Button) findViewById(R.id.btnMore);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moreIntent = new Intent(PictureActivity.this, MoreActivity.class);
                moreIntent.putExtra("imagePath", image);
                startActivityForResult(moreIntent, 400);
            }
        });
    }

    @Override
    public void finish() {
        // Prepare data intent
        Intent data = new Intent();

        data.putExtra("deleted", true);

        // Activity finished ok, return the data
        this.setResult(Activity.RESULT_OK, data);
        super.finish();
    }


    // The method is called when the user clicks the "Back" button.
    public void goBack()  {
        // Calling onBackPressed() method to back to the previous Activity.
        this.onBackPressed();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 400) {
            boolean deleted = data.getBooleanExtra("deleted", false);
            if(deleted){
                goBack();
            }
        } else {
            Log.e("Error: ", "not found");
        }
    }
}
