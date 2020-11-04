package com.example.simplegallery;

import android.app.Activity;
import android.app.WallpaperManager;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;

import java.io.File;
import java.io.IOException;

public class MoreActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.more);
        Intent intent = getIntent();
        final String image = intent.getStringExtra("imagePath");
        final Bitmap bitmap = BitmapFactory.decodeFile(image);
        Button btnFacebook = (Button) findViewById(R.id.btnFacebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeFile(image);
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                ShareDialog shareDialog;
                shareDialog = new ShareDialog(MoreActivity.this);
                shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
            }
        });

        Button btnChung = (Button) findViewById(R.id.btnChung);
        btnChung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(image);

                Uri contentUri = Uri.fromFile(f);
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
                shareIntent.setType("image/*");
                startActivity(Intent.createChooser(shareIntent, "Share images to.."));
            }
        });

        Button btnDelete = (Button) findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File f = new File(image);
//                if(f.delete()){
//                    System.out.println("Success");
//                } else {
//                    System.out.println("Fail");
//                }
                deleteImage(f);
            }

            private void deleteImage(File file) {
                // Set up the projection (we only need the ID)
                String[] projection = {MediaStore.Images.Media._ID};

                // Match on the file path
                String selection = MediaStore.Images.Media.DATA + " = ?";
                String[] selectionArgs = new String[]{file.getAbsolutePath()};

                // Query for the ID of the media matching the file path
                Uri queryUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = getContentResolver();
                Cursor c = contentResolver.query(queryUri, projection, selection, selectionArgs, null);
                if (c.moveToFirst()) {
                    // We found the ID. Deleting the item via the content provider will also remove the file
                    long id = c.getLong(c.getColumnIndexOrThrow(MediaStore.Images.Media._ID));
                    Uri deleteUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                    contentResolver.delete(deleteUri, null, null);
                    goBack();
                } else {
                    // File not found in media store DB
                }
                c.close();
            }
        });


        Button btnSetBackground = (Button) findViewById(R.id.btnSetBackground);
        btnSetBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWallpaper();
            }

            public void setWallpaper() {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(MoreActivity.this);

                try{
                    wallpaperManager.setBitmap(bitmap);

                    Toast.makeText(MoreActivity.this, "Success", Toast.LENGTH_LONG);
                }
                catch (IOException e){
                    Toast.makeText(MoreActivity.this, "Error", Toast.LENGTH_LONG);
                }

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

}