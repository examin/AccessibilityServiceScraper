package service.android.google.com.accessibility.util.function.event;

import android.view.accessibility.AccessibilityEvent;

import rx.functions.Func1;
import service.android.google.com.accessibility.model.Event;
import timber.log.Timber;

/**
 * Created by tim on 09.03.16.
 */
public class FilterAccessibilityEventFunction implements Func1<Event, Boolean> {

    @Override
    public Boolean call(Event event) {
        Timber.d("Event was filtered from Observable: " + event);
        return event.eventType() == AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED || !event.text().equals("");
    }
}
