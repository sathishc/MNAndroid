package com.alcorsys.medianearby.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.alcorsys.medianearby.R;
import com.alcorsys.medianearby.models.ShelfListInstance;
import com.github.ignition.core.adapters.EndlessListAdapter;

public class EndlessShelfAdapter extends EndlessListAdapter<ShelfListInstance> {

    static class ViewHolder{
        TextView textView;
    }

    public EndlessShelfAdapter(Activity activity, ListView view) {
        super(activity, view, R.layout.explore_row);
    }

    @Override
    protected View doGetView(int position, View convertView, ViewGroup parentView) {
        ShelfListInstance item = getItem(position);
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(getListView().getContext());
            rowView = inflater.inflate(R.layout.explore_row,
                    getListView(), false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textView = (TextView)rowView.findViewById(R.id.explore_row_title);
            rowView.setTag(viewHolder);
        }

        //get the items inside the row and set the values
        ViewHolder holder = (ViewHolder)rowView.getTag();
        holder.textView.setText(item.getMediaItemName());

        //TextView ownerView = (TextView)rowView.findViewById(R.id.explore_row_ownername);
        //ownerView.setText("Owned by - " + item.getOwnerName());

        /*ImageView imageView = (ImageView)rowView.findViewById(R.id.explore_row_thumbnail);
        //imageView.setImageUrl(item.getThumbnail());
        new ImageDrawTask().execute(imageView,item.getThumbnail());
        //imageView.setImageBitmap();*/

        return rowView;
    }


    /*public void setImageValue(HashMap<String,Object> returnMap){
        ImageView view = (ImageView)returnMap.get("imageView");
        Bitmap imageIcon = (Bitmap)returnMap.get("imageIcon");
        view.setImageBitmap(imageIcon);
    }*/

/*
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
*/



}

