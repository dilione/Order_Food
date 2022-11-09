package org.example.base;

import org.example.model.User;
import org.example.service.UserService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyBot extends TelegramLongPollingBot implements BaseBot {
 UserService userService = new UserService();
    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }



    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
                User user=userService.saveUser(chatId);
            String text = message.getText();
            if (message.hasText()) {
                if (text.equals(BaseBot.START)) {
                    myExecute(
                            replyKeyboardMarkup(List.of(BaseBot.BUYURTMA, BaseBot.BUYURTMALARIM, BaseBot.BIZHAQIMIZDA,
                                    BaseBot.FIKR, BaseBot.SOZLAMALAR), 2), null, "Welcome to bot  " + message.getChat().getFirstName(), chatId);
                      user.setStep(BaseBot.BUYURTMA);
                } else if (user.getStep().equals(BaseBot.BUYURTMA)) {
                    myExecute(replyKeyboardMarkup(List.of(BaseBot.PLACE,BaseBot.FOODS), 1), null, "Tanlovni amalga oshiring", chatId);
                } else if (text.equals(BaseBot.BUYURTMALARIM)) {

                }
            }
        }
    }

    private ReplyKeyboardMarkup replyKeyboardMarkup(List<String> menuItems, int n) {
        ReplyKeyboardMarkup r = new ReplyKeyboardMarkup();
        r.setResizeKeyboard(true);
        List<KeyboardRow> buttonRow = new ArrayList<>();
        KeyboardRow keyboardButtons = new KeyboardRow();
        for (int i = 0; i < menuItems.size(); i++) {
            keyboardButtons.add(new KeyboardButton(menuItems.get(i)));
            if (i % n == 0) {
                buttonRow.add(keyboardButtons);
                keyboardButtons = new KeyboardRow();
            }
        }
        if (keyboardButtons.size() > 0) {
            buttonRow.add(keyboardButtons);
        }
        r.setKeyboard(buttonRow);
        return r;
    }


    private void myExecute(ReplyKeyboardMarkup r, InlineKeyboardMarkup i, String text, Long chatId) {
        SendMessage sendMessage = new SendMessage();

            sendMessage.setText(text);

        sendMessage.setChatId(chatId.toString());
        sendMessage.setReplyMarkup(r == null ? i : r);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private String getKey(Map<String, InlineKeyboardMarkup> map) {
        for (Map.Entry<String, InlineKeyboardMarkup> pair : map.entrySet()) {
            return pair.getKey();
        }
        return null;
    }

}

