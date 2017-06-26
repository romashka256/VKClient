package com.client.vk.roma.vkclient.userprofile.presenter;

import android.support.v4.app.FragmentManager;

import com.client.vk.roma.vkclient.Dialog;

/**
 * Created by Roma on 22.05.2017.
 */

public interface IDialogsPresenter {
    void loadDialogs();
    void onItemClicked(FragmentManager fm,Dialog dialog);
}
