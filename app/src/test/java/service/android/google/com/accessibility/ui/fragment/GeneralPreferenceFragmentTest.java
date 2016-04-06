package service.android.google.com.accessibility.ui.fragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import service.android.google.com.accessibility.R;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * Created by tim on 06.04.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class GeneralPreferenceFragmentTest {

    private GeneralPreferenceFragment generalPreferenceFragment;

    @Before
    public void setUp() throws Exception {
        generalPreferenceFragment = spy(GeneralPreferenceFragment.class);
    }

    @Test
    public void test_onCreate() throws Exception {
        generalPreferenceFragment.onCreate(null);
        verify(generalPreferenceFragment).addPreferencesFromResource(R.xml.pref_general);
    }
}