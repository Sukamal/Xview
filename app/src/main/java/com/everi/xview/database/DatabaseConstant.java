package com.everi.xview.database;

/**
 * Created by RSAdmin on 02-03-2017.
 */

public class DatabaseConstant {

    static String DATABASE_NAME 			= "com.everi.xview.db.sqlite";
    static int DATABASE_VERSION 			= 1;

    public interface Table_Menu{

        String TABLE_NAME			= "Menu";

        String MENU_CODE = "menu_code";
        String MENU_NAME = "menu_name";
    }

    public interface Table_Transaction_Type{

        String TABLE_NAME			= "TransactionType";

        String TRNS_LOCALCODE = "LocalTransactionCode";
        String TRANS_DESCRIPTION = "Description";
    }

    public interface Table_Merchant{

        String TABLE_NAME			= "Merchant";

        String MERCHANT_SID = "MerchantSID";
        String MERCHANT_NAME = "MerchantName";
    }

}
