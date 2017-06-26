package com.client.vk.roma.vkclient.userprofile.repo;

import android.os.AsyncTask;

import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;

import org.json.JSONObject;

/**
 * Created by Roma on 24.05.2017.
 */

public class UsersInfoRepoAsync extends AsyncTask<Integer, Void, Void> {

    int type;
    private final int GET_USER_NAME_TYPE = 0;

    JSONObject vkApiUser;
    OnUsersInfoRepoFinishedListener listener;

    public UsersInfoRepoAsync(int type, OnUsersInfoRepoFinishedListener listener) {
        this.listener = listener;
        this.type = type;
    }


    public void getNameOfUserById(int id) {


        VKRequest request = new VKRequest("users.get",VKParameters.from(VKApiConst.USER_ID, id));

        request.executeWithListener(new VKRequest.VKRequestListener() {

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);

                vkApiUser = response.json;

                listener.onUserNameNetworkSuccess(vkApiUser);
            }

            @Override
            public void onError(VKError error) {
                super.onError(error);

                listener.onUserNameNetworkFailure();
            }
        });
    }

    @Override
    protected Void doInBackground(Integer... params) {

        switch (type) {
            case GET_USER_NAME_TYPE:
                getNameOfUserById(params[0]);
                break;
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {

        super.onPostExecute(aVoid);
    }
}
