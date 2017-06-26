package com.client.vk.roma.vkclient.userprofile.repo;

import org.json.JSONObject;

/**
 * Created by Roma on 13.06.2017.
 */

public interface OnDialogRepoFinishedListener {
    void onDialogNetworkSuccess(JSONObject jsonDialog);
    void onDialogNetworkFailure();
}
