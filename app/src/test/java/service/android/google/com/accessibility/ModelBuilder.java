package service.android.google.com.accessibility;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.ChatMessage;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.model.Person;

/**
 * Created by tim on 19.03.16.
 */
public class ModelBuilder {

    private final static String MOCK_PACKAGE_NAME = "com.packagename.mock";

    public static Event createEvent() {
        return createBasicEventBuilder()
                .build();
    }

    public static Event.Builder createBasicEventBuilder() {
        return Event.builder()
                .eventType(AccessibilityEvent.TYPE_VIEW_CLICKED)
                .className("com.android.test")
                .packageName(MOCK_PACKAGE_NAME)
                .eventTime(123456)
                .text("text")
                .isEnabled(true)
                .isPassword(false)
                .isChecked(true);
    }

    public static ChatEvent createChatEvent() {
        return ChatEvent.builder()
                .packageName(MOCK_PACKAGE_NAME)
                .build();
    }

    public static ChatMessage createMessage() {
        return ChatMessage.builder()
                .person(createPerson())
                .text("text")
                .date(12345)
                .messagesHash("messageHash")
                .build();
    }

    public static Person createPerson() {
        return Person.builder()
                .fullName("John Doe")
                .build();
    }
}
