import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.meta.generics.TelegramBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Calendar;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        // Create a TelegramBotsApi object to register our bot
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

        // Register our bot
        try {
            botsApi.registerBot(new WelcomeBot());
            System.out.println("Bot started successfully!");
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        GroupLinkBot bot = new GroupLinkBot();

        Calendar date = Calendar.getInstance();
        long currentTime = date.getTimeInMillis();
        date.set(Calendar.HOUR_OF_DAY, 0);
        date.set(Calendar.MINUTE, 8);
        date.set(Calendar.SECOND, 50);
        if (date.getTimeInMillis() < currentTime) {
            date.add(Calendar.DATE, 1);
        }
        long startDelay = date.getTimeInMillis() - currentTime;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(bot::sendMessageToUser, startDelay, TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS), TimeUnit.MILLISECONDS);
    }
}