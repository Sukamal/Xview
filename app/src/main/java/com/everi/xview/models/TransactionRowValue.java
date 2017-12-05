package com.everi.xview.models;

import java.util.List;

/**
 * Created by sukamal on 20/2/17.
 */

public class TransactionRowValue {

    public String columnValue1;
    public String columnValue2;
    public String columnValue3;

    public TransactionRowValue(String columnValue1,String columnValue2,String columnValue3){
        this.columnValue1 = columnValue1;
        this.columnValue2 = columnValue2;
        this.columnValue3 = columnValue3;
    }

    public TransactionRowValue(String columnValue1,String columnValue2){
        this.columnValue1 = columnValue1;
        this.columnValue2 = columnValue2;
    }


}
