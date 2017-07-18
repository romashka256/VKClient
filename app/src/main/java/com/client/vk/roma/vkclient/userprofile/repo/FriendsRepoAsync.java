package com.client.vk.roma.vkclient.userprofile.repo;

import android.os.AsyncTask;

import com.client.vk.roma.vkclient.userprofile.repo.listeners.OnFriendsRepoFinisherListener;
import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

import org.json.JSONObject;


/**
 * Created by Roma on 23.05.2017.
 */

public class FriendsRepoAsync extends AsyncTask<Void, Void, Void>{

    private String FIELDS = "first_name,last_name,photo_50,online,last_seen";
    private OnFriendsRepoFinisherListener lintener;
    private int type;
    private final int GET_FRIENDS_LIST_TYPE = 0;
    private static VKList friendsList;
    JSONObject jsonFriends;


    public FriendsRepoAsync(int type,OnFriendsRepoFinisherListener listener) {
        this.lintener = listener;
        this.type = type;
    }

    public void getFriendsList(){

        VKRequest request = VKApi.friends().get(VKParameters.from(VKApiConst.FIELDS,FIELDS));
        request.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                friendsList =(VKList) response.parsedModel;

                lintener.onFriendsNetworkSuccess(friendsList);

            }

            @Override
            public void onError(VKError error) {
                super.onError(error);

                lintener.onFriendsNetworkFailure(error);
            }

            @Override
            public void onProgress(VKRequest.VKProgressType progressType, long bytesLoaded, long bytesTotal) {
                super.onProgress(progressType, bytesLoaded, bytesTotal);
            }
        });

    }

    @Override
    protected Void doInBackground(Void... params) {
        switch(type){
            case GET_FRIENDS_LIST_TYPE :
                getFriendsList();
                break;
        }
        return null;
    }
}
