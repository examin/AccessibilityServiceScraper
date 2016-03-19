package service.android.google.com.accessibility.application;

import com.crashlytics.android.Crashlytics;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.util.crashlytics.CrashlyticsTree;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by tim on 19.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccessibilityApplicationTest {

    @Mock
    CrashlyticsTree crashlyticsTree;
    @Mock
    Crashlytics crashlytics;
    private AccessibilityApplication accessibilityApplication;

    @Before
    public void setUp() throws Exception {
        accessibilityApplication = new AccessibilityApplication();
    }

    @Test
    public void test_getInstance() throws Exception {
        assertThat(AccessibilityApplication.getInstance(), is(accessibilityApplication));
    }
}