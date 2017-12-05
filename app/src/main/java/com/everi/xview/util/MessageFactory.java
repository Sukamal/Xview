package com.everi.xview.util;

import android.content.Context;

import com.everi.xview.R;

/**
 * Created by RSAdmin on 06-03-2017.
 */

public class MessageFactory {

    private Context context;

    public MessageFactory(Context context){ this.context = context; }

    public String blankUserId() {return context.getString(R.string.error_blank_id);}
    public String blankPassword() {return context.getString(R.string.error_blank_password);}
    public String somethingWentWrongError() {return context.getString(R.string.error_somethingWentWrongError);}
    public String networkError() {return context.getString(R.string.error_networkError);}


}
