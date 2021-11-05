package autotelegabot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;


public class MyAutoTelegaBot {


    public static void main(String[] args) {
        //System.out.println("Hello AutoTelegaBot!");

        TelegramBot bot = new TelegramBot("2131740608:AAFYlF1Ysm8XRaBIkPjpoc3wc7dM8qGVWAA");




        bot.setUpdatesListener(element -> {
            //System.out.println(element);
            element.forEach(it -> {
              //System.out.println(element.size());
             //bot.execute(new SendMessage(it.message().chat().id(), "Ну-да, привет!"));
             bot.execute(new SendMessage(it.message().chat().id(), logicBots.BotResponse(it.message().text())));

            });


            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });




    }
}
