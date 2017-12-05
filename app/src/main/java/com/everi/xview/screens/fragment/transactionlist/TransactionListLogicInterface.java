package com.everi.xview.screens.fragment.transactionlist;

/**
 * Created by RSAdmin on 07-03-2017.
 */

public interface TransactionListLogicInterface {

    void displayTransactionList(String userGroupSID,String merchant,String transactionType,String fromDate,String toDate,String pageIndex,String pageSize);
}
