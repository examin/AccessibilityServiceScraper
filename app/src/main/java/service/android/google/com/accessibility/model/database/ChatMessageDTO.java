package service.android.google.com.accessibility.model.database;

import nl.qbusict.cupboard.annotation.Index;

/**
 * Created by tim on 27.03.16.
 */
public class ChatMessageDTO {

    @Index(unique = true)
    private String messagesHash;
    private String fullName;
    private String text;
    private long date;

    public ChatMessageDTO(String messagesHash, String fullName, String text, long date) {
        this.messagesHash = messagesHash;
        this.fullName = fullName;
        this.text = text;
        this.date = date;
    }
}