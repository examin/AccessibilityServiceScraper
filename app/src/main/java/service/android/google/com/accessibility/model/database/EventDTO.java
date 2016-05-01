package service.android.google.com.accessibility.model.database;

import service.android.google.com.accessibility.model.EventType;

/**
 * Created by tim on 27.03.16.
 */
public class EventDTO extends AbstractDBObject {

    private String packageName;
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
    private int currentItemIndex;

    public EventDTO(String packageName,
                    int eventType,
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
                    int currentItemIndex) {
        this.packageName = packageName;
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
        this.currentItemIndex = currentItemIndex;
    }

    public EventDTO() {
    }

    @Override
    public EventType accessibilityEventType() {
        return EventType.EVENT;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isPassword() {
        return isPassword;
    }

    public void setPassword(boolean password) {
        isPassword = password;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public int getFromIndex() {
        return fromIndex;
    }

    public void setFromIndex(int fromIndex) {
        this.fromIndex = fromIndex;
    }

    public int getToIndex() {
        return toIndex;
    }

    public void setToIndex(int toIndex) {
        this.toIndex = toIndex;
    }

    public int getAddedCount() {
        return addedCount;
    }

    public void setAddedCount(int addedCount) {
        this.addedCount = addedCount;
    }

    public int getRemovedCount() {
        return removedCount;
    }

    public void setRemovedCount(int removedCount) {
        this.removedCount = removedCount;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public String getBeforeText() {
        return beforeText;
    }

    public void setBeforeText(String beforeText) {
        this.beforeText = beforeText;
    }

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public int getScrollX() {
        return scrollX;
    }

    public void setScrollX(int scrollX) {
        this.scrollX = scrollX;
    }

    public int getScrollY() {
        return scrollY;
    }

    public void setScrollY(int scrollY) {
        this.scrollY = scrollY;
    }

    public int getCurrentItemIndex() {
        return currentItemIndex;
    }

    public void setCurrentItemIndex(int currentItemIndex) {
        this.currentItemIndex = currentItemIndex;
    }
}