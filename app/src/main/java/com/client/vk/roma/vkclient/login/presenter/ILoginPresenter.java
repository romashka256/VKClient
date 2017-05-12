package com.client.vk.roma.vkclient.login.presenter;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Roma on 10.05.2017.
 */

public interface ILoginPresenter {
    void loginAttempt(Context context, Activity activity, String... scope);
}
