package com.example.test1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test1.utils.FileTool;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.text_message);
    }

    public void checkPermission() {
        List<String> requestPermission = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_CONTACTS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
            }
        }
        //Manifest.permission.READ_CONTACTS
        requestPermission.add(Manifest.permission.WRITE_CONTACTS);
        if (!requestPermission.isEmpty()) {
            ActivityCompat.requestPermissions(this, (String[]) requestPermission.toArray(), 1);
        }
    }

    public void note1(View view) {
        Intent intent = new Intent(this, Note1.class);
        startActivity(intent);
    }

    public void note2(View view) {
        //Intent intent = new Intent(this, Note2.class);
        //startActivity(intent);

    }

    public void test1(View view) {
        //FileTool.internal(this,"text.txt");
        FileTool.list(this);
    }

    public boolean isExtStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public void show(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

}
