package service.android.google.com.accessibility.util.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import nl.nl2312.rxcupboard.RxDatabase;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by tim on 27.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class ActionFactoryTest {

    private ActionFactory actionFactory;

    @Mock
    private RxDatabase rxDatabase;

    @Before
    public void setUp() throws Exception {
        actionFactory = new ActionFactory();
    }

    @Test
    public void test_saveEventToDbAction() throws Exception {
        assertNotNull(actionFactory.saveEventToDbAction(rxDatabase));
    }

    @Test
    public void test_saveChatEventToDbFunction() throws Exception {
        assertNotNull(actionFactory.saveChatEventToDbFunction(rxDatabase));
    }
}