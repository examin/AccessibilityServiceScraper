package service.android.google.com.accessibility.rx.observer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.AS;

/**
 * Created by Tim Rijckaert on 1/05/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class NotificationEventObserverTest {

    private NotificationEventObserver notificationEventObserver;

    @Mock
    private AS accessibilityService;

    @Test
    public void test_onCreate() throws Exception {
        notificationEventObserver = new NotificationEventObserver(accessibilityService);
    }
}