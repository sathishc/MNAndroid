package com.alcorsys.medianearby.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.alcorsys.medianearby.MediaNearbyApplication;
import com.alcorsys.medianearby.models.LoggedInStatus;
import com.alcorsys.medianearby.models.LoginRequest;
import com.alcorsys.medianearby.models.LoginStatus;
import com.alcorsys.medianearby.rest.RestClient;
import org.springframework.web.client.HttpStatusCodeException;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);

        if(((MediaNearbyApplication)getApplication()).isAccountCreated()){
            new LoginTask().execute();  //Login on a worker thread - StartHomepage called of successful
        }else{
            startHomeSignedOut();
        }
    }

    public void startHomeSignedIn(){
        Intent intent = new Intent(this,HomeSignedInActivity.class);
        startActivity(intent);
    }

    public void startHomeSignedOut(){
        Intent intent = new Intent(this, HomeSignedOutActivity.class);
        startActivity(intent);
    }






    private class LoginTask extends AsyncTask<String,Void,Boolean> {
            @Override
            protected Boolean doInBackground(String... strings) {
                String user_email = MediaNearbyApplication.mediaNearbyPreferences.getString("user_name", "");
                String user_password = MediaNearbyApplication.mediaNearbyPreferences.getString("user_password", "");
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
                return loggedInStatus.isLoggedIn();
            }

            @Override
            protected void onPostExecute(Boolean aBoolean) {
                super.onPostExecute(aBoolean);    //To change body of overridden methods use File | Settings | File Templates.
                if(aBoolean){
                    startHomeSignedIn();
                }else{
                    startHomeSignedOut();
                }
            }
        }
}
