package com.alcorsys.medianearby.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.*;
import android.widget.AbsListView;
import android.widget.ListView;
import com.alcorsys.medianearby.MediaNearbyApplication;
import com.alcorsys.medianearby.R;
import com.alcorsys.medianearby.models.BrowseResultsModel;
import com.alcorsys.medianearby.models.OwnershipInstance;
import com.alcorsys.medianearby.rest.RestClient;
import org.springframework.web.client.HttpStatusCodeException;

public class EndlessExploreFragment extends ListFragment implements AbsListView.OnScrollListener {

    private static final int PAGE_SIZE = 20;
    private EndlessExploreAdapter adapter;
    private ActionMode mActionMode;

    private String exploreItemType;
    private BrowseResultsModel resultsModel;


    public EndlessExploreFragment(String exploreItemType){
        this.exploreItemType = exploreItemType;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        adapter = new EndlessExploreAdapter(getActivity(),getListView());
        loadNextPage();
        setListAdapter(adapter);
        getListView().setOnScrollListener(this);
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

    private void loadNextPage() {
        adapter.setIsLoadingData(true);
        new BrowseItemsTask().execute("");
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        //To change body of implemented methods use File | Settings | File Templates.

    }

    public void onScroll(AbsListView absListView,  int firstVisibleItem, int visibleItemCount,
                int totalItemCount) {
        //To change body of implemented methods use File | Settings | File Templates.
        if (adapter.shouldRequestNextPage(firstVisibleItem, visibleItemCount, totalItemCount)) {
           loadNextPage();
        }
    }


    class BrowseItemsTask extends AsyncTask<String,Void,Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {

            boolean callSuccess = false;
            RestClient client = MediaNearbyApplication.getRestClient();
            int offset = adapter.getCount();

            String url =  client.serverUrl() + MediaNearbyApplication.BROWSE_ITEMS_URL;
            try{
                resultsModel = client.template().postForObject(url,"",BrowseResultsModel.class,exploreItemType,offset,"All","All","All");
                System.out.println("Results from server " + resultsModel);
                OwnershipInstance[] instances = resultsModel.getResults();
                for (OwnershipInstance instance : instances) {
                    adapter.getData().add(instance);
                }
                callSuccess = true;
            }catch (HttpStatusCodeException stcExc){
                stcExc.printStackTrace();
                callSuccess = false;
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
            inflater.inflate(R.menu.mn_menu_browse, menu);
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
                case R.id.menu_browse_shelf:
                    //shareCurrentItem();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.menu_browse_borrow:
                    //shareCurrentItem();
                    mode.finish(); // Action picked, so close the CAB
                    return true;
                case R.id.menu_browse_wish:
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
