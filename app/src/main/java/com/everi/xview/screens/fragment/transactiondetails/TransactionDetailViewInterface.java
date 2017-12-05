package com.everi.xview.screens.fragment.transactiondetails;

import com.everi.xview.models.TransactionDetail;
import com.everi.xview.models.TransactionDetails;

import java.util.List;

/**
 * Created by RSAdmin on 09-03-2017.
 */

public interface TransactionDetailViewInterface {

    void displayTransactionDetails(List<TransactionDetails> transactionDetailList);
    void errorInData(String message);
}
