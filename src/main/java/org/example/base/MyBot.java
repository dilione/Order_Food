package org.example.base;

import org.example.service.ProductService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyBot extends TelegramLongPollingBot implements BaseBot {
    ProductService productService = new ProductService();

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    String step = "/start";

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            List<KeyboardRow> buttonRow = new ArrayList<>();
            String text = message.getText();
            if (message.hasText()) {
                step = BaseBot.START;
                if (text.equals(BaseBot.START)) {
                    myExecute(
                            replyKeyboardMarkup(List.of(BaseBot.BUYURTMA, BaseBot.BUYURTMALARIM, BaseBot.BIZHAQIMIZDA,
                                    BaseBot.FIKR, BaseBot.SOZLAMALAR), 2), null, "Welcome to bot  " + message.getChat().getFirstName(), chatId);

                } else if (text.equals(BaseBot.BUYURTMA)) {
                    myExecute(replyKeyboardMarkup(List.of(BaseBot.PLACE,BaseBot.FOODS), 1), null, "Tanlovni amalga oshiring", chatId);
                } else if (text.equals(BaseBot.PLACE)) {
                    URL url = null;
                    try {
                        url = new URL(URL_PLACE);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    sendPhoto("Joy band qilish","Bo'sh joylar",ORDER,EMPTY_PLACE,url,chatId);
                } else if (text.equals(BaseBot.FOODS)) {
                    URL url;
                    try {
                        url = new URL(URL_FOODS);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    }
                    sendPhoto("Milliy taomlar","FastFood","Ichimliklar","Non","Salatlar",MILLIY,
                            FAST,ICHIMLIK,NON,SALAT,url,chatId);
                }
            }
        } else if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getFrom().getId().toString();
            String data = update.getCallbackQuery().getData();
            if (data.equals(ORDER)){

            } else if (data.equals(EMPTY_PLACE)) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.setText("Bo'sh joy yo'q");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }

            } else if (data.equals(MILLIY)) {

            } else if (data.equals(FAST)) {

            } else if (data.equals(ICHIMLIK)) {

            } else if (data.equals(NON)) {

            } else if (data.equals(SALAT)) {

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
    private void sendPhoto(String txt,String txt2, String callBackData,String callBackData2, URL url, Long chatId) {
        InputStream inputStream;
        List<List<InlineKeyboardButton>> keys = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardButton1.setText(txt2);
        inlineKeyboardButton1.setCallbackData(callBackData2);
        inlineKeyboardButton.setText(txt);
        inlineKeyboardButton.setCallbackData(callBackData);
        rowInline.add(inlineKeyboardButton);
        rowInline.add(inlineKeyboardButton1);
        keys.add(rowInline);
        inlineKeyboardMarkup.setKeyboard(keys);
        try {
            inputStream = url.openStream();
            InputFile inputFile = new InputFile(inputStream, String.valueOf(url));
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setChatId(chatId.toString());
            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
            execute(sendPhoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void sendPhoto(String txt,String txt2,String txt3,String txt4,String txt5, String callBackData,String callBackData2,String callBackData3,String callBackData4,String callBackData5, URL url, Long chatId) {
        InputStream inputStream;
        List<List<InlineKeyboardButton>> keys = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline2 = new ArrayList<>();
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();

        inlineKeyboardButton.setText(txt);
        inlineKeyboardButton.setCallbackData(callBackData);
        inlineKeyboardButton1.setText(txt2);
        inlineKeyboardButton1.setCallbackData(callBackData2);
        inlineKeyboardButton2.setText(txt3);
        inlineKeyboardButton2.setCallbackData(callBackData3);
        inlineKeyboardButton3.setText(txt4);
        inlineKeyboardButton3.setCallbackData(callBackData4);
        inlineKeyboardButton4.setText(txt5);
        inlineKeyboardButton4.setCallbackData(callBackData5);
        rowInline.add(inlineKeyboardButton);
        rowInline.add(inlineKeyboardButton1);
        rowInline.add(inlineKeyboardButton2);
        rowInline2.add(inlineKeyboardButton3);
        rowInline2.add(inlineKeyboardButton4);
        keys.add(rowInline);
        keys.add(rowInline2);
        inlineKeyboardMarkup.setKeyboard(keys);
        try {
            inputStream = url.openStream();
            InputFile inputFile = new InputFile(inputStream, String.valueOf(url));
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setPhoto(inputFile);
            sendPhoto.setChatId(chatId.toString());
            sendPhoto.setReplyMarkup(inlineKeyboardMarkup);
            execute(sendPhoto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

