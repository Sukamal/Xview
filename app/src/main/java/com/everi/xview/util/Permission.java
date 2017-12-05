package com.everi.xview.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;

import static android.support.v4.app.ActivityCompat.requestPermissions;
import static android.support.v4.app.ActivityCompat.shouldShowRequestPermissionRationale;
import static android.support.v4.content.PermissionChecker.checkSelfPermission;

/**
 * Created by sukamal on 24/2/17.
 */

public class Permission {

    private Context context;

    final private int REQUEST_CODE_ASK_PERMISSIONS = 2017;

    public Permission(Context context){
        this.context = context;
    }

    public boolean checkStoragePermission(final Activity activity) {
        int hasWriteExternalStoragePermission = checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (hasWriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
            if (!shouldShowRequestPermissionRationale(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AppDialog.showMessageOKCancel(activity,"You need to allow access to Storage",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(activity,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                        REQUEST_CODE_ASK_PERMISSIONS);
                            }
                        });
                return false;
            }
            requestPermissions(activity,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_ASK_PERMISSIONS);
            return false;
        }
        return true;
    }
}
