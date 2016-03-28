package service.android.google.com.accessibility.util.action;

import android.database.sqlite.SQLiteConstraintException;

import java.util.ArrayList;
import java.util.List;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.ChatMessage;
import service.android.google.com.accessibility.model.database.ChatMessageDTO;
import timber.log.Timber;

/**
 * Created by tim on 27.03.16.
 */
public class SaveChatEventToDbFunction extends AbstractSaveASEventToDbAction<ChatEvent, List<ChatMessageDTO>> {

    public SaveChatEventToDbFunction(final RxDatabase rxDatabase) {
        super(rxDatabase);
    }

    @Override
    public void call(final ChatEvent accessibilityEvent) {
        if (accessibilityEvent == null || accessibilityEvent.messages() == null) return;
        ArrayList<ChatMessageDTO> chatMessages = new ArrayList<>();
        for (ChatMessage chatMessage : accessibilityEvent.messages()) {
            chatMessages.add(new ChatMessageDTO(
                    chatMessage.messagesHash(),
                    chatMessage.person().fullName(),
                    chatMessage.text(),
                    chatMessage.date()
            ));
        }
        save(chatMessages);
    }

    @Override
    public void save(final List<ChatMessageDTO> mappedObject) {
        Timber.d("Saving chat messages to DB.");
        for (ChatMessageDTO chatMessage : mappedObject) {
            try {
                rxDatabase.put(chatMessage);
            } catch (SQLiteConstraintException e) {
                //Ignore. We don't want duplicate entries in DB.
                Timber.d("Ignoring duplicate chat message entry.");
            }
        }
    }
}