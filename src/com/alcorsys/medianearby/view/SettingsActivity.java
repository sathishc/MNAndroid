package com.alcorsys.medianearby.view;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/22/12
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class SettingsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MediaNearbyPreferenceFragment())
                .commit();
    }
}