package com.client.vk.roma.vkclient.userprofile.repo;

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

public class FriendsRepo {

    private String FIELDS = "first_name,last_name,photo_50,online,last_seen";
    private OnFriendsRepoFinisherListener lintener;

    private static VKList friendsList;
    JSONObject jsonFriends;

    public FriendsRepo(OnFriendsRepoFinisherListener listener) {
        this.lintener = listener;
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
}
