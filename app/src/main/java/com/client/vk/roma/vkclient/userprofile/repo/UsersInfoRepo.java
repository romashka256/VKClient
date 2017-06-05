package com.client.vk.roma.vkclient.userprofile.repo;

import com.vk.sdk.api.VKApi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKList;

/**
 * Created by Roma on 24.05.2017.
 */

public class UsersInfoRepo {

    VKList vkApiUser;
    OnUsersInfoRepoFinishedListener listener;

    public UsersInfoRepo(OnUsersInfoRepoFinishedListener listener) {
        this.listener = listener;
    }

    public void getNameOfUserById(int id) {
        VKRequest request = VKApi.users().get(VKParameters.from(VKApiConst.USER_ID, id));

        request.executeWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                vkApiUser = (VKList) response.parsedModel;

                listener.onUserNameNetworkSuccess(vkApiUser);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);

                listener.onUserNameNetworkFailure();
            }
        });
    }

}
