package com.everi.xview.screens.activity.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.everi.xview.R;
import com.everi.xview.XviewApplication;
import com.everi.xview.preference.AppPreferenceInterface;
import com.everi.xview.screens.activity.BaseActivity;
import com.everi.xview.screens.fragment.atmstaedashboard.ATMstateDashBoardFragment;
import com.everi.xview.screens.fragment.terminalcommand.TerminalCommandFragment;
import com.everi.xview.screens.fragment.TerminalKeyChangeFragment;
import com.everi.xview.screens.fragment.TerminalListFragment;
import com.everi.xview.screens.fragment.terminaldashboard.TerminalDashboardNewFragment;
import com.everi.xview.screens.fragment.transactiondashboard.TransactionDashBoardFragment;
import com.everi.xview.screens.fragment.transactionlist.TransactionListFragment;
import com.everi.xview.util.AppConstant;
import com.everi.xview.util.Utility;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements MainViewInterface, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    AppPreferenceInterface preferenceInterface;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.nav_view)
    NavigationView mNavigationView;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    MainLogicImpl mainLogic;

    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((XviewApplication) getApplication()).getComponent().inject(this);
        initializeToolbar();
        initializeNavDrawer();

        mainLogic = new MainLogicImpl(MainActivity.this,this,mNavigationView,preferenceInterface);
        mainLogic.setNavActiveMenu();

//        setActiveMenu();

    }



    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            int count = fm.getBackStackEntryCount();
            if (count == 1) {
                mToolbar.setVisibility(View.VISIBLE);
                setNavDrawerEnabled(true);
            }
                super.onBackPressed();

        }
    }


    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            return false;
        }else{
            return super.onOptionsItemSelected(item);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (id == R.id.nav_transaction_dashboard) {

            HashMap<String,String> bundleData = new HashMap<>();
            bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Transactions Dashboard");
            Utility.startFragmentInContainer1(MainActivity.this, TransactionDashBoardFragment.class.getName(),false,bundleData);


        } else if (id == R.id.nav_transaction_list) {

            HashMap<String,String> bundleData = new HashMap<>();
            bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Transactions");
            Utility.startFragmentInContainer1(MainActivity.this,TransactionListFragment.class.getName(),false,bundleData);

        }else if (id == R.id.nav_terminal_dashboard) {

            HashMap<String,String> bundleData = new HashMap<>();
            bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Terminal Dashboard");
//            Utility.startFragmentInContainer1(MainActivity.this, TerminalListFragment.class.getName(),false,bundleData);
            Utility.startFragmentInContainer1(MainActivity.this, TerminalDashboardNewFragment.class.getName(),false,bundleData);


        }else if (id == R.id.nav_atm_state_dashboard) {
            HashMap<String,String> bundleData = new HashMap<>();
            bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"ATM State Dashboard");
            Utility.startFragmentInContainer1(MainActivity.this, ATMstateDashBoardFragment.class.getName(),false,bundleData);


        }else if (id == R.id.nav_terminal_command) {
            HashMap<String,String> bundleData = new HashMap<>();
            bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Terminal Command");
            Utility.startFragmentInContainer1(MainActivity.this, TerminalCommandFragment.class.getName(),false,bundleData);


        }else if (id == R.id.nav_terminal_key_change) {
            HashMap<String,String> bundleData = new HashMap<>();
            bundleData.put(AppConstant.ExtraTag.TAG_HEADING.getEnumValue(),"Terminal Key Change");
            Utility.startFragmentInContainer1(MainActivity.this, TerminalKeyChangeFragment.class.getName(),false,bundleData);


        }else{
            Toast.makeText(MainActivity.this,item.getTitle(),Toast.LENGTH_SHORT).show();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initializeToolbar(){
        mToolbar.setTitle("Transactions");
        setSupportActionBar(mToolbar);
    }

    private void initializeNavDrawer() {

        drawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout,mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.setDrawerListener(drawerToggle);

        mDrawerLayout.post(new Runnable(){

            @Override
            public void run(){
                //enable hamburger icon
                drawerToggle.syncState();
            }

        });


        mNavigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void setToolbarTitle(String title) {
        mToolbar.setTitle(title);
    }

    @Override
    public void openNavDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void closeNavDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void setNavDrawerEnabled(boolean val) {
        if(mDrawerLayout != null){
            if (val) {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            } else {
                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        }
    }

    @Override
    public void performNavMenuItemClick(int menuId) {
        mNavigationView.getMenu().findItem(menuId).setChecked(true);
        onNavigationItemSelected(mNavigationView.getMenu().findItem(menuId));
    }

    @Override
    public ActionBarDrawerToggle getNavDrawerToggle() {
        return drawerToggle;
    }

    @Override
    public Toolbar getToolbar() {
        return mToolbar;
    }


}
