package com.everi.xview.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by sukamal on 20/2/17.
 */

public class DashBoardPagerAdapter extends FragmentPagerAdapter {

    private Context context = null;
    private List<Fragment> fragmentList;

    public DashBoardPagerAdapter(Context context, FragmentManager mgr, List<Fragment> fragmentList) {
        super(mgr);
        this.context = context;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if(fragmentList != null && fragmentList.size() > 0){
            return fragmentList.size();
        }else{
            return 0;
        }

    }
}
