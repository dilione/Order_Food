package org.example;

import org.example.base.MyBot;
import org.example.dataBase.DataBase;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        File file=new File("main/recurs/user/UsersJson.json");
        if (file.exists()){
            DataBase.readJsonUsersFilesIfExist(file);
        }
        TelegramBotsApi telegramBotsApi=new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyBot());
    }
}