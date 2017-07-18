package com.client.vk.roma.vkclient.userprofile.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;

import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.SingleFragmentActivity;

public class UserProfileActivity extends SingleFragmentActivity {



    @Override
    protected Fragment createFragment() {
        return UserProfileFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }



    }


}
