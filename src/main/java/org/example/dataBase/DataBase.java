package org.example.dataBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.Product;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class DataBase {
    public static List<User> users = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static File file = new File("file");
    public static File fileUser = new File(file.getName()+"/users.txt");
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static File fileFood = new File(file.getName()+"/fileFoodJson.txt");
    public static List<User> readFileUser(){
        if(file.isDirectory()&&fileUser.exists()){
            try {
                users = gson.fromJson(new FileReader(file.getName()+"/users.txt"), new TypeToken<List<User>>(){}.getType());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return users;

    }
    public static boolean writerJsonUser(List<User> users1){
        String str = gson.toJson(users1);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileUser))){
            writer.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
    public static List<Product> readFileFood(){
        if(file.isDirectory()&&fileFood.exists()){
            try {
                products = gson.fromJson(new FileReader(file.getName()+"/fileFoodJson.txt"),
                        new TypeToken<List<Product>>(){}.getType());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return products;

    }
    public static boolean writerJsonFood(List<Product> foods){
        String str = gson.toJson(foods);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileFood))){
            writer.write(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
