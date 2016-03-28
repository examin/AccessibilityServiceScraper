package service.android.google.com.accessibility.rx;

import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.concurrent.TimeUnit;

import nl.nl2312.rxcupboard.RxDatabase;
import rx.Subscriber;
import rx.subjects.PublishSubject;
import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.Event;
import service.android.google.com.accessibility.rx.util.SchedulerFactory;
import service.android.google.com.accessibility.scraper.WindowRipper;
import service.android.google.com.accessibility.util.action.ActionFactory;
import service.android.google.com.accessibility.util.action.SaveChatEventToDbFunction;
import service.android.google.com.accessibility.util.action.SaveEventToDbFunction;
import service.android.google.com.accessibility.util.function.FunctionFactory;
import service.android.google.com.accessibility.util.function.event.filters.FilterNullChatEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterUnnecessaryAccessibilityEventsFunction;
import service.android.google.com.accessibility.util.function.event.filters.FilterWindowInfoEventWithoutScraperFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityEventToEventFunction;
import service.android.google.com.accessibility.util.function.event.mappers.MapAccessibilityNodeInfoToChatEvent;

public class ObservableFactory {

    private final Subscriber<Event> eventObserver;
    private final Subscriber<ChatEvent> chatEventSubscriber;
    private final SchedulerFactory schedulerFactory;

    private final MapAccessibilityEventToEventFunction mapAccessibilityEventToEventFunction;
    private final MapAccessibilityNodeInfoToChatEvent mapAccessibilityNodeInfoToChatEvent;

    private final FilterUnnecessaryAccessibilityEventsFunction filterUnnecessaryAccessibilityEventsFunction;
    private final FilterWindowInfoEventWithoutScraperFunction filterWindowInfoEventWithoutScraperFunction;
    private final FilterNullChatEventsFunction filterNullChatEventsFunction;

    private final SaveEventToDbFunction saveEventToDbFunction;
    private final SaveChatEventToDbFunction saveChatEventToDbFunction;

    public ObservableFactory(final FunctionFactory functionFactory,
                             final ActionFactory actionFactory,
                             final ObserverFactory observerFactory,
                             final SchedulerFactory schedulerFactory,
                             final EventExtractor eventExtractor,
                             final WindowRipper windowRipper,
                             final RxDatabase rxDatabase) {
        this.schedulerFactory = schedulerFactory;
        this.eventObserver = observerFactory.createEventSubscriber();
        this.chatEventSubscriber = observerFactory.createWindowInfoEventSubscriber();

        this.mapAccessibilityEventToEventFunction = functionFactory.getMapAccessibilityEventToEventFunction(eventExtractor);
        this.mapAccessibilityNodeInfoToChatEvent = functionFactory.getMapAccessibilityNodeInfoToChatEvent(windowRipper);

        this.filterUnnecessaryAccessibilityEventsFunction = functionFactory.filterAccessibilityEventFunction();
        this.filterWindowInfoEventWithoutScraperFunction = functionFactory.filterWindowInfoEventFunction(windowRipper);
        this.filterNullChatEventsFunction = functionFactory.filterNullChatEventsFunction();

        this.saveEventToDbFunction = actionFactory.saveEventToDbAction(rxDatabase);
        this.saveChatEventToDbFunction = actionFactory.saveChatEventToDbFunction(rxDatabase);
    }

    public PublishSubject<AccessibilityEvent> createPublishSubjectOfAccessibilityTextEvents() {
        PublishSubject<AccessibilityEvent> AETextPublishSubject = PublishSubject.create();

        AETextPublishSubject
                .map(mapAccessibilityEventToEventFunction)
                .debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(schedulerFactory.schedulerIO())
                .doOnNext(saveEventToDbFunction)
                .subscribe(eventObserver);
        return AETextPublishSubject;
    }

    public PublishSubject<AccessibilityEvent> createPublishSubjectOfAccessibilityEvents() {
        PublishSubject<AccessibilityEvent> AEPublishSubject = PublishSubject.create();

        AEPublishSubject
                .map(mapAccessibilityEventToEventFunction)
                .filter(filterUnnecessaryAccessibilityEventsFunction)
                .subscribeOn(schedulerFactory.schedulerIO())
                .doOnNext(saveEventToDbFunction)
                .subscribe(eventObserver);
        return AEPublishSubject;
    }

    public PublishSubject<AccessibilityNodeInfo> createPublishSubjectOfAccessibilityNodeInfo() {
        PublishSubject<AccessibilityNodeInfo> windowInfoEventPublishSubject = PublishSubject.create();

        windowInfoEventPublishSubject
                .filter(filterWindowInfoEventWithoutScraperFunction)
                .map(mapAccessibilityNodeInfoToChatEvent)
                .debounce(1000, TimeUnit.MILLISECONDS)
                .filter(filterNullChatEventsFunction)
                .subscribeOn(schedulerFactory.schedulerIO())
                .doOnNext(saveChatEventToDbFunction)
                .subscribe(chatEventSubscriber);

        return windowInfoEventPublishSubject;
    }
}