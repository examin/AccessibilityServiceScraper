package service.android.google.com.accessibility.rx.observer;

import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.model.ChatEvent;

public class ChatEventSubscriber extends AbstractEventSubscriber<ChatEvent> {
    public ChatEventSubscriber(AccessibilityServiceController controller) {
        super(controller);
    }
}