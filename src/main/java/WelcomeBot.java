import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class WelcomeBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        // 봇의 사용자 이름을 반환하세요
        return "@xanqus_bot";
    }

    @Override
    public String getBotToken() {
        // 봇의 토큰을 반환하세요
        return "6243957338:AAGmxOL8MwnBOXi5Lj4QjslET-fmLK4I-FU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("update: " + update);
        System.out.println("update.getMessage().getNewChatMembers(): " + update.getMessage().getNewChatMembers());
        if (update.getMessage().getNewChatMembers() != null) {
            long chat_id = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chat_id));
            message.setText("환영합니다!");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
        if (update.hasMessage() && update.getMessage().getText().equals("/sendBox")) {
            System.out.println("update.getMessage().getText(): " + update.getMessage().getText());
            long chat_id = update.getMessage().getChatId();
            System.out.println("The chat_id is: " + chat_id);

            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chat_id));

            // 인라인 키보드 버튼 생성
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
            List<InlineKeyboardButton> rowInline = new ArrayList<>();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("다른 그룹으로 이동");
            inlineKeyboardButton.setUrl("https://t.me/+DRo6RF-dzmQzNjdl");
            rowInline.add(inlineKeyboardButton);
            // 다른 그룹의 링크를 설정하세요. 위의 URL은 예시입니다.

            // 행에 버튼 추가
            rowsInline.add(rowInline);

            // 키보드에 행 추가
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);

            message.setText("버튼을 눌러 다른 그룹으로 이동하세요.");
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }


}