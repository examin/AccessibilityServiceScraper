package service.android.google.com.accessibility.rx.util;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by tim on 26.03.16.
 */
public class SchedulerFactoryTest {
    private SchedulerFactory schedulerFactory;

    @Before
    public void setUp() throws Exception {
        schedulerFactory = new SchedulerFactory();
    }

    @Test
    public void test_schedulerIO() throws Exception {
        assertNotNull(schedulerFactory.schedulerIO());
    }
}