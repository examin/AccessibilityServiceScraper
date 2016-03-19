package service.android.google.com.accessibility.rx;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.controller.AccessibilityServiceController;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class ObserverFactoryTest {

    private ObserverFactory observerFactory;

    @Mock
    private AccessibilityServiceController controller;

    @Before
    public void setUp() throws Exception {
        observerFactory = new ObserverFactory();
    }

    @Test
    public void test_createEventSubscriber() throws Exception {
        assertNotNull(observerFactory.createEventSubscriber(controller));
    }

    @Test
    public void test_createWindowInfoEventSubscriber() throws Exception {
        assertNotNull(observerFactory.createWindowInfoEventSubscriber(controller));
    }
}