package service.android.google.com.accessibility.controller;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.subjects.PublishSubject;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 19.03.16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PublishSubject.class)
public class AccessibilityServiceControllerImplTest {

    private AccessibilityServiceControllerImpl accessibilityServiceController;

    @Mock
    private PublishSubject<AccessibilityEvent> eventObservable;
    @Mock
    private PublishSubject<AccessibilityEvent> textEventObservable;
    @Mock
    private PublishSubject<AccessibilityNodeInfo> chatEventObservable;

    @Mock
    private AccessibilityNodeInfo accessibilityNodeInfo;
    @Mock
    private AccessibilityEvent accessibilityEvent;

    @Before
    public void setUp() throws Exception {
        accessibilityServiceController = new AccessibilityServiceControllerImpl(
                textEventObservable,
                eventObservable,
                chatEventObservable
        );
    }

    @Test
    public void test_evaluateEvent_unSupportedEventShouldNotDoAnything() throws Exception {
        prepareAccessibilityEventForTest(AccessibilityEvent.TYPE_GESTURE_DETECTION_END);
        accessibilityServiceController.evaluateEvent(accessibilityNodeInfo, accessibilityEvent);
        verifyZeroInteractions(chatEventObservable, eventObservable, textEventObservable);
    }

    @Test
    public void test_evaluateEvent_TYPE_VIEW_CLICKED() throws Exception {
        prepareAccessibilityEventForTest(AccessibilityEvent.TYPE_VIEW_CLICKED);
        accessibilityServiceController.evaluateEvent(accessibilityNodeInfo, accessibilityEvent);
        verify(chatEventObservable).onNext(accessibilityNodeInfo);
        verify(eventObservable).onNext(accessibilityEvent);
        verifyZeroInteractions(textEventObservable);
    }

    @Test
    public void test_evaluateEvent_TYPE_VIEW_FOCUSED() throws Exception {
        prepareAccessibilityEventForTest(AccessibilityEvent.TYPE_VIEW_FOCUSED);
        accessibilityServiceController.evaluateEvent(accessibilityNodeInfo, accessibilityEvent);
        verify(chatEventObservable).onNext(accessibilityNodeInfo);
        verify(eventObservable).onNext(accessibilityEvent);
        verifyZeroInteractions(textEventObservable);
    }

    @Test
    public void test_evaluateEvent_TYPE_NOTIFICATION_STATE_CHANGED() throws Exception {
        prepareAccessibilityEventForTest(AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED);
        accessibilityServiceController.evaluateEvent(accessibilityNodeInfo, accessibilityEvent);
        verify(chatEventObservable).onNext(accessibilityNodeInfo);
        verify(eventObservable).onNext(accessibilityEvent);
        verifyZeroInteractions(textEventObservable);
    }

    @Test
    public void test_evaluateEvent_TYPE_VIEW_TEXT_CHANGED() throws Exception {
        prepareAccessibilityEventForTest(AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED);
        accessibilityServiceController.evaluateEvent(accessibilityNodeInfo, accessibilityEvent);
        verify(textEventObservable).onNext(accessibilityEvent);
        verifyZeroInteractions(chatEventObservable, eventObservable);
    }

    @Test
    public void test_evaluateEvent_TYPE_WINDOW_CONTENT_CHANGED() throws Exception {
        prepareAccessibilityEventForTest(AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED);
        accessibilityServiceController.evaluateEvent(accessibilityNodeInfo, accessibilityEvent);
        verify(chatEventObservable).onNext(accessibilityNodeInfo);
        verify(eventObservable).onNext(accessibilityEvent);
        verifyZeroInteractions(textEventObservable);
    }

    @Test
    public void test_evaluateEvent_TYPE_WINDOW_STATE_CHANGED() throws Exception {
        prepareAccessibilityEventForTest(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);
        accessibilityServiceController.evaluateEvent(accessibilityNodeInfo, accessibilityEvent);
        verify(chatEventObservable).onNext(accessibilityNodeInfo);
        verify(eventObservable).onNext(accessibilityEvent);
        verifyZeroInteractions(textEventObservable);
    }

    private void prepareAccessibilityEventForTest(int typeGestureDetectionEnd) {
        when(accessibilityEvent.getEventType()).thenReturn(typeGestureDetectionEnd);
    }
}