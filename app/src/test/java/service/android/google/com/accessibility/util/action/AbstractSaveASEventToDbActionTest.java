package service.android.google.com.accessibility.util.action;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import nl.nl2312.rxcupboard.RxDatabase;
import service.android.google.com.accessibility.model.ASEvent;
import service.android.google.com.accessibility.model.EventType;
import service.android.google.com.accessibility.model.database.AbstractDBObject;

import static org.mockito.Mockito.verify;

/**
 * Created by tim on 27.03.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractSaveASEventToDbActionTest {

    private MockAbstractSaveAsEventToDbAction abstractSaveAsEventToDbAction;
    @Mock
    private RxDatabase rxDatabase;

    @Before
    public void setUp() throws Exception {
        abstractSaveAsEventToDbAction = new MockAbstractSaveAsEventToDbAction(rxDatabase);
    }

    @Test
    public void test_save() throws Exception {
        final MockAsEventDTO mappedObject = new MockAsEventDTO();
        abstractSaveAsEventToDbAction.save(mappedObject);
        verify(rxDatabase).put(mappedObject);
    }

    class MockAbstractSaveAsEventToDbAction extends AbstractSaveASEventToDbAction<MockAsEvent, MockAsEventDTO> {

        public MockAbstractSaveAsEventToDbAction(RxDatabase rxDatabase) {
            super(rxDatabase);
        }

        @Override
        public void call(MockAsEvent accessibilityEvent) {

        }
    }

    class MockAsEvent implements ASEvent {
    }

    class MockAsEventDTO extends AbstractDBObject {
        @Override
        public EventType accessibilityEventType() {
            return null;
        }
    }
}