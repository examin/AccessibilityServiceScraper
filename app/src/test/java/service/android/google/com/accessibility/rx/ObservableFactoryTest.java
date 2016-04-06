package service.android.google.com.accessibility.rx;

import android.content.res.Resources;

import com.github.pwittchen.prefser.library.Prefser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.concurrent.TimeUnit;

import nl.nl2312.rxcupboard.RxDatabase;
import rx.Subscriber;
import rx.Subscription;
import rx.subjects.PublishSubject;
import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.rx.observer.ChatEventSubscriber;
import service.android.google.com.accessibility.rx.observer.EventSubscriber;
import service.android.google.com.accessibility.rx.observer.ToggleEventSubscriber;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 26.03.16.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PublishSubject.class)
public class ObservableFactoryTest {
    private ObservableFactory observableFactory;

    @Mock
    private FunctionFactory functionFactory;
    @Mock
    private ObserverFactory observerFactory;
    @Mock
    private SchedulerFactory schedulerFactory;
    @Mock
    private EventExtractor eventExtractor;
    @Mock
    private WindowRipper windowRipper;
    @Mock
    private EventSubscriber eventSubsriber;
    @Mock
    private ChatEventSubscriber windowEventSubscriber;
    @Mock
    private MapAccessibilityEventToEventFunction mapAccessibilityEventToEventFunction;
    @Mock
    private MapAccessibilityNodeInfoToChatEvent mapAccessibilityNodeInfoToChatEvent;
    @Mock
    private FilterUnnecessaryAccessibilityEventsFunction filterUnnecessaryAccessibilityEventsFunction;
    @Mock
    private FilterWindowInfoEventWithoutScraperFunction filterWindowInfoEventWithoutScraperFunction;
    @Mock
    private FilterNullChatEventsFunction filterNullChatEventsFunction;
    @Mock
    private rx.Scheduler ioScheduler;
    @Mock
    private ActionFactory actionFactory;
    @Mock
    private RxDatabase rxDatabase;
    @Mock
    private SaveEventToDbFunction saveEventToDbFunction;
    @Mock
    private SaveChatEventToDbFunction saveChatEventToDbFunction;
    @Mock
    private Resources resources;
    @Mock
    private Prefser prefser;
    @Mock
    private rx.Observable<java.lang.String> preferencesObservable;
    @Mock
    private ToggleEventSubscriber toggleEventSubscriber;
    @Mock
    private rx.Subscription toggleEventSubscription;

    @Before
    public void setUp() throws Exception {
        when(schedulerFactory.schedulerIO()).thenReturn(ioScheduler);

        when(actionFactory.saveEventToDbAction(rxDatabase)).thenReturn(saveEventToDbFunction);
        when(actionFactory.saveChatEventToDbFunction(rxDatabase)).thenReturn(saveChatEventToDbFunction);

        when(observerFactory.createEventSubscriber()).thenReturn(eventSubsriber);
        when(observerFactory.createWindowInfoEventSubscriber()).thenReturn(windowEventSubscriber);
        when(observerFactory.createToggleEventSubscriber(resources, prefser)).thenReturn(toggleEventSubscriber);

        when(functionFactory.getMapAccessibilityEventToEventFunction(eventExtractor)).thenReturn(mapAccessibilityEventToEventFunction);
        when(functionFactory.getMapAccessibilityNodeInfoToChatEvent(windowRipper)).thenReturn(mapAccessibilityNodeInfoToChatEvent);

        when(functionFactory.filterAccessibilityEventFunction()).thenReturn(filterUnnecessaryAccessibilityEventsFunction);
        when(functionFactory.filterWindowInfoEventFunction(windowRipper)).thenReturn(filterWindowInfoEventWithoutScraperFunction);
        when(functionFactory.filterNullChatEventsFunction()).thenReturn(filterNullChatEventsFunction);

        when(prefser.observePreferences()).thenReturn(preferencesObservable);

        observableFactory = new ObservableFactory(
                functionFactory,
                actionFactory,
                observerFactory,
                schedulerFactory,
                eventExtractor,
                windowRipper,
                rxDatabase,
                resources,
                prefser
        );
    }

