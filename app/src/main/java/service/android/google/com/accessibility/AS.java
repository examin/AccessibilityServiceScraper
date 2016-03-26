package service.android.google.com.accessibility;

import service.android.google.com.accessibility.model.ASEvent;

public interface AS {

    void evaluateEvent(final ASEvent event);

    void handleError(final Throwable e);
}