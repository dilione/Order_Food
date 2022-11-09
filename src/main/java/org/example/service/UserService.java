package org.example.service;

import org.example.dataBase.DataBase;
import org.example.model.User;

public class UserService {
    User user = new User();
    public User saveUser(long chatId){
        for (User user1 : DataBase.USERS_LIST ) {
            if (user1.getChatId()==chatId){
                return user1;
            }

        }
        User user = new User();
        user.setChatId(chatId);
        DataBase.USERS_LIST.add(user);
        return user;
    }

}
