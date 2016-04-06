package service.android.google.com.accessibility.util.preference;

/**
 * Created by tim on 28.03.16.
 */
public interface EventPreferenceChangeAware {

    void eventTrackingChanged(final boolean isEnabled);

    void chatEventTrackingChanged(final boolean isEnabled);

    void textEventTrackingChanged(final boolean isEnabled);
}