package com.client.vk.roma.vkclient.login.presenter;

import android.app.Activity;
import android.content.Context;

import com.vk.sdk.VKSdk;

/**
 * Created by Roma on 10.05.2017.
 */

public class LoginPresenter implements ILoginPresenter, IOnLoginFinishedListener {

    private ILoginView view;

    public LoginPresenter(ILoginView view) {
        this.view = view;
    }

    @Override
    public void loginAttempt(Context context, Activity activity, String... scope) {
        if (VKSdk.isLoggedIn()) {
            VKSdk.wakeUpSession(context);
            onLoginSucess();
        } else {
            VKSdk.login(activity, scope);
        }
    }

    @Override
    public void onLoginSucess() {
        view.navigateToProfileActivity();
    }

    @Override
    public void onLoginFailure() {
        view.onError();
    }
}
