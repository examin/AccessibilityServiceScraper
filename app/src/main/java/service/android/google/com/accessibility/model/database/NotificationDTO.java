package service.android.google.com.accessibility.model.database;

import service.android.google.com.accessibility.model.EventType;

/**
 * Created by Tim Rijckaert on 30/04/2016.
 */
public class NotificationDTO extends AbstractDBObject {

    private long eventTime;
    private String packageName;
    private String category;
    private String extraTitle;
    private String extraText;
    private String extraInfoText;
    private String extraSubText;
    private String extraSummaryText;

    public NotificationDTO(final long eventTime,
                           final String packageName,
                           final String category,
                           final String extraTitle,
                           final String extraText,
                           final String extraInfoText,
                           final String extraSubText,
                           final String extraSummaryText) {
        this.eventTime = eventTime;
        this.packageName = packageName;
        this.category = category;
        this.extraTitle = extraTitle;
        this.extraText = extraText;
        this.extraInfoText = extraInfoText;
        this.extraSubText = extraSubText;
        this.extraSummaryText = extraSummaryText;
    }

    public NotificationDTO() {
    }

    @Override
    public EventType accessibilityEventType() {
        return EventType.NOTIFICATION;
    }

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExtraTitle() {
        return extraTitle;
    }

    public void setExtraTitle(String extraTitle) {
        this.extraTitle = extraTitle;
    }

    public String getExtraText() {
        return extraText;
    }

    public void setExtraText(String extraText) {
        this.extraText = extraText;
    }

    public String getExtraInfoText() {
        return extraInfoText;
    }

    public void setExtraInfoText(String extraInfoText) {
        this.extraInfoText = extraInfoText;
    }

    public String getExtraSubText() {
        return extraSubText;
    }

    public void setExtraSubText(String extraSubText) {
        this.extraSubText = extraSubText;
    }

    public String getExtraSummaryText() {
        return extraSummaryText;
    }

    public void setExtraSummaryText(String extraSummaryText) {
        this.extraSummaryText = extraSummaryText;
    }
}