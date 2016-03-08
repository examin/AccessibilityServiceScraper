package service.android.google.com.accessibility.rx.observer;

import rx.Subscriber;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 08.03.16.
 */
public class EventSubscriber extends Subscriber<Event> {

    private final AccessibilityServiceController accessibilityServiceController;

    public EventSubscriber(final AccessibilityServiceController accessibilityServiceController) {
        this.accessibilityServiceController = accessibilityServiceController;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        accessibilityServiceController.handleError(e);
    }

    @Override
    public void onNext(Event event) {
        accessibilityServiceController.evaluateEvent(event);
    }
}
