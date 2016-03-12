package service.android.google.com.accessibility.controller;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import rx.Subscriber;
import rx.subjects.PublishSubject;
import service.android.google.com.accessibility.model.ASEvent;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.rx.ObservableFactory;
import service.android.google.com.accessibility.rx.ObserverFactory;
import timber.log.Timber;

public class AccessibilityServiceControllerImpl implements AccessibilityServiceController {

    private static final String TAG = "TAG";
    private final PublishSubject<AccessibilityEvent> textEventObservable;
    private final PublishSubject<AccessibilityEvent> eventObservable;

    public AccessibilityServiceControllerImpl(final ObservableFactory observableFactory,
                                              final ObserverFactory observerFactory) {
        Subscriber<Event> eventObserver = observerFactory.createEventSubscriber(this);
        this.eventObservable = observableFactory.createPublishSubjectOfAccessibilityEvents(eventObserver);
        this.textEventObservable = observableFactory.createPublishSubjectOfAccessibilityTextEvents(eventObserver);
    }

    @Override
    public void evaluateEvent(final AccessibilityEvent accessibilityEvent) {
        int type = accessibilityEvent.getEventType();
        switch (type) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
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