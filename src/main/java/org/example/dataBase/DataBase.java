package org.example.dataBase;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
    public  abstract class DataBase {
        public static List<User> USERS_LIST=new ArrayList<>();
        private static Gson gson=new GsonBuilder().setPrettyPrinting().create();
        public static void saveUserToDataBase(User user) throws IOException {

            File file = new File("main/recurs/user.json");
            if(file.createNewFile()){
                String str = "[" + gson.toJson(user) + "]"; // agar shunday save qilinmasa kegn oqiy olmaydi;
                fileWriter(file,str);
            }else{
                List<User> users = gson.fromJson(new FileReader(file),new TypeToken<List<User>>(){}.getType());
                users.add(user);
                fileWriter(file, gson.toJson(users));
            }
        }
        private static void fileWriter(File file,String str) throws IOException {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(str);
            bufferedWriter.close();
        }
        public static List<User> checkJsonFile(File file) throws FileNotFoundException {
            if(file.exists()){
                return gson.fromJson(new FileReader(file),new TypeToken<List<User>>(){}.getType());
            }
            else{
                return null;
            }
        }
        public static void readJsonUsersFilesIfExist(File file){
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                USERS_LIST = gson.fromJson(bufferedReader,new TypeToken<List<User>>(){}.getType());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
