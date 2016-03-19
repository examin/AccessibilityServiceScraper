package service.android.google.com.accessibility;

import android.view.accessibility.AccessibilityEvent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.controller.AccessibilityServiceController;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 19.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccessibilityServiceTest {

    private AccessibilityService accessibilityService;

    @Mock
    private AccessibilityServiceController accessibilityServiceController;
    @Mock
    private android.view.accessibility.AccessibilityNodeInfo activeWindowNodeInfo;
    @Mock
    private AccessibilityEvent accessibilityEvent;

    @Before
    public void setUp() throws Exception {
        accessibilityService = spy(new AccessibilityService());
        accessibilityService.accessibilityServiceController = accessibilityServiceController;
    }

    @Test
    public void test_onAccessibilityEvent() throws Exception {
        when(accessibilityService.getRootInActiveWindow()).thenReturn(activeWindowNodeInfo);
        ArgumentCaptor<AccessibilityEvent> accessibilityEventArgumentCaptor = ArgumentCaptor.forClass(AccessibilityEvent.class);
        accessibilityService.onAccessibilityEvent(accessibilityEvent);
        verify(accessibilityServiceController).evaluateEvent(eq(activeWindowNodeInfo), accessibilityEventArgumentCaptor.capture());
        assertThat(accessibilityEventArgumentCaptor.getValue(), is(accessibilityEvent));
    }
}