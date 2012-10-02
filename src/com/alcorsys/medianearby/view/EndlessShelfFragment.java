package com.alcorsys.medianearby.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.*;
import android.widget.ListView;
import com.alcorsys.medianearby.R;
import com.alcorsys.medianearby.models.ShelfListInstance;
import com.alcorsys.medianearby.models.ShelfListModel;

public class EndlessShelfFragment extends ListFragment {

    private static final int PAGE_SIZE = 20;
    private EndlessShelfAdapter adapter;
    private ActionMode mActionMode;
    private String shelfItemType;
    private ShelfListModel resultsModel;

    public EndlessShelfFragment(String shelfItemType, ShelfListModel shelfListModel){
        this.shelfItemType = shelfItemType;
        this.resultsModel = shelfListModel;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        adapter = new EndlessShelfAdapter(getActivity(),getListView());
        setListAdapter(adapter);
        //new PopulateShelfItemsTask().execute("");

        ShelfListInstance[] instances = resultsModel.getOwnershipInstanceList();
        for (ShelfListInstance instance : instances) {
           if(instance.getMediaItemType().equals(shelfItemType)){
               adapter.getData().add(instance);
           }
        }
        adapter.setIsLoadingData(false);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);    //To change body of overridden methods use File | Settings | File Templates.
        if (mActionMode == null) {
            // Start the CAB using the ActionMode.Callback defined above
            mActionMode = getActivity().startActionMode(mActionModeCallback);
            view.setSelected(true);
        }
    }


    class PopulateShelfItemsTask extends AsyncTask<String,Void,Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {

           boolean callSuccess = false;
           ShelfListInstance[] instances = resultsModel.getOwnershipInstanceList();
           for (ShelfListInstance instance : instances) {
               if(instance.getMediaItemType().equals(shelfItemType)){
                   adapter.getData().add(instance);
               }
           }
           return callSuccess;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);    //To change body of overridden methods use File | Settings | File Templates.
            adapter.setIsLoadingData(false);
            adapter.notifyDataSetChanged();
        }
    }



    private ActionMode.Callback mActionModeCallback = new ActionMode.Callback() {

        // Called when the action mode is created; startActionMode() was called
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            // Inflate a menu resource providing context menu items
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.mn_menu_list, menu);
            return true;
        }

        // Called each time the action mode is shown. Always called after onCreateActionMode, but
        // may be called multiple times if the mode is invalidated.
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false; // Return false if nothing is done
        }

        // Called when the user selects a contextual menu item
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_list_add_item:
                    //shareCurrentItem();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                default:
                    return false;
            }
        }

        // Called when the user exits the action mode
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode = null;
        }
    };
}
