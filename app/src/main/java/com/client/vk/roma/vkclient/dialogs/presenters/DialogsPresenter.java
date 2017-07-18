package com.client.vk.roma.vkclient.dialogs.presenters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.client.vk.roma.vkclient.JSONHelper;
import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.dialogs.models.Dialog;
import com.client.vk.roma.vkclient.dialogs.repo.DialogsRepoAsync;
import com.client.vk.roma.vkclient.dialogs.repo.OnDialogsRepoFinishedListener;
import com.client.vk.roma.vkclient.dialogs.view.DialogFragment;
import com.client.vk.roma.vkclient.dialogs.view.IDialogsView;
import com.client.vk.roma.vkclient.userprofile.presenter.UsersInfoResponse;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiDialog;
import com.vk.sdk.api.model.VKList;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * Created by Roma on 22.05.2017.
 */

public class DialogsPresenter implements IDialogsPresenter, OnDialogsRepoFinishedListener {

    private IDialogsView view;
    private JSONHelper jsonHelper;
    private DialogsRepoAsync dialogsRepoAsync;
    private UsersInfoResponse usersInfoResponse;
    private final int GET_DIALOGS_TYPE = 0;
    private final int GET_USER_NAME_TYPE = 0;
    private final String POSITION_KEY = "position_key";
    private Map<Integer,Dialog> dialogs;

    public DialogsPresenter(IDialogsView view) {
        this.view = view;
        this.dialogsRepoAsync = new DialogsRepoAsync(GET_DIALOGS_TYPE, this);
        this.usersInfoResponse = new UsersInfoResponse(GET_USER_NAME_TYPE);

        jsonHelper = new JSONHelper();
    }

    @Override
    public void loadDialogs(int startid, int more) {
        dialogsRepoAsync = new DialogsRepoAsync(GET_DIALOGS_TYPE, this);
        dialogsRepoAsync.execute(startid, more);
    }

    @Override
    public void onDialogsNetworkSuccess(VKList<VKApiDialog> vkDialogResponse, int more) {
        dialogs = jsonHelper.getDialogs(vkDialogResponse);
        for (int i = 0; i < dialogs.size(); i++) {
 //           if (dialogs.get(i).getTitle() == null) {
 //               usersInfoResponse.loadNameOfUserById(dialogs.get(i).getUserid());
 //           }
        }

        usersInfoResponse.setNames(dialogs);

        List<Dialog> dialogsList = new ArrayList<>(dialogs.values());

        view.onDialogsLoadedSuccess(dialogsList, more);
    }


    @Override
    public void onDialogsNetworkLoading() {

    }

    @Override
    public void oDialogsNetworkFailure(VKError error) {
        Log.i("DialogError", error.errorMessage);
    }

    @Override
    public void onItemClicked(FragmentManager fm, Dialog dialog) {
        DialogFragment dialogFragment = DialogFragment.newInstance(dialog.getUserid());

        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_main, dialogFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    @Override
    public void loadMoreDialogs() {

    }

    @Override
    public String dialogTimeCount(DateTime dateTime) {

        Calendar cal = Calendar.getInstance();
        String date = dateTime.getHourOfDay() + " : " + dateTime.getMinuteOfHour();
        int curDay = cal.getTime().getDay();
        int mesDay =  dateTime.getDayOfMonth();
        if (cal.getTime().getMonth() <= dateTime.getMonthOfYear()) {
            date = dateTime.getDayOfMonth() + "." + dateTime.getMonthOfYear();
        if (curDay < mesDay) {
            if (curDay - 1 == mesDay) {
                date = "вчера";
            }

                if (cal.getTime().getYear() < dateTime.getYear()) {
                    date = dateTime.getDayOfMonth() + "." + dateTime.getMonthOfYear() + "." + dateTime.getYear();
                }
            }
        }

        return date;
    }
}
