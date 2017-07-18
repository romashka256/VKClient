package com.client.vk.roma.vkclient.dialogs.presenters;

import android.support.v4.app.FragmentManager;

import com.client.vk.roma.vkclient.dialogs.models.Dialog;

import org.joda.time.DateTime;

/**
 * Created by Roma on 22.05.2017.
 */

public interface IDialogsPresenter {
    void loadDialogs(int startid, int more);
    void onItemClicked(FragmentManager fm,Dialog dialog);
    void loadMoreDialogs();
    String dialogTimeCount(DateTime dateTime);
}
