package com.example.simplegallery;

import java.util.ArrayList;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> images = new ArrayList<>();
    boolean boolean_folder;
    GridView gv_folder;
    private static final int REQUEST_PERMISSIONS = 100;
    private static final int REQUEST_CODE_IMAGE = 400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gv_folder = (GridView) findViewById(R.id.galleryGridView);
        final Intent intent = new Intent(MainActivity.this, PictureActivity.class);

        gv_folder.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                if (null != images && !images.isEmpty())
                    intent.putExtra("image_uri", images.get(position));
                    startActivityForResult(intent, REQUEST_CODE_IMAGE);
                ;

            }
        });

        if ((ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            if ((ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) && (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE))) {

            } else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }
        } else {
            Log.e("Else","Else");
            fn_imagespath();
            gv_folder.setAdapter(new ImageAdapterCustom(this, images));
            setCurrentImage();
        }
    }

    public void setCurrentImage(){
        final ImageView image1, image2, image3;

        final Intent intent = new Intent(MainActivity.this, PictureActivity.class);
        if(images.size() >= 1){
            image1 = (ImageView) findViewById(R.id.picture1);

            Glide.with(this).load(images.get(0))
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(image1);

            image1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(images != null || !images.isEmpty()){
                        intent.putExtra("image_uri", images.get(0));
                        startActivityForResult(intent, REQUEST_CODE_IMAGE);
                    }
                }
            });
        }

        if(images.size() >= 2){
            image2 = (ImageView) findViewById(R.id.picture2);

            Glide.with(this).load(images.get(1))
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(image2);

            image2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(images != null || !images.isEmpty()){
                        intent.putExtra("image_uri", images.get(1));
                        startActivityForResult(intent, REQUEST_CODE_IMAGE);
                    }
                }
            });
        }
        if(images.size() >= 3){
            image3 = (ImageView) findViewById(R.id.picture3);

            Glide.with(this).load(images.get(2))
                    .centerCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(image3);
            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(images != null || !images.isEmpty()){
                        intent.putExtra("image_uri", images.get(2));
                        startActivityForResult(intent, REQUEST_CODE_IMAGE);
                    }
                }
            });
        }

    }

    public ArrayList<String> fn_imagespath() {
        images.clear();

        Uri uri;
        Cursor cursor;
        int column_index_data;

        String absolutePathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.Images.Media.BUCKET_DISPLAY_NAME};
        final String orderBy = MediaStore.Images.Media.DATE_TAKEN;

        cursor = getApplicationContext().getContentResolver().query(uri, projection, null, null, orderBy + " DESC");

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            images.add(absolutePathOfImage);
        }

        return images;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults.length > 0 && grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        fn_imagespath();
                        gv_folder.setAdapter(new ImageAdapterCustom(this, images));
                        setCurrentImage();
                    } else {
                        Toast.makeText(MainActivity.this, "The app was not allowed to read or write to your storage. Hence, it cannot function properly. Please consider granting it this permission", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_IMAGE) {
            boolean deleted = data.getBooleanExtra("deleted", false);
            if(deleted == true){
                fn_imagespath();
                gv_folder = (GridView) findViewById(R.id.galleryGridView);
                gv_folder.setAdapter(new ImageAdapterCustom(this, images));
                setCurrentImage();
            }
        } else {
            Log.e("Error: ", "not found");
        }
    }
}