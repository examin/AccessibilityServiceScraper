package service.android.google.com.accessibility.util.database.chatmessage;

import java.util.List;

import service.android.google.com.accessibility.model.database.ChatMessageDTO;

/**
 * Created by tim on 14.04.16.
 */
public interface ChatMessageProvider {
    List<ChatMessageDTO> getAllChatMessages();
}
