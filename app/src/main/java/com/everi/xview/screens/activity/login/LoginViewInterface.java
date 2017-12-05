package com.everi.xview.screens.activity.login;

/**
 * Created by RSAdmin on 06-03-2017.
 */

public interface LoginViewInterface {

    void initUserData(String userId,String pwd);
    void invalidUserId(String message);
    void invalidPassword(String message);
    void successfullyLoggedIn();
    void errorLoggedIn(String message);
    void showProgress();
    void hideProgress();
}
