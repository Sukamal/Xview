package com.everi.xview.screens.fragment.transactionlist;

import com.everi.xview.models.TransactionDetail;

import java.util.List;

/**
 * Created by RSAdmin on 07-03-2017.
 */

public interface TransactionListViewInterface {

    void displayTransactionList(List<TransactionDetail> transactionDetailList);
    void errorInData(String message);
}
