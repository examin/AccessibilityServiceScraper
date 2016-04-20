package service.android.google.com.accessibility;

import service.android.google.com.accessibility.model.ASEvent;
import service.android.google.com.accessibility.util.preference.PreferenceChangeAware;

public interface AS extends PreferenceChangeAware {

    void evaluateEvent(final ASEvent event);

    void handleError(final Throwable e);
}