package service.android.google.com.accessibility.util.date;

import android.util.LruCache;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class DateFormatter {

    private static final String DATE_DAY_DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm";

    private static final int MAX_SIZE = 3;

    private final LruCache<String, SimpleDateFormat> cache;

    @Inject
    public DateFormatter() {
        cache = new LruCache<>(MAX_SIZE);
    }

    public synchronized String dateToString(final Date date) {
        return date != null ? getFormatterWithDateFormat(DATE_DAY_DATE_TIME_FORMAT).format(date) : "";
    }

    private SimpleDateFormat getFormatterWithDateFormat(final String format) {
        synchronized (cache) {
            if (cache.get(format) == null) {
                cache.put(format, new SimpleDateFormat(format, new Locale("nl", "be")));
            }
        }
        return cache.get(format);
    }
}