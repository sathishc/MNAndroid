package com.alcorsys.medianearby.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AbsListView;
import com.alcorsys.medianearby.MediaNearbyApplication;
import com.alcorsys.medianearby.R;
import com.alcorsys.medianearby.models.BrowseResultsModel;
import com.alcorsys.medianearby.models.OwnershipInstance;
import com.alcorsys.medianearby.rest.RestClient;
import org.springframework.web.client.HttpStatusCodeException;

public class EndlessExploreFragment extends ListFragment implements AbsListView.OnScrollListener {

    private static final int PAGE_SIZE = 20;
    private EndlessExploreAdapter adapter;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        adapter = new EndlessExploreAdapter(getActivity(),getListView());
        loadNextPage();
        setListAdapter(adapter);
        getListView().setOnScrollListener(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.mn_menu_browse, menu);
        super.onCreateOptionsMenu(menu, inflater);    //To change body of overridden methods use File | Settings | File Templates.
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
            BrowseResultsModel resultsModel = null;
            try{
                resultsModel = client.template().postForObject(url,"",BrowseResultsModel.class,"Books",offset,"All","All","All");
                System.out.println("Results from server " + resultsModel);
                OwnershipInstance[] instances = resultsModel.getResults();
                for (OwnershipInstance instance : instances) {
                    adapter.getData().add(instance);
                }
                callSuccess = true;
            }catch (HttpStatusCodeException stcExc){
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
}
