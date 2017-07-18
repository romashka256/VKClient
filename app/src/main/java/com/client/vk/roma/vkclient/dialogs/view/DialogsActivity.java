package com.client.vk.roma.vkclient.dialogs.view;

import android.support.v4.app.Fragment;

import com.client.vk.roma.vkclient.SingleFragmentActivity;

/**
 * Created by Roma on 13.07.2017.
 */

public class DialogsActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return DialogsListFragment.newInstance();
    }
}
