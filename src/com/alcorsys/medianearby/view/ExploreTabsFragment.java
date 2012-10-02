package com.alcorsys.medianearby.view;

/**
 * Created with IntelliJ IDEA.
 * User: SatSang
 * Date: 10/1/12
 * Time: 8:02 PM
 * To change this template use File | Settings | File Templates.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import com.alcorsys.medianearby.R;

public class ExploreTabsFragment extends Fragment implements OnTabChangeListener {

    private static final String TAG = "ExploreFragmentTabs";
    public static final String TAB_BOOKS = "Books";
    public static final String TAB_MOVIES = "Video";
    public static final String TAB_MUSIC = "Music";
    public static final String TAB_GAMES = "Game";

    private View mRoot;
    private TabHost mTabHost;
    private int mCurrentTab;
    private Fragment booksFragment,videoFragment,musicFragment,gameFragment;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.list_explore_tabs, null);
        mTabHost = (TabHost) mRoot.findViewById(android.R.id.tabhost);
        setupTabs();
        return mRoot;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);

        mTabHost.setOnTabChangedListener(this);
        mTabHost.setCurrentTab(mCurrentTab);
        // manually start loading stuff in the first tab
        updateTab(TAB_BOOKS, R.id.tab_books);
    }

    private void setupTabs() {
        mTabHost.setup(); // you must call this before adding your tabs!
        mTabHost.addTab(newTab(TAB_BOOKS, R.string.tab_list_books, R.id.tab_books));
        mTabHost.addTab(newTab(TAB_MOVIES, R.string.tab_list_movies, R.id.tab_movies));
        mTabHost.addTab(newTab(TAB_MUSIC, R.string.tab_list_music, R.id.tab_music));
        mTabHost.addTab(newTab(TAB_GAMES, R.string.tab_list_games, R.id.tab_games));
    }

    private TabSpec newTab(String tag, int labelId, int tabContentId) {
        Log.d(TAG, "buildTab(): tag=" + tag);

        TabSpec tabSpec = mTabHost.newTabSpec(tag);
        tabSpec.setIndicator(tag);
        tabSpec.setContent(tabContentId);
        return tabSpec;
    }


    public void onTabChanged(String tabId) {
        Log.d(TAG, "onTabChanged(): tabId=" + tabId);
        if (TAB_BOOKS.equals(tabId)) {
            updateTab(tabId, R.id.tab_books);
            mCurrentTab = 0;
            return;
        }
        if (TAB_MOVIES.equals(tabId)) {
            updateTab(tabId, R.id.tab_movies);
            mCurrentTab = 1;
            return;
        }
        if (TAB_MUSIC.equals(tabId)) {
            updateTab(tabId, R.id.tab_music);
            mCurrentTab = 2;
            return;
        }
        if (TAB_GAMES.equals(tabId)) {
            updateTab(tabId, R.id.tab_games);
            mCurrentTab = 3;
        }
    }

    private Fragment getFragmentForType(String type){
        if(type.equals(TAB_BOOKS)){
            if(booksFragment == null){
                booksFragment = new EndlessExploreFragment(type);
            }
            return booksFragment;
        }
        if(type.equals(TAB_MOVIES)){
            if(videoFragment == null){
                videoFragment = new EndlessExploreFragment(type);
            }
            return videoFragment;
        }
        if(type.equals(TAB_MUSIC)){
            if(musicFragment == null){
                musicFragment = new EndlessExploreFragment(type);
            }
            return musicFragment;
        }
        if(type.equals(TAB_GAMES)){
            if(gameFragment == null){
                gameFragment = new EndlessExploreFragment(type);
            }
            return gameFragment;
        }
        return null;
    }

    private void updateTab(String tabId, int placeholder) {
        FragmentManager fm = getFragmentManager();
        Fragment fragment = getFragmentForType(tabId);
        if(fm != null){
            fm.beginTransaction().replace(placeholder, fragment, tabId).commit();
        }
    }
}
