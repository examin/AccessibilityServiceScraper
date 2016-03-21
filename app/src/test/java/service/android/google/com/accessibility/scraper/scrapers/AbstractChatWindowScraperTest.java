package service.android.google.com.accessibility.scraper.scrapers;

import android.view.accessibility.AccessibilityNodeInfo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.model.ChatEvent;
import service.android.google.com.accessibility.model.Person;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractChatWindowScraperTest {

    private final String packageName = "com.mock.package";
    private AbstractChatWindowScraper abstractChatWindowRipper;
    @Mock
    private AccessibilityNodeInfo nodeInfo;

    @Before
    public void setUp() throws Exception {
        abstractChatWindowRipper = new MockChatWindowScraper(packageName);
    }

    @Test
    public void testGetFQResID() throws Exception {
        final String res_id = "res_id";
        final String fqResId = this.packageName + ":id/" + res_id;
        final String fqResID = abstractChatWindowRipper.getFQResID(res_id);
        assertThat(fqResID, is(fqResId));
    }

    @Test
    public void test_getYou() throws Exception {
        final Person you = abstractChatWindowRipper.getYou();
        assertThat(you.fullName(), is("You"));
    }

    @Test
    public void testGetContactPersonFromName() throws Exception {
        String contactName = "John Doe";
        abstractChatWindowRipper.setContactPersonFromName(contactName);
        assertThat(abstractChatWindowRipper.getContactPerson().fullName(), is(contactName));
    }

    @Test
    public void testIsForAccessibilityNodeInfo_shouldReturnTrueIfCorrectPackage() throws Exception {
        when(nodeInfo.getPackageName()).thenReturn(packageName);
        assertTrue(abstractChatWindowRipper.isForAccessibilityNodeInfo(nodeInfo));
    }

    @Test
    public void testIsForAccessibilityNodeInfo_shouldReturnFalseIfNotCorrectPackage() throws Exception {
        when(nodeInfo.getPackageName()).thenReturn("some.other.package.name");
        assertFalse(abstractChatWindowRipper.isForAccessibilityNodeInfo(nodeInfo));
    }

    @Test
    public void testIsForAccessibilityNodeInfo_shouldReturnFalseIfNullAccessibilityNodeInfo() throws Exception {
        assertFalse(abstractChatWindowRipper.isForAccessibilityNodeInfo(null));
    }

    class MockChatWindowScraper extends AbstractChatWindowScraper {

        public MockChatWindowScraper(String packageName) {
            super(packageName);
        }

        @Override
        public ChatEvent getChatEventFromAccessibilityNodeInfo(AccessibilityNodeInfo nodeInfo) {
            return null;
        }
    }
}