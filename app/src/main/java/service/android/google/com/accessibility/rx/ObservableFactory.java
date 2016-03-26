package service.android.google.com.accessibility.rx;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.concurrent.TimeUnit;

import rx.Subscriber;
import rx.subjects.PublishSubject;
import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.rx.util.SchedulerFactory;
import service.android.google.com.accessibility.scraper.WindowRipper;
import service.android.google.com.accessibility.util.function.FunctionFactory;
import service.android.google.com.accessibility.util.function.event.filters.FilterNullChatEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterUnnecessaryAccessibilityEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterWindowInfoEventFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityEventToEventFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityNodeInfoToChatEvent;

public class ObservableFactory {

    private final Subscriber<Event> eventObserver;
    private final Subscriber<ChatEvent> chatEventSubscriber;
    private final SchedulerFactory schedulerFactory;

    private final MapAccessibilityEventToEventFunction mapAccessibilityEventToEventFunction;
    private final FilterUnnecessaryAccessibilityEventsFunction filterUnnecessaryAccessibilityEventsFunction;
    private final FilterWindowInfoEventFunction filterWindowInfoEventFunction;
    private final MapAccessibilityNodeInfoToChatEvent mapAccessibilityNodeInfoToChatEvent;
    private final FilterNullChatEventsFunction filterNullChatEventsFunction;

    public ObservableFactory(final FunctionFactory functionFactory,
                             final ObserverFactory observerFactory,
                             final SchedulerFactory schedulerFactory,
                             final EventExtractor eventExtractor,
                             final WindowRipper windowRipper) {
        this.schedulerFactory = schedulerFactory;
        this.eventObserver = observerFactory.createEventSubscriber();
        this.chatEventSubscriber = observerFactory.createWindowInfoEventSubscriber();

        this.mapAccessibilityEventToEventFunction = functionFactory.getMapAccessibilityEventToEventFunction(eventExtractor);
        this.mapAccessibilityNodeInfoToChatEvent = functionFactory.getMapAccessibilityNodeInfoToChatEvent(windowRipper);

        this.filterUnnecessaryAccessibilityEventsFunction = functionFactory.filterAccessibilityEventFunction();
        this.filterWindowInfoEventFunction = functionFactory.filterWindowInfoEventFunction(windowRipper);
        this.filterNullChatEventsFunction = functionFactory.filterNullChatEventsFunction();
    }

    public PublishSubject<AccessibilityEvent> createPublishSubjectOfAccessibilityTextEvents() {
        PublishSubject<AccessibilityEvent> AETextPublishSubject = PublishSubject.create();

        AETextPublishSubject
                .map(mapAccessibilityEventToEventFunction)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(schedulerFactory.schedulerIO())
                .subscribe(eventObserver);
        return AETextPublishSubject;
    }

    public PublishSubject<AccessibilityEvent> createPublishSubjectOfAccessibilityEvents() {
        PublishSubject<AccessibilityEvent> AEPublishSubject = PublishSubject.create();

        AEPublishSubject
                .map(mapAccessibilityEventToEventFunction)
                .filter(filterUnnecessaryAccessibilityEventsFunction)
                .subscribeOn(schedulerFactory.schedulerIO())
                .subscribe(eventObserver);
        return AEPublishSubject;
    }

    public PublishSubject<AccessibilityNodeInfo> createPublishSubjectOfAccessibilityNodeInfo() {
        PublishSubject<AccessibilityNodeInfo> windowInfoEventPublishSubject = PublishSubject.create();

        windowInfoEventPublishSubject
                .filter(filterWindowInfoEventFunction)
                .map(mapAccessibilityNodeInfoToChatEvent)
                .filter(filterNullChatEventsFunction)
                .subscribeOn(schedulerFactory.schedulerIO())
                .subscribe(chatEventSubscriber);

        return windowInfoEventPublishSubject;
    }
}