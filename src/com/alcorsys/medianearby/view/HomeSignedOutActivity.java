package com.alcorsys.medianearby.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import com.alcorsys.medianearby.R;


public class HomeSignedOutActivity extends FragmentActivity {


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signed_out_home);

        System.out.println("Activity on create called");

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
                  android.R.layout.simple_list_item_1,android.R.id.text1,new String[]{"Home", "Settings"}),

                  // Provide a listener to be called when an item is selected.
                  new ActionBar.OnNavigationListener() {
                      public boolean onNavigationItemSelected(
                              int position, long id) {
                          // Take action here, e.g. switching to the
                          // corresponding fragment.
                          if(position == 1){
                              showSettingsActivity();
                          }else{
                              showHome();
                          }
                          return true;
                      }
                  }
              );
        }

         // Check whether the activity is using the layout version with
        // the fragment_container FrameLayout. If so, we must add the first fragment
        if (findViewById(R.id.fragment_container_home_signed_out) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create an instance of ExampleFragment
            DemoFragment firstFragment = new DemoFragment();

            // In case this activity was started with special instructions from an Intent,
            // pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.fragment_container_home_signed_out, firstFragment);

            transaction.commit();
        }

    }







    public void showRegistrationFragment(){
        // Create fragment and give it an argument specifying the article it should show
        RegistrationFragment newFragment = new RegistrationFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container_home_signed_out, newFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }


    public void showSettingsActivity(){
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }


    public void showHome(){
        // Create fragment and give it an argument specifying the article it should show
        DemoFragment newFragment = new DemoFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container_home_signed_out, newFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    public void showLoginFragment(){
        // Create fragment and give it an argument specifying the article it should show
        LoginFragment newFragment = new LoginFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container_home_signed_out, newFragment);
        transaction.addToBackStack(null);
        // Commit the transaction
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mn_menu_notsigned, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.menu_search_notloggedin).getActionView();
        // Configure the search info and add any event listeners

        return super.onCreateOptionsMenu(menu);
    }



}
