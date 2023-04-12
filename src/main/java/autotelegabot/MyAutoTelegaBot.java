package autotelegabot;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.SendDocument;
import com.pengrad.telegrambot.request.SendMessage;

import java.io.File;
import java.time.LocalDate;


public class MyAutoTelegaBot {


    public static void main(String[] args) {

        TelegramBot bot = new TelegramBot(new BotKey().getKey());
        KeyboardButton kb1 = new KeyboardButton("Вопрос");
        KeyboardButton kb2 = new KeyboardButton("Ответ");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup(kb1,kb2);
        keyboardMarkup.oneTimeKeyboard(true);

        LocalDate date = LocalDate.now().minusDays(1);
        int dayOfMonth = date.getDayOfMonth();
        int year = date.getYear();
        int month = date.getMonthValue();
        String dataFormat = String.format("Отчет за %d.%d.%d \n", dayOfMonth, month, year);


        bot.setUpdatesListener(element-> {
            System.out.println(element);
            element.forEach(it -> {
              System.out.println(element.size() );
             bot.execute(new SendMessage(it.message().chat().id(), "Ну-да, привет!"));
             bot.execute(new SendMessage(it.message().chat().id(), logicBots.BotResponse(it.message().text())).replyMarkup(keyboardMarkup));


            });

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
        SendMessage request = new SendMessage(971823758, "Текст сообщения в чат!");
        bot.execute(request);
        SendDocument request1 = new SendDocument(971823758, new File(new PathDocument().getPathDocument()));
        request1.caption(dataFormat);
        bot.execute(request1);



    }

    }