    //<editor-fold desc="Text Event PublishSubject">
    @Test
    public void test_createPublishSubjectOfAccessibilityTextEvents_shouldNotBeNull() throws Exception {
        final PublishSubject textPublishSubject = prepareAccessibilityTextEvent();
        assertThat(observableFactory.createPublishSubjectOfAccessibilityTextEvents(), is(textPublishSubject));
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityTextEvents_shouldSetMapper() throws Exception {
        final PublishSubject textPublishSubject = prepareAccessibilityTextEvent();
        observableFactory.createPublishSubjectOfAccessibilityTextEvents();
        verify(textPublishSubject).map(mapAccessibilityEventToEventFunction);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityTextEvents_shouldSetDebouncer() throws Exception {
        final PublishSubject textPublishSubject = prepareAccessibilityTextEvent();
        observableFactory.createPublishSubjectOfAccessibilityTextEvents();
        verify(textPublishSubject).debounce(500, TimeUnit.MILLISECONDS);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityTextEvents_shouldSetCorrectScheduler() throws Exception {
        final PublishSubject textPublishSubject = prepareAccessibilityTextEvent();
        observableFactory.createPublishSubjectOfAccessibilityTextEvents();
        verify(textPublishSubject).subscribeOn(ioScheduler);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityTextEvents_shouldSetSaverToDbFunction() throws Exception {
        final PublishSubject textPublishSubject = prepareAccessibilityTextEvent();
        observableFactory.createPublishSubjectOfAccessibilityTextEvents();
        verify(textPublishSubject).doOnNext(saveEventToDbFunction);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityTextEvents_shouldSetSubscriber() throws Exception {
        final PublishSubject textPublishSubject = prepareAccessibilityTextEvent();
        observableFactory.createPublishSubjectOfAccessibilityTextEvents();
        verify(textPublishSubject).subscribe(eventSubsriber);
    }

    private PublishSubject prepareAccessibilityTextEvent() {
        PowerMockito.mockStatic(PublishSubject.class);
        PublishSubject textPublishSubject = PowerMockito.mock(PublishSubject.class);
        when(textPublishSubject.map(mapAccessibilityEventToEventFunction)).thenReturn(textPublishSubject);
        when(textPublishSubject.debounce(500, TimeUnit.MILLISECONDS)).thenReturn(textPublishSubject);
        when(textPublishSubject.subscribeOn(ioScheduler)).thenReturn(textPublishSubject);
        when(textPublishSubject.doOnNext(saveEventToDbFunction)).thenReturn(textPublishSubject);
        when(textPublishSubject.subscribe()).thenReturn(mock(Subscription.class));
        when(PublishSubject.create()).thenReturn(textPublishSubject);
        return textPublishSubject;
    }
    //</editor-fold>

    //<editor-fold desc="Accessibility Event PublishSubject">
    @Test
    public void test_createPublishSubjectOfAccessibilityEvents_shouldNotBeNull() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityEvent();
        assertThat(observableFactory.createPublishSubjectOfAccessibilityEvents(), is(accessibilityEventObservable));
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityEvents_shouldSetMapper() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityEvent();
        observableFactory.createPublishSubjectOfAccessibilityEvents();
        verify(accessibilityEventObservable).map(mapAccessibilityEventToEventFunction);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityEvents_shouldSetFilter() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityEvent();
        observableFactory.createPublishSubjectOfAccessibilityEvents();
        verify(accessibilityEventObservable).filter(filterUnnecessaryAccessibilityEventsFunction);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityEvents_shouldSetIOScheduler() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityEvent();
        observableFactory.createPublishSubjectOfAccessibilityEvents();
        verify(accessibilityEventObservable).subscribeOn(ioScheduler);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityEvents_shouldSetSaverToDbFunction() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityEvent();
        observableFactory.createPublishSubjectOfAccessibilityEvents();
        verify(accessibilityEventObservable).doOnNext(saveEventToDbFunction);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityEvents_shouldSetSubscriber() throws Exception {
        final PublishSubject accessibilityEventObservable = prepareAccessibilityEvent();
        observableFactory.createPublishSubjectOfAccessibilityEvents();
        verify(accessibilityEventObservable).subscribe(eventSubsriber);
    }

    private PublishSubject prepareAccessibilityEvent() {
        PowerMockito.mockStatic(PublishSubject.class);
        PublishSubject accessibilityEventObservable = PowerMockito.mock(PublishSubject.class);
        when(accessibilityEventObservable.map(mapAccessibilityEventToEventFunction)).thenReturn(accessibilityEventObservable);
        when(accessibilityEventObservable.filter(filterUnnecessaryAccessibilityEventsFunction)).thenReturn(accessibilityEventObservable);
        when(accessibilityEventObservable.subscribeOn(ioScheduler)).thenReturn(accessibilityEventObservable);
        when(accessibilityEventObservable.doOnNext(saveEventToDbFunction)).thenReturn(accessibilityEventObservable);
        when(accessibilityEventObservable.subscribe()).thenReturn(mock(Subscriber.class));
        when(PublishSubject.create()).thenReturn(accessibilityEventObservable);
        return accessibilityEventObservable;
    }
    //</editor-fold>

    //<editor-fold desc="Accessibility NodeInfo PublishSubject">
    @Test
    public void test_createPublishSubjectOfAccessibilityNodeInfo_shouldNotBeNull() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityNodeInfoEvent();
        assertThat(observableFactory.createPublishSubjectOfAccessibilityNodeInfo(), is(accessibilityEventObservable));
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityNodeInfo_shouldFilterWindowInfoEvent() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityNodeInfoEvent();
        observableFactory.createPublishSubjectOfAccessibilityNodeInfo();
        verify(accessibilityEventObservable).filter(filterWindowInfoEventWithoutScraperFunction);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityNodeInfo_shouldMapAccessibilityNodeInfoToChatEvent() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityNodeInfoEvent();
        observableFactory.createPublishSubjectOfAccessibilityNodeInfo();
        verify(accessibilityEventObservable).map(mapAccessibilityNodeInfoToChatEvent);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityNodeInfo_shouldSetDebouncer() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityNodeInfoEvent();
        observableFactory.createPublishSubjectOfAccessibilityNodeInfo();
        verify(accessibilityEventObservable).debounce(1000, TimeUnit.MILLISECONDS);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityNodeInfo_shouldFilterNullChatEvent() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityNodeInfoEvent();
        observableFactory.createPublishSubjectOfAccessibilityNodeInfo();
        verify(accessibilityEventObservable).filter(filterNullChatEventsFunction);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityNodeInfo_shouldSubscribeOnIOThread() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityNodeInfoEvent();
        observableFactory.createPublishSubjectOfAccessibilityNodeInfo();
        verify(accessibilityEventObservable).subscribeOn(ioScheduler);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityNodeInfo_shouldSetSaverToDbFunction() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityNodeInfoEvent();
        observableFactory.createPublishSubjectOfAccessibilityNodeInfo();
        verify(accessibilityEventObservable).doOnNext(saveChatEventToDbFunction);
    }

    @Test
    public void test_createPublishSubjectOfAccessibilityNodeInfo_shouldSetSubscriber() throws Exception {
        PublishSubject accessibilityEventObservable = prepareAccessibilityNodeInfoEvent();
        observableFactory.createPublishSubjectOfAccessibilityNodeInfo();
        verify(accessibilityEventObservable).subscribe(windowEventSubscriber);
    }

    private PublishSubject prepareAccessibilityNodeInfoEvent() {
        PowerMockito.mockStatic(PublishSubject.class);
        PublishSubject accessibilityNodeInfo = PowerMockito.mock(PublishSubject.class);
        when(accessibilityNodeInfo.filter(filterWindowInfoEventWithoutScraperFunction)).thenReturn(accessibilityNodeInfo);
        when(accessibilityNodeInfo.map(mapAccessibilityNodeInfoToChatEvent)).thenReturn(accessibilityNodeInfo);
        when(accessibilityNodeInfo.debounce(1000, TimeUnit.MILLISECONDS)).thenReturn(accessibilityNodeInfo);
        when(accessibilityNodeInfo.filter(filterNullChatEventsFunction)).thenReturn(accessibilityNodeInfo);
        when(accessibilityNodeInfo.subscribeOn(ioScheduler)).thenReturn(accessibilityNodeInfo);
        when(accessibilityNodeInfo.doOnNext(saveChatEventToDbFunction)).thenReturn(accessibilityNodeInfo);
        when(accessibilityNodeInfo.subscribe()).thenReturn(mock(Subscriber.class));
        when(PublishSubject.create()).thenReturn(accessibilityNodeInfo);
        return accessibilityNodeInfo;
    }
    //</editor-fold>

    //<editor-fold desc="Toggle Event Observer">
    @Test
    public void test_subscribeObservePreferences() throws Exception {
        prepareToggleEventObservable();
        assertThat(observableFactory.subscribeObservePreferences(), is(toggleEventSubscription));
    }

    @Test
    public void test_subscribeObservePreference_shouldSetCorrectScheduler() throws Exception {
        prepareToggleEventObservable();
        observableFactory.subscribeObservePreferences();
        verify(preferencesObservable).subscribeOn(ioScheduler);
    }

    @Test
    public void test_subscribeObservePreference_shouldSubscribe() throws Exception {
        prepareToggleEventObservable();
        observableFactory.subscribeObservePreferences();
        verify(preferencesObservable).subscribe(toggleEventSubscriber);
    }

    private void prepareToggleEventObservable() {
        when(preferencesObservable.subscribeOn(ioScheduler)).thenReturn(preferencesObservable);
        when(preferencesObservable.subscribe(toggleEventSubscriber)).thenReturn(toggleEventSubscription);
    }
    //</editor-fold>
}