package service.android.google.com.accessibility.rx.observer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.Event;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * Created by tim on 19.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractEventSubscriberTest {

    private MockEventSubscriber mockEventSubscriber;

    @Mock
    private AS accessibilityService;

    @Before
    public void setUp() throws Exception {
        mockEventSubscriber = new MockEventSubscriber(accessibilityService);
    }

    @Test
    public void test_onNext() throws Exception {
        final Event event = ModelBuilder.createEvent();
        mockEventSubscriber.onNext(event);
        verify(accessibilityService).evaluateEvent(event);
    }

    @Test
    public void test_onComplete() throws Exception {
        mockEventSubscriber.onCompleted();
        verifyZeroInteractions(accessibilityService);
    }

    @Test
    public void test_onError() throws Exception {
        final Exception mockception = new Exception("Mockception");
        mockEventSubscriber.onError(mockception);
        verify(accessibilityService).handleError(mockception);
    }

    class MockEventSubscriber extends AbstractEventSubscriber<Event> {
        public MockEventSubscriber(AS accessibilityService) {
            super(accessibilityService);
        }
    }
}