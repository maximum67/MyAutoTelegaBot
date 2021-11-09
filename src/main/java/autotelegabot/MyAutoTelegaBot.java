package autotelegabot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendMessage;

public class MyAutoTelegaBot {


    public static void main(String[] args) {

        BotKey botKeyToken = new BotKey();

        TelegramBot bot = new TelegramBot(botKeyToken.getKey());

        bot.setUpdatesListener(element -> {
            System.out.println(element);

            element.forEach(it -> {
                //bot.execute(new SendMessage(it.message().chat().id(),"Здравствуйте, "+it.message().chat().firstName()));
               SendMessage sendMessage = new SendMessage(it.message().chat().id(), logicBots.BotResponse(it.message().text()));
                bot.execute(sendMessage);


            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;

        });



    }


 }

