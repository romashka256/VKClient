package com.client.vk.roma.vkclient.userprofile.presenter;

import android.content.Context;
import android.widget.Toast;

import com.client.vk.roma.vkclient.userprofile.repo.FriendsRepo;
import com.client.vk.roma.vkclient.userprofile.repo.OnFriendsRepoFinisherListener;
import com.client.vk.roma.vkclient.userprofile.JSONHelper;
import com.client.vk.roma.vkclient.userprofile.view.IDialogsView;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKList;

/**
 * Created by Roma on 23.05.2017.
 */

public class FriendsPresenter implements IFriendsPresenter,OnFriendsRepoFinisherListener {

    private IDialogsView view;
    JSONHelper jsonHelper;
    FriendsRepo interactor;
    Context context;

    public FriendsPresenter(Context context, IDialogsView view) {
        this.view = view;
        this.context = context;
        jsonHelper = new JSONHelper();
        this.interactor = new FriendsRepo(this);
    }

    @Override
    public void loadFriends() {
        interactor.getFriendsList();
    }

    @Override
    public void onFriendsNetworkSuccess(VKList vkDialogResponse) {
        jsonHelper.getFriends(vkDialogResponse);
        Toast.makeText(context,"Friends Loaded",Toast.LENGTH_SHORT);
    }

    @Override
    public void onFriendsNetworkLoading() {

    }

    @Override
    public void onFriendsNetworkFailure(VKError error) {

    }
}
