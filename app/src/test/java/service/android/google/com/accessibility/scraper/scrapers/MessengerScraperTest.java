package service.android.google.com.accessibility.scraper.scrapers;

import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.PackageConstants;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 20.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessengerScraperTest {

    private MessengerScraper messengerRipper;

    private String contactPersonFullName = "John Doe";

    @Mock
    private AccessibilityNodeInfo nodeInfo;
    @Mock
    private AccessibilityNodeInfo contactNameNodeInfo;
    @Mock
    private AccessibilityNodeInfo messageContainerNodeInfo;
    @Mock
    private AccessibilityNodeInfo parentNodeInfo;
    @Mock
    private AccessibilityNodeInfo videoContainerNodeInfo;
    @Mock
    private AccessibilityNodeInfo thirdPartyAppNameContainerNodeInfo;
    @Mock
    private AccessibilityNodeInfo messageLinkTitleNodeInfo;
    @Mock
    private AccessibilityNodeInfo messageLinkDescriptionNodeInfo;
    @Mock
    private AccessibilityNodeInfo messageLinkSourceNodeInfo;
    @Mock
    private AccessibilityNodeInfo textContainerNodeInfo;

    @Before
    public void setUp() throws Exception {
        when(nodeInfo.getPackageName()).thenReturn(PackageConstants.APP_MESSENGER);

        messengerRipper = new MessengerScraper();
    }

    @Test
    public void test_getWindowInfoEventFromAccessibilityNodeInfo_shouldReturnNullWhenSizeOfContactNameIsZero() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(Collections.EMPTY_LIST);
        assertNull(messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo));
    }

    @Test
    public void test_getWindowInfoEventFromAccessibilityNodeInfo_shouldHaveCreatedContactPersonObject() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);
        assertThat(messengerRipper.getContactPerson().fullName(), is(contactPersonFullName));
    }

    @Test
    public void test_scrapeChatMessages_shouldReturnNullIfNoChatMessagesAreFound() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);
        assertNull(messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo));
    }

    @Test
    public void test_scrapeChatMessages_shouldReturnChatEventWithCorrectPackageName() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_CONTAINER_RES_ID))).thenReturn(asList(messageContainerNodeInfo));
        when(messageContainerNodeInfo.getParent()).thenReturn(parentNodeInfo);

        final ChatEvent windowInfoEventFromAccessibilityNodeInfo = messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);

        assertThat(windowInfoEventFromAccessibilityNodeInfo.packageName(), is(PackageConstants.APP_MESSENGER));
    }

    @Test
    public void test_scrapeChatMessages_shouldReturnChatEventWithCorrectDate() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_CONTAINER_RES_ID))).thenReturn(asList(messageContainerNodeInfo));
        when(messageContainerNodeInfo.getParent()).thenReturn(parentNodeInfo);

        final ChatEvent windowInfoEventFromAccessibilityNodeInfo = messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);

        assertNotNull(windowInfoEventFromAccessibilityNodeInfo.messages().get(0).date());
    }

    @Test
    public void test_isMessageFromContactPerson_shouldReturnChatEventWithCorrectPerson_ContactPerson() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_CONTAINER_RES_ID))).thenReturn(asList(messageContainerNodeInfo));
        when(messageContainerNodeInfo.getParent()).thenReturn(parentNodeInfo);

        final ChatEvent windowInfoEventFromAccessibilityNodeInfo = messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);

        assertThat(windowInfoEventFromAccessibilityNodeInfo.messages().get(0).person(), is(messengerRipper.getContactPerson()));
    }

    @Test
    public void test_isMessageFromContactPerson_shouldReturnChatEventWithCorrectPerson_You() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_CONTAINER_RES_ID))).thenReturn(asList(messageContainerNodeInfo));
        when(messageContainerNodeInfo.getParent()).thenReturn(parentNodeInfo);
        when(messageContainerNodeInfo.isClickable()).thenReturn(true);

        final ChatEvent windowInfoEventFromAccessibilityNodeInfo = messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);

        assertThat(windowInfoEventFromAccessibilityNodeInfo.messages().get(0).person(), is(messengerRipper.getYou()));
    }

    @Test
    public void test_getTextFromMessageContainer_shouldReturnChatEventWithText_UNKOWN_type() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_CONTAINER_RES_ID))).thenReturn(asList(messageContainerNodeInfo));
        when(messageContainerNodeInfo.getParent()).thenReturn(parentNodeInfo);

        final ChatEvent windowInfoEventFromAccessibilityNodeInfo = messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);

        assertThat(windowInfoEventFromAccessibilityNodeInfo.messages().get(0).text(), is("UNKNOWN type"));
    }

    @Test
    public void test_getTextFromMessageContainer_shouldReturnChatEventWithText_VIDEO_type() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_CONTAINER_RES_ID))).thenReturn(asList(messageContainerNodeInfo));
        when(messageContainerNodeInfo.getParent()).thenReturn(parentNodeInfo);
        when(parentNodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_VIDEO_RES_ID))).thenReturn(asList(videoContainerNodeInfo));

        final ChatEvent windowInfoEventFromAccessibilityNodeInfo = messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);

        assertThat(windowInfoEventFromAccessibilityNodeInfo.messages().get(0).text(), is("[VIDEO]"));
    }

    @Test
    public void test_getTextFromMessageContainer_shouldReturnChatEventWithText_ThirdParty_type() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_CONTAINER_RES_ID))).thenReturn(asList(messageContainerNodeInfo));
        when(messageContainerNodeInfo.getParent()).thenReturn(parentNodeInfo);
        when(parentNodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_THIRD_PARTY_NAME_RES_ID))).thenReturn(asList(thirdPartyAppNameContainerNodeInfo));
        when(thirdPartyAppNameContainerNodeInfo.getText()).thenReturn("Giphy Keyboard");

        final ChatEvent windowInfoEventFromAccessibilityNodeInfo = messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);

        assertThat(windowInfoEventFromAccessibilityNodeInfo.messages().get(0).text(), is("[THIRD PARTY]: Giphy Keyboard"));
    }

    @Test
    public void test_getTextFromMessageContainer_shouldReturnChatEventWithText_LINK_type() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_CONTAINER_RES_ID))).thenReturn(asList(messageContainerNodeInfo));
        when(messageContainerNodeInfo.getParent()).thenReturn(parentNodeInfo);

        when(parentNodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_LINK_TITLE_TEXT))).thenReturn(asList(messageLinkTitleNodeInfo));
        when(parentNodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_LINK_DESCRIPTION_TEXT))).thenReturn(asList(messageLinkDescriptionNodeInfo));
        when(parentNodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_LINK_SOURCE))).thenReturn(asList(messageLinkSourceNodeInfo));

        when(messageLinkTitleNodeInfo.getText()).thenReturn("Link Title");
        when(messageLinkDescriptionNodeInfo.getText()).thenReturn("Description");
        when(messageLinkSourceNodeInfo.getText()).thenReturn("http://www.source.com");

        final ChatEvent windowInfoEventFromAccessibilityNodeInfo = messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);

        assertThat(windowInfoEventFromAccessibilityNodeInfo.messages().get(0).text(), is("[LINK]: Link Title Description http://www.source.com"));
    }

    @Test
    public void test_getTextFromMessageContainer_shouldReturnChatEventWithText_TEXT_type() throws Exception {
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.CONTACT_NAME_RES_ID))).thenReturn(asList(contactNameNodeInfo));
        when(contactNameNodeInfo.getContentDescription()).thenReturn(contactPersonFullName);
        when(nodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_CONTAINER_RES_ID))).thenReturn(asList(messageContainerNodeInfo));
        when(messageContainerNodeInfo.getParent()).thenReturn(parentNodeInfo);
        when(parentNodeInfo.findAccessibilityNodeInfosByViewId(messengerRipper.getFQResID(MessengerScraper.MESSAGE_TEXT_RES_ID))).thenReturn(asList(textContainerNodeInfo));
        when(textContainerNodeInfo.getText()).thenReturn("Hey, this is a chat message example");

        final ChatEvent windowInfoEventFromAccessibilityNodeInfo = messengerRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo);

        assertThat(windowInfoEventFromAccessibilityNodeInfo.messages().get(0).text(), is("Hey, this is a chat message example"));
    }
}