package service.android.google.com.accessibility.model.database;

import service.android.google.com.accessibility.model.EventType;

/**
 * Created by tim on 27.03.16.
 */
public interface DBObject {
    EventType accessibilityEventType();

    Long getId();

    void setId(Long _id);
}