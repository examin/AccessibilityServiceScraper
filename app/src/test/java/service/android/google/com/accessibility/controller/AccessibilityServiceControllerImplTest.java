package service.android.google.com.accessibility.controller;

import android.content.res.Resources;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.github.pwittchen.prefser.library.Prefser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;
import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.rx.ObservableFactory;
import service.android.google.com.accessibility.upload.UploaderHelper;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 19.03.16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({PublishSubject.class, CompositeSubscription.class})
public class AccessibilityServiceControllerImplTest {

    private AccessibilityServiceControllerImpl accessibilityServiceController;

    @Mock
    private PublishSubject<AccessibilityEvent> eventObservable;
    @Mock
    private PublishSubject<AccessibilityEvent> textEventObservable;
    @Mock
    private PublishSubject<AccessibilityNodeInfo> chatEventObservable;
    @Mock
    private PublishSubject<AccessibilityEvent> notificationEventObservable;
    @Mock
    private Prefser prefser;
    @Mock
    private Resources resources;
    @Mock
    private CompositeSubscription compositeSubscription;

    @Mock
    private AccessibilityNodeInfo accessibilityNodeInfo;
    @Mock
    private AccessibilityEvent accessibilityEvent;
    @Mock
    private ObservableFactory observableFactory;
    @Mock
    private rx.Subscription preferenceSubscription;
    @Mock
    private UploaderHelper uploaderHelper;

    @Before
    public void setUp() throws Exception {
        when(prefser.get("EVENT_GENERAL", Boolean.class, false)).thenReturn(true);
        when(prefser.get("CHAT_EVENT", Boolean.class, false)).thenReturn(true);
        when(prefser.get("TEXT_EVENT", Boolean.class, false)).thenReturn(true);

        when(resources.getString(R.string.pref_key_event_general)).thenReturn("EVENT_GENERAL");
        when(resources.getString(R.string.pref_key_chat_event)).thenReturn("CHAT_EVENT");
        when(resources.getString(R.string.pref_key_event_text)).thenReturn("TEXT_EVENT");

        accessibilityServiceController = new AccessibilityServiceControllerImpl(
                textEventObservable,
                eventObservable,
                chatEventObservable,
                notificationEventObservable,
                prefser,
                resources,
                compositeSubscription,
                uploaderHelper
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
        verify(notificationEventObservable).onNext(accessibilityEvent);
        verifyZeroInteractions(eventObservable, textEventObservable, chatEventObservable);
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

    @Test
    public void test_unSubscribe() throws Exception {
        accessibilityServiceController.unSubscribe();
        verify(compositeSubscription).unsubscribe();
    }

    private void prepareAccessibilityEventForTest(int typeGestureDetectionEnd) {
        when(accessibilityEvent.getEventType()).thenReturn(typeGestureDetectionEnd);
    }
}