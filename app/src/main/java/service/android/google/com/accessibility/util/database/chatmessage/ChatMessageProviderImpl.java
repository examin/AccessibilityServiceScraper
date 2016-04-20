package service.android.google.com.accessibility.util.database.chatmessage;

import java.util.List;

import nl.nl2312.rxcupboard.RxDatabase;
import rx.schedulers.Schedulers;
import service.android.google.com.accessibility.model.database.ChatMessageDTO;
import service.android.google.com.accessibility.util.database.AbstractProvider;

/**
 * Created by tim on 14.04.16.
 */
public class ChatMessageProviderImpl extends AbstractProvider implements ChatMessageProvider {

    public ChatMessageProviderImpl(final RxDatabase rxDatabase) {
        super(rxDatabase);
    }

    @Override
    public List<ChatMessageDTO> getAllChatMessages() {
        return rxDatabase.query(ChatMessageDTO.class)
                .toList()
                .subscribeOn(Schedulers.io())
                .toBlocking()
                .first();
    }
}