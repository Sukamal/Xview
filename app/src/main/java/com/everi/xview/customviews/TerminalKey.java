package com.everi.xview.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.everi.xview.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sukamal on 22/2/17.
 */

public class TerminalKey extends LinearLayout{

    private Context mContext;

    @Bind(R.id.spi_processor)
    Spinner spiProcessor;

    public TerminalKey(Context context) {
        this(context,null);
    }

    public TerminalKey(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TerminalKey(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initializeUiComponents();
    }

    private void initializeUiComponents() {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layOut = inflater.inflate(R.layout.view_terminal_key, this,true);;
        ButterKnife.bind(this);

        setUpSpinner();
    }

    private void setUpSpinner(){

        List<String> categories = new ArrayList<String>();
        categories.add("CDS");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiProcessor.setAdapter(dataAdapter);
    }


}
