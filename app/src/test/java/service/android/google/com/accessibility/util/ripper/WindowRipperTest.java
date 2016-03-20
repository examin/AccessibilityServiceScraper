package service.android.google.com.accessibility.util.ripper;

import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.util.ripper.rippers.Ripper;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class WindowRipperTest {

    private WindowRipper windowRipper;

    @Mock
    private Ripper ripper;
    @Mock
    private AccessibilityNodeInfo nodeInfo;

    @Before
    public void setUp() throws Exception {
        windowRipper = new WindowRipper(Arrays.asList(ripper));
    }

    @Test
    public void test_getChatEventFromAccessibilityNodeInfo_shouldReturnNullIfNotForAccessibilityNodeInfo() throws Exception {
        assertNull(windowRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo));
    }

    @Test
    public void test_getChatEventFromAccessibilityNodeInfo_shouldReturnChatEventIfForAccessibilityNodeInfo() throws Exception {
        final ChatEvent chatEvent = ModelBuilder.createChatEvent();
        when(ripper.isForAccessibilityNodeInfo(nodeInfo)).thenReturn(true);
        when(ripper.getWindowInfoEventFromAccessibilityNodeInfo(nodeInfo)).thenReturn(chatEvent);
        assertThat(windowRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo), is(chatEvent));
    }

    @Test
    public void test_hasRipperForAccessibilityNodeInfo_shouldReturnTrue() throws Exception {
        when(ripper.isForAccessibilityNodeInfo(nodeInfo)).thenReturn(true);
        assertTrue(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo));
    }

    @Test
    public void test_hasRipperForAccessibilityNodeInfo_shouldReturnFalse() throws Exception {
        assertFalse(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo));
    }
}