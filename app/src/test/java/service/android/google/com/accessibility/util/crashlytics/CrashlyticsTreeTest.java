package service.android.google.com.accessibility.util.crashlytics;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class CrashlyticsTreeTest {

    private CrashlyticsTree crashlyticsTree;

    @Test
    public void test_onCreate() throws Exception {
        crashlyticsTree = new CrashlyticsTree();
    }
}