package service.android.google.com.accessibility.util.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.model.Notification;
import service.android.google.com.accessibility.model.database.NotificationDTO;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Tim Rijckaert on 1/05/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class SaveNotificationToDbFunctionTest {

    private SaveNotificationToDbFunction saveNotificationToDbFunction;

    @Mock
    private RxDatabase rxDatabase;

    private java.lang.Long eventTime = 156564L;
    private java.lang.String packageName = "com.testing";
    private java.lang.String category = "category";
    private java.lang.String extraTitle = "extraTitle";
    private java.lang.String extraText = "extraText";
    private java.lang.String extraInfoText = "extraInfoText";
    private java.lang.String extraSubText = "extraSubText";
    private java.lang.String extraSummaryText = "extraSummaryText";

    private ArgumentCaptor<NotificationDTO> notificationDTOArgumentCaptor;

    @Before
    public void setUp() throws Exception {

        notificationDTOArgumentCaptor = ArgumentCaptor.forClass(NotificationDTO.class);

        saveNotificationToDbFunction = new SaveNotificationToDbFunction(
                rxDatabase
        );
    }

    public Notification prepareForTest() {
        final Notification notification = Mockito.mock(Notification.class);
        when(notification.eventTime()).thenReturn(eventTime);
        when(notification.packageName()).thenReturn(packageName);
        when(notification.category()).thenReturn(category);
        when(notification.extraTitle()).thenReturn(extraTitle);
        when(notification.extraText()).thenReturn(extraText);
        when(notification.extraInfoText()).thenReturn(extraInfoText);
        when(notification.extraSubText()).thenReturn(extraSubText);
        when(notification.extraSummaryText()).thenReturn(extraSummaryText);
        return notification;
    }

    @Test
    public void test_call_shouldNotSaveAnythingWhenNotificationWasNull() throws Exception {
        saveNotificationToDbFunction.call(null);
        verifyZeroInteractions(rxDatabase);
    }

    @Test
    public void test_call_SaveWithCorrect_Time() throws Exception {
        final Notification notification = prepareForTest();
        saveNotificationToDbFunction.call(notification);
        verify(rxDatabase).put(notificationDTOArgumentCaptor.capture());
        assertThat(notificationDTOArgumentCaptor.getValue().getEventTime(), is(eventTime));
    }

    @Test
    public void test_call_SaveWithCorrect_PackageName() throws Exception {
        final Notification notification = prepareForTest();
        saveNotificationToDbFunction.call(notification);
        verify(rxDatabase).put(notificationDTOArgumentCaptor.capture());
        assertThat(notificationDTOArgumentCaptor.getValue().getPackageName(), is(packageName));
    }

    @Test
    public void test_call_SaveWithCorrect_Category() throws Exception {
        final Notification notification = prepareForTest();
        saveNotificationToDbFunction.call(notification);
        verify(rxDatabase).put(notificationDTOArgumentCaptor.capture());
        assertThat(notificationDTOArgumentCaptor.getValue().getCategory(), is(category));
    }

    @Test
    public void test_call_SaveWithCorrect_ExtraTitle() throws Exception {
        final Notification notification = prepareForTest();
        saveNotificationToDbFunction.call(notification);
        verify(rxDatabase).put(notificationDTOArgumentCaptor.capture());
        assertThat(notificationDTOArgumentCaptor.getValue().getExtraTitle(), is(extraTitle));
    }

    @Test
    public void test_call_SaveWithCorrect_ExtraText() throws Exception {
        final Notification notification = prepareForTest();
        saveNotificationToDbFunction.call(notification);
        verify(rxDatabase).put(notificationDTOArgumentCaptor.capture());
        assertThat(notificationDTOArgumentCaptor.getValue().getExtraText(), is(extraText));
    }

    @Test
    public void test_call_SaveWithCorrect_ExtraInfoText() throws Exception {
        final Notification notification = prepareForTest();
        saveNotificationToDbFunction.call(notification);
        verify(rxDatabase).put(notificationDTOArgumentCaptor.capture());
        assertThat(notificationDTOArgumentCaptor.getValue().getExtraInfoText(), is(extraInfoText));
    }

    @Test
    public void test_call_SaveWithCorrect_ExtraSubText() throws Exception {
        final Notification notification = prepareForTest();
        saveNotificationToDbFunction.call(notification);
        verify(rxDatabase).put(notificationDTOArgumentCaptor.capture());
        assertThat(notificationDTOArgumentCaptor.getValue().getExtraSubText(), is(extraSubText));
    }

    @Test
    public void test_call_SaveWithCorrect_ExtraSummaryText() throws Exception {
        final Notification notification = prepareForTest();
        saveNotificationToDbFunction.call(notification);
        verify(rxDatabase).put(notificationDTOArgumentCaptor.capture());
        assertThat(notificationDTOArgumentCaptor.getValue().getExtraSummaryText(), is(extraSummaryText));
    }
}