package service.android.google.com.accessibility.rx.observer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.controller.AccessibilityServiceController;

/**
 * Created by tim on 19.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatEventSubscriberTest {
    private ChatEventSubscriber chatEventSubscriber;

    @Mock
    private AccessibilityServiceController controller;

    @Test
    public void test_onCreate() throws Exception {
        chatEventSubscriber = new ChatEventSubscriber(controller);
    }
}