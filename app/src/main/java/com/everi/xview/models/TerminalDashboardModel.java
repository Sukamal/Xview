package com.everi.xview.models;

/**
 * Created by sukamal on 15/2/17.
 */

public class TerminalDashboardModel {

    private String activityTitle;
    private String activityCount;

    public TerminalDashboardModel(String activityTitle,String activityCount){
        this.activityTitle = activityTitle;
        this.activityCount = activityCount;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityCount() {
        return activityCount;
    }

    public void setActivityCount(String activityCount) {
        this.activityCount = activityCount;
    }
}
