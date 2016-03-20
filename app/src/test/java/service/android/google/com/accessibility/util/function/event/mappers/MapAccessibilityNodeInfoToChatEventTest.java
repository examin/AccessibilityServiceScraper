package service.android.google.com.accessibility.util.function.event.mappers;

import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.util.ripper.WindowRipper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class MapAccessibilityNodeInfoToChatEventTest {

    private MapAccessibilityNodeInfoToChatEvent mapAccessibilityNodeInfoToChatEvent;

    @Mock
    private WindowRipper windowRipper;
    @Mock
    private AccessibilityNodeInfo nodeInfo;

    @Before
    public void setUp() throws Exception {
        mapAccessibilityNodeInfoToChatEvent = new MapAccessibilityNodeInfoToChatEvent(windowRipper);
    }

    @Test
    public void test_call() throws Exception {
        ChatEvent chatEvent = ModelBuilder.createChatEvent();
        when(windowRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo)).thenReturn(chatEvent);
        assertThat(mapAccessibilityNodeInfoToChatEvent.call(nodeInfo), is(chatEvent));
    }
}