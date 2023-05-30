package com.mealmastercookingrecipesapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {

    public static boolean FileExists(String fileName){
        File file = new File(fileName);
        return file.exists();
    }

    public static void SaveToFile(ArrayList<String> data, String fileName) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for(String line : data){
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> LoadFromFile(String fileName){
        ArrayList<String> data = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

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
