package service.android.google.com.accessibility.rx.observer;

import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.model.ChatEvent;

public class ChatEventSubscriber extends AbstractEventSubscriber<ChatEvent> {
    public ChatEventSubscriber(final AS accessibilityService) {
        super(accessibilityService);
    }
}