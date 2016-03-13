package service.android.google.com.accessibility.util.function.event.filters;

import android.util.Log;

import rx.functions.Func1;
import service.android.google.com.accessibility.model.ChatEvent;
import timber.log.Timber;

public class FilterNullChatEventsFunction implements Func1<ChatEvent, Boolean> {

    @Override
    public Boolean call(ChatEvent chatEvent) {
        boolean isValid = chatEvent != null;

        if (!isValid) {
            Log.d("TAG", "Filtered an empty ChatEvent.");
            Timber.d("Filtered an empty ChatEvent.");
        }

        return isValid;
    }
}