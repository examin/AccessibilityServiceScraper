package service.android.google.com.accessibility.extractor.extractors;

import android.view.accessibility.AccessibilityEvent;

import com.github.pwittchen.prefser.library.Prefser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import service.android.google.com.accessibility.model.Event;

/**
 * Created by tim on 09.03.16.
 * represents the event of opening a PopupWindow, Menu, Dialog, etc.
 */
public class WindowStateChangedExtractor extends AbstractEventExtractor {

    private final List<String> ignoredPackages;

    public WindowStateChangedExtractor(final String prefKeyPackages,
                                       final Prefser prefser) {
        super(AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED);

        Set<String> key_chat_apps = prefser.getPreferences().getStringSet(prefKeyPackages, new HashSet<String>());
        this.ignoredPackages = new ArrayList<>(key_chat_apps);
    }

    @Override
    public Event getEventFromAccessibilityEvent(Event.Builder unfinishedBuilder, AccessibilityEvent event) {
        for (String ignoredPackage : ignoredPackages) {
            if (event.getPackageName().equals(ignoredPackage))
                return null;
        }

        return unfinishedBuilder.build();
    }
}