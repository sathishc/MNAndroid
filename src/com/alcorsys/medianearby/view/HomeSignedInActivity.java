package com.alcorsys.medianearby.view;

import android.app.ActionBar;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
        //displayFragment("Explore");
    }

    public void displayFragment(String fragmentName){

        Fragment fragment;
        // Create an instance of ExampleFragment
        if(fragmentName.equals("Explore")){
            fragment = new ExploreTabsFragment();
        }else if(fragmentName.equals("Lists")){
            fragment = new ShelfTabsFragment();
        }else{
            fragment = new DemoFragment();
        }

        // Add the fragment to the 'fragment_container' FrameLayout
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container_home_signed_in, fragment,fragmentName);
        transaction.addToBackStack(null);
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
                        if(position == 0){
                            displayFragment("Explore");
                        }else if(position == 1){
                            displayFragment("Lists");
                        }else{
                            displayFragment("Inbox");
                        }
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
