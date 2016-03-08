package service.android.google.com.accessibility.rx;

import android.view.accessibility.AccessibilityEvent;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import service.android.google.com.accessibility.controller.AccessibilityServiceController;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.util.function.FunctionFactory;

/**
 * Created by tim on 08.03.16.
 */
public class ObservableFactory {

    private final FunctionFactory functionFactory;
    private final ObserverFactory observerFactory;

    public ObservableFactory(final FunctionFactory functionFactory,
                             final ObserverFactory observerFactory) {
        this.functionFactory = functionFactory;
        this.observerFactory = observerFactory;
    }

    public PublishSubject<AccessibilityEvent> createPublishSubjectOfAccessibilityEvents(AccessibilityServiceController accessibilityServiceController) {
        PublishSubject objectPublishSubject = PublishSubject.create();
        Subscriber<Event> eventSubscriber = observerFactory.createEventSubscriber(accessibilityServiceController);
        objectPublishSubject
                .map(functionFactory.getMapAccessibilityEventToEventFunction())
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eventSubscriber);

        return objectPublishSubject;
    }
}
