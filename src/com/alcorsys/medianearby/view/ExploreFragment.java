package com.alcorsys.medianearby.view;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import com.alcorsys.medianearby.MediaNearbyApplication;
import com.alcorsys.medianearby.R;
import com.androidquery.AQuery;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ExploreFragment extends ListFragment {

    public static final String[] FROM = {"name","ownerName","thumbnail"};
    public static final int[] TO = {R.id.explore_row_title,R.id.explore_row_ownername,R.id.explore_row_thumbnail};


    private AQuery aq;
    private Cursor cursor;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.

        ArrayList<HashMap<String,String>> arrayList = MediaNearbyApplication.getBrowseResultsModel().getOwnershipArrayList();
        SimpleAdapter adapter = new SimpleAdapter(getActivity(),arrayList,R.layout.explore_row,FROM,TO);
        adapter.setViewBinder(ImageViewBinder);
        setListAdapter(adapter);
    }


    static final SimpleAdapter.ViewBinder ImageViewBinder = new SimpleAdapter.ViewBinder() {
        public boolean setViewValue(View view, Object o, String s) {
            if(view.getId() != R.id.explore_row_thumbnail) return false;

            ImageView imageView = (ImageView)view;
            new ImageDrawTask().execute(imageView,s);
            return true;  //To change body of implemented methods use File | Settings | File Templates.
        }

        public void setImageValue(HashMap<String,Object> returnMap){
            ImageView view = (ImageView)returnMap.get("imageView");
            Bitmap imageIcon = (Bitmap)returnMap.get("imageIcon");
            view.setImageBitmap(imageIcon);
        }

        class ImageDrawTask extends AsyncTask{

            @Override
            protected Object doInBackground(Object... objects) {
                ImageView imageView = (ImageView)objects[0];
                String imageUrl = (String)objects[1];
                Bitmap imageIcon = null;
                try{
                    URL imageURL = new URL(imageUrl);
                    imageIcon = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
                }catch (MalformedURLException mfe){
                   //do something
                }catch (IOException ioe){
                   //do something
                }

                HashMap<String,Object> returnMap = new HashMap<String, Object>();
                returnMap.put("imageView",imageView);
                returnMap.put("imageIcon",imageIcon);
                return returnMap;
            }

            @Override
            protected void onPostExecute(Object returnMap) {
                super.onPostExecute(returnMap);    //To change body of overridden methods use File | Settings | File Templates.
                setImageValue((HashMap<String,Object>)returnMap);
            }
        }

    };
}
