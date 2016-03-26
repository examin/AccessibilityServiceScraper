package service.android.google.com.accessibility.rx.observer;

import rx.Subscriber;
import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.model.ASEvent;

public abstract class AbstractEventSubscriber<M extends ASEvent> extends Subscriber<M> {

    private final AS accessibilityService;

    public AbstractEventSubscriber(final AS accessibilityService) {
        this.accessibilityService = accessibilityService;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        accessibilityService.handleError(e);
    }

    @Override
    public void onNext(M m) {
        accessibilityService.evaluateEvent(m);
    }
}