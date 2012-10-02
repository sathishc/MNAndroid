package com.alcorsys.medianearby;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.alcorsys.medianearby.models.BrowseResultsModel;
import com.alcorsys.medianearby.rest.RestClient;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/22/12
 * Time: 8:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class MediaNearbyApplication extends Application {


    public static final String SIGN_IN_URL = "/user/emailLogin/?user_email={user_email}&user_password={user_password}"; //user/emailLogin/
    public static final String IS_LOGGED_IN_URL = "/user/isLoggedIn/"; //user/isLoggedIn
    public static final String BROWSE_ITEMS_URL = "/browse/items/?exploreMediaType={exploreMediaType}&offset={offset}&explorePlace={explorePlace}&explorePerson={explorePerson}&exploreGenre={exploreGenre}";
    public static final String SHELF_ITEMS_URL = "/my/shelf/";
    public static final String WISH_ITEMS_URL = "/my/wishlist/";

    //Database Name and version
    public static final String DB_NAME = "MediaNearby_DB";
    public static final int DB_VERSION = 1;



    private boolean accountCreated = false; //set to true if we find an available acount in the system
    public static SharedPreferences mediaNearbyPreferences;
    private static BrowseResultsModel browseResultsModel;

    private static RestClient client;


    @Override
    public void onCreate() {
        super.onCreate();    //To change body of overridden methods use File | Settings | File Templates.
        System.out.println("Application on create called");

        mediaNearbyPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user_name = mediaNearbyPreferences.getString("user_name","");
        String user_password = mediaNearbyPreferences.getString("user_password","");

        if(!user_name.equals("") && !user_password.equals("")){
            setAccountCreated(true);
        }
    }

    public boolean isAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(boolean accountCreated) {
        this.accountCreated = accountCreated;
    }

    public static BrowseResultsModel getBrowseResultsModel() {
        if(browseResultsModel == null){
            browseResultsModel = new BrowseResultsModel();
        }
        return browseResultsModel;
    }

    public static void setBrowseResultsModel(BrowseResultsModel model) {
        browseResultsModel = model;
    }

    public static RestClient getRestClient(){
        if(client == null){
            client = new RestClient();
        }
        return client;
    }
}


