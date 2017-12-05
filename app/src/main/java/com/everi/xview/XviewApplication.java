package com.everi.xview;

import android.app.Application;
import android.os.Environment;

import com.everi.xview.di.component.DaggerXviewComponent;
import com.everi.xview.di.modules.SharedDIModule;
import com.everi.xview.di.component.XviewComponent;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by sukamal on 24/2/17.
 */

public class XviewApplication extends Application {

    private XviewComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerXviewComponent.builder()
                .sharedDIModule(new SharedDIModule(this))
                .build();

        AppConfigData appConfig = AppConfigData.getInstance(this);
        appConfig.loadConfig();
    }


    public XviewComponent getComponent(){return component;}

    private void readLogs(){
        try {
            Process process = Runtime.getRuntime().exec("logcat -d");
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            StringBuilder log=new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line);
            }

        } catch (IOException e) {
        }
    }

    private void saveLogToFile(){

        try {
            File filename = new File(Environment.getExternalStorageDirectory()+"/xviewlog.log");
            filename.createNewFile();
            String cmd = "logcat -d -f"+filename.getAbsolutePath();
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
