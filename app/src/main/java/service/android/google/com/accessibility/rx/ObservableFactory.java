package service.android.google.com.accessibility.rx;

import android.view.accessibility.AccessibilityEvent;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
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

    public PublishSubject<AccessibilityEvent> createPublishSubjectOfAccessibilityTextEvents(final Subscriber<Event> eventSubscriber) {
        PublishSubject<AccessibilityEvent> AETextPublishSubject = PublishSubject.create();

        AETextPublishSubject
                .map(functionFactory.getMapAccessibilityEventToEventFunction())
                .debounce(400, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eventSubscriber);

        return AETextPublishSubject;
    }

    public PublishSubject<AccessibilityEvent> createPublishSubjectOfAccessibilityEvents(final Subscriber<Event> eventSubscriber) {
        PublishSubject<AccessibilityEvent> AEPublishSubject = PublishSubject.create();

        AEPublishSubject
                .map(functionFactory.getMapAccessibilityEventToEventFunction())
                .filter(functionFactory.filterAccessibilityEventFunction())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(eventSubscriber);

        return AEPublishSubject;
    }
}
