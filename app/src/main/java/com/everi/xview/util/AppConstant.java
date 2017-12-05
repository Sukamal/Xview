package com.everi.xview.util;

import com.everi.xview.R;

/**
 * Created by sukamal on 15/2/17.
 */

public final class AppConstant {

    public enum MENU {

        MENU_TRANSDASHBOARD("TRANSDASHBOARD", R.id.nav_transaction_dashboard),
        MENU_TRANSLISTING("TRANSLISTING",R.id.nav_transaction_list),
        MENU_TERMINALDASHBOARD("TERMINALDASHBOARD",R.id.nav_terminal_dashboard),
        MENU_ATMSTATEDASHBOARD("ATMSTATEDASHBOARD",R.id.nav_atm_state_dashboard),
        MENU_TERMINALCOMMANDS("TERMINALCOMMANDS",R.id.nav_terminal_command),
        MENU_TERMINALKEYCHANGE("TERMINALKEYCHANGE",R.id.nav_terminal_key_change);

        private String name;
        private int id;
        MENU(String name,int id){
            this.name = name;
            this.id = id;
        }
        public String getMenuName(){return name;}
        public int getMenuId(){return id;}

    }

    public enum ExtraTag {

        TAG_HEADING("Heading"),
        TAG_FILTER_BY("FilterBy"),
        TAG_TRANSACTION_UID("TransactionUId");

        private String value;
        ExtraTag(String value){
            this.value = value;
        }

        public String getEnumValue(){return value;}

    }

    public enum FilterCategory {

        FILTER_BY_MERCHANT("1"),
        FILTER_BY_STATUS("2"),
        FILTER_BY_TYPE("3");

        private String value;
        FilterCategory(String value){
            this.value = value;
        }

        public String getEnumValue(){return value;}

    }
}
