package com.client.vk.roma.vkclient.userprofile.presenter;

import android.content.Context;
import android.widget.Toast;

import com.client.vk.roma.vkclient.JSONHelper;
import com.client.vk.roma.vkclient.userprofile.repo.FriendsRepoAsync;
import com.client.vk.roma.vkclient.userprofile.repo.listeners.OnFriendsRepoFinisherListener;
import com.client.vk.roma.vkclient.userprofile.view.IFriendsView;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKList;

/**
 * Created by Roma on 23.05.2017.
 */

public class FriendsPresenter implements IFriendsPresenter,OnFriendsRepoFinisherListener {

    private IFriendsView view;
    JSONHelper jsonHelper;
    FriendsRepoAsync friendsRepoAsync;
    Context context;
    private final int GET_FRIENDS_LIST_TYPE = 0;

    public FriendsPresenter(Context context, IFriendsView view) {
        this.view = view;
        this.context = context;
        jsonHelper = new JSONHelper();
        this.friendsRepoAsync = new FriendsRepoAsync(GET_FRIENDS_LIST_TYPE,this);
    }

    @Override
    public void loadFriends() {
        friendsRepoAsync = new FriendsRepoAsync(GET_FRIENDS_LIST_TYPE,this);
        friendsRepoAsync.execute();
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
