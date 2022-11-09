package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.example.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.example.dataBase.DataBase.*;

public class UserService {



    public boolean addUser(User user){
        file.mkdirs();
        try {
            fileUser.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        users = readFileUser();
        if(users == null){
            List<User> userTemp = new ArrayList<>();
            userTemp.add(user);
            users = userTemp;
            writerJsonUser(users);
            return true;
        }
        for (User user1:users) {
            if(user1.getChatId()==user.getChatId()){
                return false;
            }
        }
        users.add(user);
        writerJsonUser(users);
        return true;
    }


}
