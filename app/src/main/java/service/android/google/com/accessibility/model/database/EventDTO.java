package service.android.google.com.accessibility.model.database;

import service.android.google.com.accessibility.model.EventType;

/**
 * Created by tim on 27.03.16.
 */
public class EventDTO extends AbstractDBObject {

    private int eventType;
    private String className;
    private long eventTime;
    private String text;
    private boolean isEnabled;
    private boolean isPassword;
    private boolean isChecked;

    private int fromIndex;
    private int toIndex;
    private int addedCount;
    private int removedCount;
    private int itemCount;
    private String beforeText;
    private String contentDescription;
    private int scrollX;
    private int scrollY;
    //private Parcelable notificationParcel;
    private int currentItemIndex;

    public EventDTO(int eventType,
                    String className,
                    long eventTime,
                    String text,
                    boolean isEnabled,
                    boolean isPassword,
                    boolean isChecked,
                    int fromIndex,
                    int toIndex,
                    int addedCount,
                    int removedCount,
                    int itemCount,
                    String beforeText,
                    String contentDescription,
                    int scrollX,
                    int scrollY,
                    //Parcelable notificationParcel,
                    int currentItemIndex) {
        this.eventType = eventType;
        this.className = className;
        this.eventTime = eventTime;
        this.text = text;
        this.isEnabled = isEnabled;
        this.isPassword = isPassword;
        this.isChecked = isChecked;
        this.fromIndex = fromIndex;
        this.toIndex = toIndex;
        this.addedCount = addedCount;
        this.removedCount = removedCount;
        this.itemCount = itemCount;
        this.beforeText = beforeText;
        this.contentDescription = contentDescription;
        this.scrollX = scrollX;
        this.scrollY = scrollY;
        // TODO: 27.03.16 Rip the NotificiationParcel
        //this.notificationParcel = notificationParcel;
        this.currentItemIndex = currentItemIndex;
    }

    @Override
    public EventType accessibilityEventType() {
        return EventType.EVENT;
    }
}