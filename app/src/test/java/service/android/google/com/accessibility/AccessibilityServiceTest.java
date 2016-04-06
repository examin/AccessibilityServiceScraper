package service.android.google.com.accessibility;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.model.Event;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 19.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccessibilityServiceTest {

    private AccessibilityService accessibilityService;

    @Mock
    private AccessibilityServiceController accessibilityServiceController;
    @Mock
    private android.view.accessibility.AccessibilityNodeInfo activeWindowNodeInfo;
    @Mock
    private AccessibilityEvent accessibilityEvent;

    @Before
    public void setUp() throws Exception {
        accessibilityService = spy(new AccessibilityService());
        accessibilityService.controller = accessibilityServiceController;
    }

    @Test
    public void test_onAccessibilityEvent() throws Exception {
        when(accessibilityService.getRootInActiveWindow()).thenReturn(activeWindowNodeInfo);
        ArgumentCaptor<AccessibilityEvent> accessibilityEventArgumentCaptor = ArgumentCaptor.forClass(AccessibilityEvent.class);
        accessibilityService.onAccessibilityEvent(accessibilityEvent);
        verify(accessibilityServiceController).evaluateEvent(eq(activeWindowNodeInfo), accessibilityEventArgumentCaptor.capture());
        assertThat(accessibilityEventArgumentCaptor.getValue(), is(accessibilityEvent));
    }

    @Test
    public void test_evaluateEvent() throws Exception {
        final Event event = ModelBuilder.createEvent();
        accessibilityService.evaluateEvent(event);
        verify(accessibilityServiceController).evaluateEvent(event);
    }

    @Test
    public void test_handleError() throws Exception {
        final Exception e = new Exception("MockException");
        accessibilityService.handleError(e);
        verify(accessibilityServiceController).handleError(e);
    }

    @Test
    public void test_eventTrackingChanged() throws Exception {
        accessibilityService.eventTrackingChanged(true);
        verify(accessibilityServiceController).toggleEventTracking(true);
    }

    @Test
    public void test_textEventTrackingChanged() throws Exception {
        accessibilityService.textEventTrackingChanged(true);
        verify(accessibilityServiceController).toggleTextEventTracking(true);
    }

    @Test
    public void test_chatEventTrackingChanged() throws Exception {
        accessibilityService.chatEventTrackingChanged(true);
        verify(accessibilityServiceController).toggleChatEventTracking(true);
    }

    @Test
    public void test_onDestroy() throws Exception {
        accessibilityService.onDestroy();
        verify(accessibilityServiceController).unSubscribe();
    }
}