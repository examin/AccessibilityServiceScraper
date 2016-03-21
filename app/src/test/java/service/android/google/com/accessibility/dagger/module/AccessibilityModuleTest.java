package service.android.google.com.accessibility.dagger.module;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.AS;
import service.android.google.com.accessibility.extractor.EventExtractor;
import service.android.google.com.accessibility.rx.ObservableFactory;
import service.android.google.com.accessibility.rx.ObserverFactory;
import service.android.google.com.accessibility.scraper.WindowRipper;
import service.android.google.com.accessibility.util.function.FunctionFactory;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class AccessibilityModuleTest {

    private AccessibilityModule accessibilityModule;

    @Mock
    private AS accessibilityService;
    @Mock
    private ObservableFactory observableFactory;
    @Mock
    private ObserverFactory observerFactory;
    @Mock
    private EventExtractor eventExtractor;
    @Mock
    private WindowRipper windowRipper;
    @Mock
    private FunctionFactory functionFactory;

    @Before
    public void setUp() throws Exception {
        accessibilityModule = new AccessibilityModule(accessibilityService);
    }

    @Test
    public void test_accessibilityService() throws Exception {
        assertThat(accessibilityModule.accessibilityService(), is(accessibilityService));
    }

    @Test
    public void test_accessibilityServiceController() throws Exception {
        assertNotNull(
                accessibilityModule.accessibilityServiceController(
                        observableFactory,
                        observerFactory,
                        eventExtractor,
                        windowRipper)
        );
    }

    @Test
    public void test_observableFactory() throws Exception {
        assertNotNull(
                accessibilityModule.observableFactory(
                        functionFactory,
                        observerFactory
                )
        );
    }

    @Test
    public void test_functionFactory() throws Exception {
        assertNotNull(
                accessibilityModule.functionFactory()
        );
    }

    @Test
    public void test_eventExtractor() throws Exception {
        assertNotNull(
                accessibilityModule.eventExtractor()
        );
    }

    @Test
    public void test_windowRipper() throws Exception {
        assertNotNull(
                accessibilityModule.windowRipper()
        );
    }

    @Test
    public void test_observerFactory() throws Exception {
        assertNotNull(
                accessibilityModule.observerFactory()
        );

    }
}