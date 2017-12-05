package com.everi.xview.screens.fragment;

import android.support.v4.app.Fragment;

import com.everi.xview.screens.fragment.atmstaedashboard.ATMstateDashBoardFragment;
import com.everi.xview.screens.fragment.atmstaedashboard.ATMstateDashBoardGraphFragment;
import com.everi.xview.screens.fragment.terminalcommand.TerminalCommandFragment;
import com.everi.xview.screens.fragment.terminaldashboard.TerminalDashBoardDetailsFragment;
import com.everi.xview.screens.fragment.terminaldashboard.TerminalDashBoardFragment;
import com.everi.xview.screens.fragment.terminaldashboard.TerminalDashBoardStatisticsFragment;
import com.everi.xview.screens.fragment.terminaldashboard.TerminalDashboardNewFragment;
import com.everi.xview.screens.fragment.terminaldashboard.TerminalDetailsFragment;
import com.everi.xview.screens.fragment.terminaldashboard.TerminalListNewFragment;
import com.everi.xview.screens.fragment.terminaldashboard.TerminalViewTransactionListFragment;
import com.everi.xview.screens.fragment.transactiondashboard.TransactionDashBoardDetailsFragment;
import com.everi.xview.screens.fragment.transactiondashboard.TransactionDashBoardFragment;
import com.everi.xview.screens.fragment.transactiondashboard.TransactionDashBoardGraphFragment;
import com.everi.xview.screens.fragment.transactiondetails.TransactionDetailsFragment;
import com.everi.xview.screens.fragment.transactionlist.TransactionListFragment;
import com.everi.xview.util.Utility;

/**
 * Created by sukamal on 15/2/17.
 */

public class FragmentFactory {

    public Fragment getFragment(String name){
        Fragment fragment = null;

        if(!Utility.isNullOrBlank(name)){
            if(name.equalsIgnoreCase(TransactionListFragment.class.getName())){
                fragment = new TransactionListFragment();
            }else if(name.equalsIgnoreCase(TransactionFilterFragment.class.getName())){
                fragment = new TransactionFilterFragment();
            }else if(name.equalsIgnoreCase(TransactionFilterWheelFragment.class.getName())){
                fragment = new TransactionFilterWheelFragment();
            }else if(name.equalsIgnoreCase(TransactionFilterDateRangeFragment.class.getName())){
                fragment = new TransactionFilterDateRangeFragment();
            }else if(name.equalsIgnoreCase(TransactionDetailsFragment.class.getName())){
                fragment = new TransactionDetailsFragment();
            }else if(name.equalsIgnoreCase(TransactionDashBoardFragment.class.getName())){
                fragment = new TransactionDashBoardFragment();
            }else if(name.equalsIgnoreCase(TransactionDashBoardGraphFragment.class.getName())){
                fragment = new TransactionDashBoardGraphFragment();
            }else if(name.equalsIgnoreCase(TransactionDashBoardDetailsFragment.class.getName())){
                fragment = new TransactionDashBoardDetailsFragment();
            }else if(name.equalsIgnoreCase(ATMstateDashBoardFragment.class.getName())){
                fragment = new ATMstateDashBoardFragment();
            }else if(name.equalsIgnoreCase(ATMstateDashBoardGraphFragment.class.getName())){
                fragment = new ATMstateDashBoardGraphFragment();
            }

            else if(name.equalsIgnoreCase(TerminalDashboardNewFragment.class.getName())){
                fragment = new TerminalDashboardNewFragment();
            }else if(name.equalsIgnoreCase(TerminalListNewFragment.class.getName())){
                fragment = new TerminalListNewFragment();
            }else if(name.equalsIgnoreCase(TerminalDetailsFragment.class.getName())){
                fragment = new TerminalDetailsFragment();
            }


            else if(name.equalsIgnoreCase(TerminalListFragment.class.getName())){
                fragment = new TerminalListFragment();
            }else if(name.equalsIgnoreCase(TerminalDashBoardFragment.class.getName())){
                fragment = new TerminalDashBoardFragment();
            }else if(name.equalsIgnoreCase(TerminalDashBoardDetailsFragment.class.getName())){
                fragment = new TerminalDashBoardDetailsFragment();
            }else if(name.equalsIgnoreCase(TerminalDashBoardStatisticsFragment.class.getName())){
                fragment = new TerminalDashBoardStatisticsFragment();
            }else if(name.equalsIgnoreCase(TerminalViewTransactionListFragment.class.getName())){
                fragment = new TerminalViewTransactionListFragment();
            }else if(name.equalsIgnoreCase(TerminalKeyChangeFragment.class.getName())){
                fragment = new TerminalKeyChangeFragment();
            }else if(name.equalsIgnoreCase(TerminalCommandFragment.class.getName())){
                fragment = new TerminalCommandFragment();
            }



        }

        return fragment;
    }
}
