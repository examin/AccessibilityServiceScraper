package service.android.google.com.accessibility.ui.fragment;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import service.android.google.com.accessibility.R;

/**
 * @author Created by trijckaert
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DataSyncPreferenceFragment extends AbstractPreferenceFragment {
    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_data_sync);
    }
}