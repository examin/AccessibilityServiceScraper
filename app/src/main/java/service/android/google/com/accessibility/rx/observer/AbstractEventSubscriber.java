package service.android.google.com.accessibility.rx.observer;

import rx.Subscriber;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.model.ASEvent;

public abstract class AbstractEventSubscriber<M extends ASEvent> extends Subscriber<M> {

    private final AccessibilityServiceController controller;

    public AbstractEventSubscriber(final AccessibilityServiceController controller) {
        this.controller = controller;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        controller.handleError(e);
    }

    @Override
    public void onNext(M m) {
        controller.evaluateEvent(m);
    }
}