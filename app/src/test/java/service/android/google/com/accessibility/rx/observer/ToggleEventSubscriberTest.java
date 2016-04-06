package service.android.google.com.accessibility.rx.observer;

import android.content.res.Resources;

import com.github.pwittchen.prefser.library.Prefser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.util.preference.EventPreferenceChangeAware;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 04.04.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ToggleEventSubscriberTest {

    private static final String GENERAL_EVENT_TRACKING = "general";
    private static final String TEXT_EVENT_TRACKING = "text";
    private static final String CHAT_EVENT_TRACKING = "chat";
    private ToggleEventSubscriber toggleEventSubscriber;
    @Mock
    private EventPreferenceChangeAware eventPreferenceChangeAware;
    @Mock
    private Resources resources;
    @Mock
    private Prefser prefser;

    @Before
    public void setUp() throws Exception {
        when(resources.getBoolean(R.bool.general_event)).thenReturn(true);
        when(resources.getBoolean(R.bool.text_event)).thenReturn(true);
        when(resources.getBoolean(R.bool.chat_message_event)).thenReturn(true);

        when(resources.getString(R.string.pref_key_event_general)).thenReturn(GENERAL_EVENT_TRACKING);
        when(resources.getString(R.string.pref_key_event_text)).thenReturn(TEXT_EVENT_TRACKING);
        when(resources.getString(R.string.pref_key_chat_event)).thenReturn(CHAT_EVENT_TRACKING);

        toggleEventSubscriber = new ToggleEventSubscriber(eventPreferenceChangeAware, resources, prefser);
    }

    @Test
    public void test_onCompleted() throws Exception {
        toggleEventSubscriber.onCompleted();
        verifyZeroInteractions(eventPreferenceChangeAware, prefser);
    }

    @Test
    public void test_onError() throws Exception {
        toggleEventSubscriber.onError(new Exception("testing"));
        verifyZeroInteractions(eventPreferenceChangeAware, prefser);
    }

    @Test
    public void test_onNext_withUnknownKey() throws Exception {
        toggleEventSubscriber.onNext("UNKNOWN_KEY");
        verifyZeroInteractions(eventPreferenceChangeAware, prefser);
    }

    @Test
    public void test_onNext_withGeneralEventTrackingKey() throws Exception {
        when(prefser.get(GENERAL_EVENT_TRACKING, Boolean.class, true)).thenReturn(true);
        toggleEventSubscriber.onNext(GENERAL_EVENT_TRACKING);
        verify(eventPreferenceChangeAware).eventTrackingChanged(true);
    }

    @Test
    public void test_onNext_witTextEventTrackingKey() throws Exception {
        when(prefser.get(TEXT_EVENT_TRACKING, Boolean.class, true)).thenReturn(true);
        toggleEventSubscriber.onNext(TEXT_EVENT_TRACKING);
        verify(eventPreferenceChangeAware).textEventTrackingChanged(true);
    }

    @Test
    public void test_onNext_withChatEventTrackingKey() throws Exception {
        when(prefser.get(CHAT_EVENT_TRACKING, Boolean.class, true)).thenReturn(true);
        toggleEventSubscriber.onNext(CHAT_EVENT_TRACKING);
        verify(eventPreferenceChangeAware).chatEventTrackingChanged(true);
    }
}