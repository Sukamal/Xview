package com.everi.xview.util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.everi.xview.R;
import com.everi.xview.screens.fragment.FragmentFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by sukamal on 14/2/17.
 */

public class Utility {


    public static boolean isNullOrBlank(String str) {
        boolean retVal = false;
        if (str == null || (str != null && str.equalsIgnoreCase("null"))
                || (str != null && str.isEmpty())
                || (str != null && str.trim().length() == 0))
            retVal = true;

        return retVal;
    }

    public static boolean isNullOrBlank(View view) {
        String str = null;
        boolean retVal = false;


        if(view != null){
            if(view instanceof TextView){
                str = ((TextView) view).getText().toString();
            }else if(view instanceof EditText){
                str = ((EditText) view).getText().toString();
            }

            if (str == null || (str != null && str.equalsIgnoreCase("null"))
                    || (str != null && str.isEmpty())
                    || (str != null && str.trim().length() == 0))
                retVal = true;
        }



        return retVal;
    }

    public static Date getDateFromString(String input, String pattern){
        if(input == null || input==""){
            return null;
        }
        DateFormat format = new SimpleDateFormat(pattern, Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(input);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public static String convertDateFormat(String input,String fromPattern,String toPattern){
        if(input == null || input==""){
            return"";
        }
        SimpleDateFormat format = new SimpleDateFormat(fromPattern, Locale.ENGLISH);
        SimpleDateFormat formatter = new SimpleDateFormat(toPattern, Locale.ENGLISH);
        Date date;
        try {
            date = format.parse(input);
            return formatter.format(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return "";
    }


    public static void startFragmentInContainer1(FragmentActivity activity, String fragmentName, boolean isAddtoBackStack, HashMap<String, String> extras) {


        FragmentFactory fragmentFactory = new FragmentFactory();
        Fragment fragment = fragmentFactory.getFragment(fragmentName);

        FragmentManager fm = activity.getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        FrameLayout fl = (FrameLayout) activity.findViewById(R.id.fl_main_container_1);
        fl.removeAllViews();


        if (extras != null && !extras.isEmpty()) {
            Bundle bundle = new Bundle();

            for (HashMap.Entry<String, String> entry : extras.entrySet()) {
                bundle.putString(entry.getKey(),entry.getValue());
            }

            fragment.setArguments(bundle);
        }

        if (isAddtoBackStack) {
            ft.replace(R.id.fl_main_container_1, fragment, fragmentName);
            ft.addToBackStack(fragmentName);
        } else {
            ft.replace(R.id.fl_main_container_1, fragment, fragmentName);

        }
        ft.commit();
    }

    public static int dip2px(Context context, float dipValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }




}
