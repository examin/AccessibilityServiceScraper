package service.android.google.com.accessibility.util.function.event.filters;

import org.junit.Before;
import org.junit.Test;

import service.android.google.com.accessibility.ModelBuilder;
import service.android.google.com.accessibility.model.ChatEvent;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author Created by trijckaert
 */
public class FilterNullChatEventsFunctionTest {

    private FilterNullChatEventsFunction filterNullChatEventsFunction;

    private ChatEvent chatEvent = ModelBuilder.createChatEvent();

    @Before
    public void setUp() throws Exception {
        filterNullChatEventsFunction = new FilterNullChatEventsFunction();
    }

    @Test
    public void test_call_chatEventIsNull_shouldReturnFalse() throws Exception {
        assertFalse(filterNullChatEventsFunction.call(null));
    }

    @Test
    public void test_call_chatEventIsNull_shouldReturnTrue() throws Exception {
        assertTrue(filterNullChatEventsFunction.call(chatEvent));
    }
}