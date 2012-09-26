package com.alcorsys.medianearby.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.alcorsys.medianearby.R;
import com.androidquery.AQuery;

public class DemoFragment extends Fragment {

    private AQuery aq;

   @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container,
       Bundle savedInstanceState) {

       View mView = inflater.inflate(R.layout.demo, container, false);



       DemoAdapter adapter = new DemoAdapter();
       ViewPager myPager = (ViewPager) mView.findViewById(R.id.four_panel_pager);
       myPager.setAdapter(adapter);
       myPager.setCurrentItem(0);

       aq = new AQuery(getActivity(),mView);
       aq.id(R.id.sign_in_inout).clicked(this, "signInClicked");
       aq.id(R.id.sign_up_inout).clicked(this, "signUpClicked");

       // Inflate the layout for this fragment
       return mView;
   }

    public void signInClicked(View button){
            ((HomeSignedOutActivity)getActivity()).showLoginFragment();
    }

    public void signUpClicked(View button){
        ((HomeSignedOutActivity)getActivity()).showRegistrationFragment();
    }



}
