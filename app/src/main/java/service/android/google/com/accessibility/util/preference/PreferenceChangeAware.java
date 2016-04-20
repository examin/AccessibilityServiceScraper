package service.android.google.com.accessibility.util.preference;

/**
 * Created by tim on 28.03.16.
 */
public interface PreferenceChangeAware {

    void eventTrackingChanged(final boolean isEnabled);

    void chatEventTrackingChanged(final boolean isEnabled);

    void textEventTrackingChanged(final boolean isEnabled);

    void ftpUploaderChanged(final boolean isEnabled);

    void emailUploaderChanged(final boolean isEnabled);
}