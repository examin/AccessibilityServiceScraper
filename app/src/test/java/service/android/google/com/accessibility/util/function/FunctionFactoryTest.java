package service.android.google.com.accessibility.util.function;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.scraper.WindowRipper;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class FunctionFactoryTest {
    private FunctionFactory functionFactory;

    @Mock
    private EventExtractor eventExtractor;
    @Mock
    private WindowRipper windowRipper;
    @Mock
    private RxDatabase rxDatabase;

    @Before
    public void setUp() throws Exception {
        functionFactory = new FunctionFactory();
    }

    @Test
    public void testGetMapAccessibilityEventToEventFunction() throws Exception {
        assertNotNull(functionFactory.getMapAccessibilityEventToEventFunction(eventExtractor));
    }

    @Test
    public void testFilterAccessibilityEventFunction() throws Exception {
        assertNotNull(functionFactory.filterAccessibilityEventFunction());
    }

    @Test
    public void testFilterWindowInfoEventFunction() throws Exception {
        assertNotNull(functionFactory.filterWindowInfoEventFunction(windowRipper));
    }

    @Test
    public void testGetMapAccessibilityNodeInfoToChatEvent() throws Exception {
        assertNotNull(functionFactory.getMapAccessibilityNodeInfoToChatEvent(windowRipper));
    }

    @Test
    public void testFilterNullChatEventsFunction() throws Exception {
        assertNotNull(functionFactory.filterNullChatEventsFunction());
    }
}