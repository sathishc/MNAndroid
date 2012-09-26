package com.alcorsys.medianearby.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.alcorsys.medianearby.MediaNearbyApplication;
import com.alcorsys.medianearby.models.BrowseResultsModel;
import com.alcorsys.medianearby.models.LoggedInStatus;
import com.alcorsys.medianearby.models.LoginRequest;
import com.alcorsys.medianearby.models.LoginStatus;
import com.alcorsys.medianearby.rest.RestClient;
import org.springframework.web.client.HttpStatusCodeException;

public class MainActivity extends Activity {

    private boolean signedIn = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        if(((MediaNearbyApplication)getApplication()).isAccountCreated()){
            //get the user name and password
            String user_name = MediaNearbyApplication.mediaNearbyPreferences.getString("user_name", "");
            String user_password = MediaNearbyApplication.mediaNearbyPreferences.getString("user_password", "");
            LoginTask task = new LoginTask();
            task.execute(user_name, user_password);

        }else{
            Intent intent = new Intent(this, HomeSignedOutActivity.class);
            startActivity(intent);
        }
    }

    public void startHomePage(){

        System.out.println("Starting home and isSignedIn is " + isSignedIn());

        if(isSignedIn()){
            Intent intent = new Intent(this,HomeSignedInActivity.class);
            new BrowseItemsTask().execute("");
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, HomeSignedOutActivity.class);
            startActivity(intent);
        }
    }


    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }



    private class LoginTask extends AsyncTask<String,Void,Boolean> {
            @Override
            protected Boolean doInBackground(String... strings) {

                String user_email = strings[0];
                String user_password = strings[1];

                RestClient client = MediaNearbyApplication.getRestClient();

                String url =  client.serverUrl() + MediaNearbyApplication.SIGN_IN_URL;
                LoginStatus loginStatus = null;
                try{
                    loginStatus = client.template().postForObject(url, new LoginRequest("", ""), LoginStatus.class, user_email, user_password);
                    System.out.println("Log in Status is " + loginStatus.getStatus() + "\n\n");
                }catch (HttpStatusCodeException stcExc){
                    loginStatus = null;
                }

                if(loginStatus == null){
                    return false;
                }

                if(!loginStatus.getStatus().equals("success")){
                    return false;
                }

                url =  client.serverUrl() + MediaNearbyApplication.IS_LOGGED_IN_URL;

                LoggedInStatus loggedInStatus = client.template().getForObject(url, LoggedInStatus.class);

                System.out.println("Logged In Status is " + loggedInStatus.isLoggedIn() + "\n\n");

                return loggedInStatus.isLoggedIn();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);    //To change body of overridden methods use File | Settings | File Templates.
                setSignedIn(aBoolean);
                startHomePage();
            }
        }


    private class BrowseItemsTask extends AsyncTask<String,Void,Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {

            boolean callSuccess = false;

            RestClient client = MediaNearbyApplication.getRestClient();

            String url =  client.serverUrl() + MediaNearbyApplication.BROWSE_ITEMS_URL;
            BrowseResultsModel resultsModel = null;
            try{
                resultsModel = client.template().postForObject(url,"",BrowseResultsModel.class,"Books","All","All","All");

                System.out.println("Results from server " + resultsModel);

                MediaNearbyApplication.setBrowseResultsModel(resultsModel);
                callSuccess = true;
            }catch (HttpStatusCodeException stcExc){
                callSuccess = false;
            }



            return callSuccess;
        }
    }

}
