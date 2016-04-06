package service.android.google.com.accessibility.storage;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by tim on 05.04.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class CupboardDbHelperTest {

    private CupboardDbHelper cupboardDbHelper;

    @Mock
    private Context context;

    @Test
    public void test_onCreate() throws Exception {
        cupboardDbHelper = new CupboardDbHelper(context);
    }
}