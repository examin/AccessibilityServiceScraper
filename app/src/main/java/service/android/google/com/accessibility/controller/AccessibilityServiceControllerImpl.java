package service.android.google.com.accessibility.controller;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import rx.subjects.PublishSubject;
import service.android.google.com.accessibility.model.ASEvent;
import service.android.google.com.accessibility.rx.ObservableFactory;
import service.android.google.com.accessibility.util.test.TestOnly;
import timber.log.Timber;

public class AccessibilityServiceControllerImpl implements AccessibilityServiceController {

    private final PublishSubject<AccessibilityEvent> textEventObservable;
    private final PublishSubject<AccessibilityEvent> eventObservable;
    private final PublishSubject<AccessibilityNodeInfo> chatEventObservable;

    public AccessibilityServiceControllerImpl(final ObservableFactory observableFactory) {
        this.eventObservable = observableFactory.createPublishSubjectOfAccessibilityEvents();
        this.textEventObservable = observableFactory.createPublishSubjectOfAccessibilityTextEvents();
        this.chatEventObservable = observableFactory.createPublishSubjectOfAccessibilityNodeInfo();
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
        Timber.d("Event received in controller: ", event.toString());
    }

    @Override
    public void handleError(final Throwable e) {
        Timber.e(e, "Handled Exception.");
    }
}