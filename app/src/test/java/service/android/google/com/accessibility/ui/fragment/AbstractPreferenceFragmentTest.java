package service.android.google.com.accessibility.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.MenuItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tim on 05.04.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class AbstractPreferenceFragmentTest {
    private AbstractPreferenceFragment abstractPreferenceFragment;

    @Mock
    private MenuItem menuItem;
    @Mock
    private Activity activity;

    @Before
    public void setUp() throws Exception {
        abstractPreferenceFragment = spy(AbstractPreferenceFragment.class);
        doReturn(activity).when(abstractPreferenceFragment).getActivity();
    }

    @Test
    public void test_onCreate_shouldSetHasOptionsMenuToTrue() throws Exception {
        abstractPreferenceFragment.onCreate(null);
        verify(abstractPreferenceFragment).setHasOptionsMenu(true);
    }

    @Test
    public void test_onOptionsItemSelected_shouldReturnFalseIfIdWasNotHome() throws Exception {
        abstractPreferenceFragment.onCreate(null);
        assertFalse(abstractPreferenceFragment.onOptionsItemSelected(menuItem));
    }

    @Test
    public void test_onOptionsItemSelected_shouldNotStartActivityIfIdWasNotHome() throws Exception {
        when(menuItem.getItemId()).thenReturn(-1);
        abstractPreferenceFragment.onOptionsItemSelected(menuItem);
        verify(abstractPreferenceFragment, never()).startActivity(any(Intent.class));
    }

    @Test
    public void test_onOptionsItemSelected_shouldStartActivity() throws Exception {
        when(menuItem.getItemId()).thenReturn(android.R.id.home);
        abstractPreferenceFragment.onOptionsItemSelected(menuItem);
        verify(abstractPreferenceFragment).startActivity(any(Intent.class));
    }

    @Test
    public void test_onOptionsItemSelected_shouldReturnTrue() throws Exception {
        when(menuItem.getItemId()).thenReturn(android.R.id.home);
        assertTrue(abstractPreferenceFragment.onOptionsItemSelected(menuItem));
    }
}