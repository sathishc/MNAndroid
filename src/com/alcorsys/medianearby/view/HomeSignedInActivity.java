package com.alcorsys.medianearby.view;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.ArrayAdapter;
import com.alcorsys.medianearby.R;

public class HomeSignedInActivity extends FragmentActivity {


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signed_in_home);
    }

    @Override
    protected void onResume() {
        super.onResume();    //To change body of overridden methods use File | Settings | File Templates.

        // Create an instance of ExampleFragment
        EndlessExploreFragment firstFragment = new EndlessExploreFragment();

        // In case this activity was started with special instructions from an Intent,
        // pass the Intent's extras to the fragment as arguments
        firstFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.fragment_container_home_signed_in, firstFragment);

        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //ActionBar setup
        // Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // For the main activity, make sure the app icon in the action bar
            // does not behave as a button
            ActionBar actionBar = getActionBar();
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setListNavigationCallbacks(
            // Specify a SpinnerAdapter to populate the dropdown list.
            new ArrayAdapter(actionBar.getThemedContext(),
                android.R.layout.simple_list_item_1,android.R.id.text1,new String[]{"Home", "Lists", "Inbox"}),

                // Provide a listener to be called when an item is selected.
                new ActionBar.OnNavigationListener() {
                    public boolean onNavigationItemSelected(
                            int position, long id) {
                        // Take action here, e.g. switching to the
                        // corresponding fragment.
                        return true;
                    }
                }
            );
        }

        getMenuInflater().inflate(R.menu.mn_menu, menu);
        //SearchView searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        // Configure the search info and add any event listeners


        return super.onCreateOptionsMenu(menu);
    }
}
