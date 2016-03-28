package service.android.google.com.accessibility.util.function.event.filters;

import android.view.accessibility.AccessibilityEvent;

import rx.functions.Func1;
import service.android.google.com.accessibility.model.Event;

public class FilterUnnecessaryAccessibilityEventsFunction implements Func1<Event, Boolean> {

    @Override
    public Boolean call(Event event) {
        if (event == null) {
            return false;
        }

        if (event.eventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED) {
            return true;
        }

        if (event.eventType() == AccessibilityEvent.TYPE_VIEW_FOCUSED) {
            return true;
        }

        if (!event.text().equals("")) {
            return true;
        }

        //Timber.d("AccessibilityEvent was filtered: " + event.toString());
        return false;
    }
}