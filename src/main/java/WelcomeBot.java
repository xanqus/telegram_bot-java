import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class WelcomeBot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        // Return your bot's token
        return "6243957338:AAGmxOL8MwnBOXi5Lj4QjslET-fmLK4I-FU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("updated");
        System.out.println("update.hasMessage(): " + update.hasMessage());
        System.out.println("update.getMessage().getNewChatMembers(): " + update.getMessage());
        sendWelcomeMessage(-1001825125325L, "테스트유저");
    }

    private void sendWelcomeMessage(Long chatId, String userName) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText("Welcome, " + userName + "!");

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        // Return your bot's username
        return "@xanqus_bot";
    }
}
