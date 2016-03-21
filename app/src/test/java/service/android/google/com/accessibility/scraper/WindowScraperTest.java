package service.android.google.com.accessibility.scraper;

import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.scraper.scrapers.Scraper;

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
public class WindowScraperTest {

    private WindowRipper windowRipper;

    @Mock
    private Scraper scraper;
    @Mock
    private AccessibilityNodeInfo nodeInfo;

    @Before
    public void setUp() throws Exception {
        windowRipper = new WindowRipper(Arrays.asList(scraper));
    }

    @Test
    public void test_getChatEventFromAccessibilityNodeInfo_shouldReturnNullIfNotForAccessibilityNodeInfo() throws Exception {
        assertNull(windowRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo));
    }

    @Test
    public void test_getChatEventFromAccessibilityNodeInfo_shouldReturnChatEventIfForAccessibilityNodeInfo() throws Exception {
        final ChatEvent chatEvent = ModelBuilder.createChatEvent();
        when(scraper.isForAccessibilityNodeInfo(nodeInfo)).thenReturn(true);
        when(scraper.getChatEventFromAccessibilityNodeInfo(nodeInfo)).thenReturn(chatEvent);
        assertThat(windowRipper.getChatEventFromAccessibilityNodeInfo(nodeInfo), is(chatEvent));
    }

    @Test
    public void test_hasRipperForAccessibilityNodeInfo_shouldReturnTrue() throws Exception {
        when(scraper.isForAccessibilityNodeInfo(nodeInfo)).thenReturn(true);
        assertTrue(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo));
    }

    @Test
    public void test_hasRipperForAccessibilityNodeInfo_shouldReturnFalse() throws Exception {
        assertFalse(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo));
    }
}