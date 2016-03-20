package service.android.google.com.accessibility.util.ripper;

import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import service.android.google.com.accessibility.util.ripper.rippers.Ripper;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
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
    public void test_hasRipperForAccessibilityNodeInfo_shouldReturnTrue() throws Exception {
        when(ripper.isForAccessibilityNodeInfo(nodeInfo)).thenReturn(true);
        assertTrue(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo));
    }

    @Test
    public void test_hasRipperForAccessibilityNodeInfo_shouldReturnFalse() throws Exception {
        assertFalse(windowRipper.hasRipperForAccessibilityNodeInfo(nodeInfo));
    }
}