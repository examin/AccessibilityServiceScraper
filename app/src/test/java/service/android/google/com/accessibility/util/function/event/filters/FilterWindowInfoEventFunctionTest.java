package service.android.google.com.accessibility.util.function.event.filters;

import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.util.ripper.WindowRipper;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class FilterWindowInfoEventFunctionTest {

    private FilterWindowInfoEventFunction filterWindowInfoEventFunction;

    @Mock
    private WindowRipper windowRipper;
    @Mock
    private AccessibilityNodeInfo nodeInfo;

    @Before
    public void setUp() throws Exception {
        filterWindowInfoEventFunction = new FilterWindowInfoEventFunction(windowRipper);
    }

    @Test
    public void test_call_nodeInfoIsNullShouldReturnFalse() throws Exception {
        assertFalse(filterWindowInfoEventFunction.call(null));
    }

    @Test
    public void test_call_hasRipperForAccessibilityNodeInfo() throws Exception {
        when(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo)).thenReturn(true);
        assertTrue(filterWindowInfoEventFunction.call(nodeInfo));
    }

    @Test
    public void test_call_hasNoRipperForAccessibilityNodeInfo() throws Exception {
        when(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo)).thenReturn(false);
        assertFalse(filterWindowInfoEventFunction.call(nodeInfo));
    }
}