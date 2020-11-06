package com.example.simplegallery;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.simplegallery.MainActivity.CAMERA_PERM_CODE;
import static com.example.simplegallery.MainActivity.DETAIL_CODE;

public class Information extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        Intent intent = getIntent();
        String imagePath = intent.getStringExtra("imagePath");
        File f = new File(imagePath);
        String imageFileName = f.getName();
        f.getTotalSpace();//lay size
        Date lastModDate = new Date(f.lastModified()); //lay ngay
        TextView txtDate = (TextView) findViewById(R.id.txtDate);
        TextView txtPath = (TextView) findViewById(R.id.txtPath);
        TextView txtFileName = (TextView) findViewById(R.id.txtFileName);

        txtDate.setText(lastModDate.toString());
        txtPath.setText(imagePath);
        txtFileName.setText(imageFileName);
    }
}
