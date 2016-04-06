package service.android.google.com.accessibility.rx;

import android.content.res.Resources;

import com.github.pwittchen.prefser.library.Prefser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.AS;

import static junit.framework.Assert.assertNotNull;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class ObserverFactoryTest {

    private ObserverFactory observerFactory;

    @Mock
    private AS accessibilityService;
    @Mock
    private Resources resources;
    @Mock
    private Prefser prefser;

    @Before
    public void setUp() throws Exception {
        observerFactory = new ObserverFactory(accessibilityService);
    }

    @Test
    public void test_createEventSubscriber() throws Exception {
        assertNotNull(observerFactory.createEventSubscriber());
    }

    @Test
    public void test_createWindowInfoEventSubscriber() throws Exception {
        assertNotNull(observerFactory.createWindowInfoEventSubscriber());
    }

    @Test
    public void test_createToggleEventSubscriber() throws Exception {
        assertNotNull(observerFactory.createToggleEventSubscriber(resources, prefser));
    }
}