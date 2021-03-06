package com.alcorsys.medianearby.view;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.alcorsys.medianearby.R;
import com.alcorsys.medianearby.models.OwnershipInstance;
import com.github.ignition.core.adapters.EndlessListAdapter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class EndlessExploreAdapter extends EndlessListAdapter<OwnershipInstance> {

    static class ViewHolder{
        TextView titleView;
        TextView ownerView;
    }

    public EndlessExploreAdapter(Activity activity, ListView view) {
        super(activity, view, R.layout.explore_row);
    }

    @Override
    protected View doGetView(int position, View convertView, ViewGroup parentView) {
        OwnershipInstance item = getItem(position);
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(getListView().getContext());
            rowView = inflater.inflate(R.layout.explore_row,
                    getListView(), false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.titleView = (TextView)rowView.findViewById(R.id.explore_row_title);
            viewHolder.ownerView = (TextView)rowView.findViewById(R.id.explore_row_ownername);
            rowView.setTag(viewHolder);
        }
        //get the items inside the row and set the values

        ViewHolder holder = (ViewHolder)rowView.getTag();
        holder.titleView.setText(item.getName());
        holder.ownerView.setText(item.getOwnerName());

        /*ImageView imageView = (ImageView)rowView.findViewById(R.id.explore_row_thumbnail);
        //imageView.setImageUrl(item.getThumbnail());
        new ImageDrawTask().execute(imageView,item.getThumbnail());
        //imageView.setImageBitmap();*/

        return rowView;
    }


    public void setImageValue(HashMap<String,Object> returnMap){
        ImageView view = (ImageView)returnMap.get("imageView");
        Bitmap imageIcon = (Bitmap)returnMap.get("imageIcon");
        view.setImageBitmap(imageIcon);
    }

    class ImageDrawTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object... objects) {
            ImageView imageView = (ImageView)objects[0];
            String imageUrlString = (String)objects[1];
            Bitmap imageIcon = null;
            try{
                URL imageURL = new URL(imageUrlString);
                imageIcon = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
            }catch (MalformedURLException mfe){
               //do something
                mfe.printStackTrace();
            }catch (IOException ioe){
               //do something
                ioe.printStackTrace();
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



}

