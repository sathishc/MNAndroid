package com.alcorsys.medianearby.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.alcorsys.medianearby.MediaNearbyApplication;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 9/25/12
 * Time: 3:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class ExploreData {

    static final String TAG = "BrowseResultsData";
    public static final String TABLE = "ExploreData";
    
    public static final String id = "id";
    public static final String identifier = "identifier";
    public static final String name = "name";
    public static final String type = "type";
    public static final String userId = "userId";
    public static final String userThumbnail = "userThumbnail";
    public static final String thumbnail = "thumbnail";
    public static final String genre = "genre";
    public static final String ownerName = "ownerName";

    Context context;
    DBHelper dbHelper;
    SQLiteDatabase db;

    public ExploreData(Context context){
        this.context = context;
        dbHelper = new DBHelper();
    }


    class DBHelper extends SQLiteOpenHelper{
        public DBHelper(){
            super(context, MediaNearbyApplication.DB_NAME,null,MediaNearbyApplication.DB_VERSION);
        }


        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }
}
