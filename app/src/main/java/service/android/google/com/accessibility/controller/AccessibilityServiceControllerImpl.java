package service.android.google.com.accessibility.controller;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import rx.Subscriber;
import rx.subjects.PublishSubject;
import service.android.google.com.accessibility.model.ASEvent;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.rx.ObservableFactory;
import service.android.google.com.accessibility.rx.ObserverFactory;
import service.android.google.com.accessibility.util.extractor.EventExtractor;
import service.android.google.com.accessibility.util.ripper.WindowRipper;
import service.android.google.com.accessibility.util.test.TestOnly;
import timber.log.Timber;

import static service.android.google.com.accessibility.model.PackageConstants.TAG;

public class AccessibilityServiceControllerImpl implements AccessibilityServiceController {

    private final PublishSubject<AccessibilityEvent> textEventObservable;
    private final PublishSubject<AccessibilityEvent> eventObservable;
    private final PublishSubject<AccessibilityNodeInfo> chatEventObservable;

    public AccessibilityServiceControllerImpl(final ObservableFactory observableFactory,
                                              final ObserverFactory observerFactory,
                                              final EventExtractor eventExtractor,
                                              final WindowRipper windowRipper) {
        Subscriber<Event> eventObserver = observerFactory.createEventSubscriber(this);
        Subscriber<ChatEvent> chatEventSubscriber = observerFactory.createWindowInfoEventSubscriber(this);

        this.eventObservable = observableFactory.createPublishSubjectOfAccessibilityEvents(eventExtractor, eventObserver);
        this.textEventObservable = observableFactory.createPublishSubjectOfAccessibilityTextEvents(eventExtractor, eventObserver);
        this.chatEventObservable = observableFactory.createPublishSubjectOfAccessibilityNodeInfo(windowRipper, chatEventSubscriber);
    }

    @TestOnly
    protected AccessibilityServiceControllerImpl(final PublishSubject<AccessibilityEvent> textEventObservable,
                                                 final PublishSubject<AccessibilityEvent> eventObservable,
                                                 final PublishSubject<AccessibilityNodeInfo> nodeInfoPublishSubject) {
        this.textEventObservable = textEventObservable;
        this.eventObservable = eventObservable;
        this.chatEventObservable = nodeInfoPublishSubject;
    }

    @Override
    public void evaluateEvent(final AccessibilityNodeInfo rootInActiveWindow,
                              final AccessibilityEvent accessibilityEvent) {
        final int type = accessibilityEvent.getEventType();
        switch (type) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                chatEventObservable.onNext(rootInActiveWindow);
                eventObservable.onNext(accessibilityEvent);
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                textEventObservable.onNext(accessibilityEvent);
                break;
            default:
                break;
        }
    }

    @Override
    public void evaluateEvent(final ASEvent event) {
        Log.d(TAG, event.toString());
    }

    @Override
    public void handleError(final Throwable e) {
        Timber.e(e, "Handled Exception.");
    }
}