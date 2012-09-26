package com.alcorsys.medianearby.view;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import com.alcorsys.medianearby.R;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/22/12
 * Time: 9:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class MediaNearbyPreferenceFragment extends PreferenceFragment {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}