package com.mealmastercookingrecipesapp.Controller;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileHandler {
    private final Context context;

    public FileHandler(Context context){
        this.context = context;
    }

    public boolean FileExists(String fileName){
        File file = new File(context.getFilesDir(), fileName);
        return file.exists();
    }

    public void SaveToFile(ArrayList<String> data, String fileName) {
        try{
            FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));

            for(String line : data){
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> LoadFromFile(String fileName){
        ArrayList<String> data = new ArrayList<>();
        try {
            FileInputStream fis = context.openFileInput(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while((line = reader.readLine()) != null) {
                data.add(line);
            }

            reader.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return data;
    }

    public static String GetFileLocation(String fileName) {
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

}
