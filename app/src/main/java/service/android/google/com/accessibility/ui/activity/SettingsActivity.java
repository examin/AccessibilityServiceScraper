package service.android.google.com.accessibility.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.preference.PreferenceFragment;

import java.util.List;

import service.android.google.com.accessibility.R;
import service.android.google.com.accessibility.ui.fragment.DataSyncPreferenceFragment;
import service.android.google.com.accessibility.ui.fragment.GeneralPreferenceFragment;

public class SettingsActivity extends AppCompatPreferenceActivity {
    @Override
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.pref_headers, target);
    }

    protected boolean isValidFragment(String fragmentName) {
        return PreferenceFragment.class.getName().equals(fragmentName)
                || GeneralPreferenceFragment.class.getName().equals(fragmentName)
                || DataSyncPreferenceFragment.class.getName().equals(fragmentName);
    }
}