package com.example.test1.utils;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.test1.MainActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Iterator;
import java.util.stream.Stream;

public class FileTool {

    public static void list(MainActivity mainActivity) {
        String dirName = mainActivity.getFilesDir().toString();
        try {
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.O) {
                return;
            }
            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N) {
                return;
            }
            Stream<Path> paths = Files.list(new File(dirName).toPath());
            for (Iterator<Path> iterator = paths.iterator(); iterator.hasNext(); ) {
                System.out.println(iterator.next().toString());
            }
        } catch (IOException ioe) {
        }
    }

    public static void internal(MainActivity mainActivity, String path) {
        File dir = mainActivity.getFilesDir();
        File outFile = new File(dir, path);
        writeToFile(outFile, "Hello! 大家好");
        // or
        //writeToFile(mainActivity, path, "Hello! 大家好");
        dir = mainActivity.getFilesDir();
        File inFile = new File(dir, path);
        String data = readFromFile(inFile);
        System.out.println(data);
        //or
        //openFileInput(path);
    }

    public static void external(MainActivity mainActivity) {
//        if (isExtStorageWritable()) {
//            File dir = this.getExternalFilesDir(null);
//            File outFile = new File(dir, "test.txt");
//            textView.setText(outFile.toString());
//        }
    }

    public static boolean writeToFile(MainActivity mainActivity, String path, String context) {
        try (FileOutputStream out = mainActivity.openFileOutput(path, Context.MODE_PRIVATE);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));) {
            writer.write(context);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean writeToFile(File file, String context) {
        try (FileOutputStream out = new FileOutputStream(file);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));) {
            writer.write(context);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static String readFromFile(File file) {
        StringBuilder content = new StringBuilder();
        try (FileInputStream in = new FileInputStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
            String line = " ";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
        }
        return content.toString();
    }


}
