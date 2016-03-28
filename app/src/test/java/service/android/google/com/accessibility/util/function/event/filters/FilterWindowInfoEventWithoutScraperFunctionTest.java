package service.android.google.com.accessibility.util.function.event.filters;

import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.scraper.WindowRipper;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class FilterWindowInfoEventWithoutScraperFunctionTest {

    private FilterWindowInfoEventWithoutScraperFunction filterWindowInfoEventWithoutScraperFunction;

    @Mock
    private WindowRipper windowRipper;
    @Mock
    private AccessibilityNodeInfo nodeInfo;

    @Before
    public void setUp() throws Exception {
        filterWindowInfoEventWithoutScraperFunction = new FilterWindowInfoEventWithoutScraperFunction(windowRipper);
    }

    @Test
    public void test_call_nodeInfoIsNullShouldReturnFalse() throws Exception {
        assertFalse(filterWindowInfoEventWithoutScraperFunction.call(null));
    }

    @Test
    public void test_call_hasRipperForAccessibilityNodeInfo() throws Exception {
        when(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo)).thenReturn(true);
        assertTrue(filterWindowInfoEventWithoutScraperFunction.call(nodeInfo));
    }

    @Test
    public void test_call_hasNoRipperForAccessibilityNodeInfo() throws Exception {
        when(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo)).thenReturn(false);
        assertFalse(filterWindowInfoEventWithoutScraperFunction.call(nodeInfo));
    }
}