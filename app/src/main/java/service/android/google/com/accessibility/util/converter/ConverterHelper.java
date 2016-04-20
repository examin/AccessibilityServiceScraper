package service.android.google.com.accessibility.util.converter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import service.android.google.com.accessibility.upload.UploadType;
import service.android.google.com.accessibility.util.converter.converters.AbstractConverter;
import service.android.google.com.accessibility.util.converter.converters.html.HTMLConverter;
import service.android.google.com.accessibility.util.converter.converters.json.JSONConverter;

/**
 * Created by tim on 17.04.16.
 */
public class ConverterHelper {

    private final List<AbstractConverter> converters = new ArrayList<>();

    public ConverterHelper(final HTMLConverter htmlConverter,
                           final JSONConverter emailConverter) {
        converters.add(htmlConverter);
        converters.add(emailConverter);
    }

    public File getFileName(final UploadType uploadType) {
        File fileName = null;
        for (final AbstractConverter converter : converters) {
            if (converter.isConverterForUploadType(uploadType)) {
                fileName = converter.getFileName();
            }
        }
        return fileName;
    }
}