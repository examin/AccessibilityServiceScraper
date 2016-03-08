package service.android.google.com.accessibility.controller;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import rx.subjects.PublishSubject;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.rx.ObservableFactory;
import timber.log.Timber;

public class AccessibilityServiceControllerImpl implements AccessibilityServiceController {

    private static final String TAG = "TAG";
    private final PublishSubject<AccessibilityEvent> accessibilityEventPublishSubject;

    public AccessibilityServiceControllerImpl(final ObservableFactory observableFactory) {
        this.accessibilityEventPublishSubject = observableFactory.createPublishSubjectOfAccessibilityEvents(this);
    }

    @Override
    public void evaluateEvent(final AccessibilityEvent accessibilityEvent) {
        int type = accessibilityEvent.getEventType();
        switch (type) {
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                Timber.d("Event received in controller: " + accessibilityEvent.toString());
                accessibilityEventPublishSubject.onNext(accessibilityEvent);
                break;
            default:
                break;
        }
    }

    @Override
    public void evaluateEvent(Event event) {
        Log.d(TAG, event.toString());
    }

    @Override
    public void handleError(Throwable e) {
        Timber.e(e, "Handled Exception.");
    }
}