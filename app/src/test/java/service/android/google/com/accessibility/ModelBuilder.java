package service.android.google.com.accessibility;

import android.view.accessibility.AccessibilityEvent;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 19.03.16.
 */
public class ModelBuilder {
    public static Event createEvent() {
        return Event.builder()
                .eventType(AccessibilityEvent.TYPE_VIEW_CLICKED)
                .className("com.android.test")
                .packageName("com.packagename.mock")
                .eventTime(123456)
                .text("text")
                .isEnabled(true)
                .isPassword(false)
                .isChecked(true)
                .build();
    }
}
