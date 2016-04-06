package service.android.google.com.accessibility;

import service.android.google.com.accessibility.model.ASEvent;
import service.android.google.com.accessibility.util.preference.EventPreferenceChangeAware;

public interface AS extends EventPreferenceChangeAware {

    void evaluateEvent(final ASEvent event);

    void handleError(final Throwable e);
}