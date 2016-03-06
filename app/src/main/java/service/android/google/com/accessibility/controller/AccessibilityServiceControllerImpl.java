package service.android.google.com.accessibility.controller;

import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import java.util.concurrent.TimeUnit;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class AccessibilityServiceControllerImpl implements AccessibilityServiceController {
    private static final String TAG = "TAG";
    private final PublishSubject<AccessibilityEvent> accessibilityEventPublishSubject = PublishSubject.create();

    public AccessibilityServiceControllerImpl() {
        accessibilityEventPublishSubject
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccessibilityEvent>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AccessibilityEvent accessibilityEvent) {
                        Log.d(TAG, accessibilityEvent.toString());
                    }
                });
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
}