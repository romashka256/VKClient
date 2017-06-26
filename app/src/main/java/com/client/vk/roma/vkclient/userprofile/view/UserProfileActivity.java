package com.client.vk.roma.vkclient.userprofile.view;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.client.vk.roma.vkclient.SingleFragmentActivity;
import com.client.vk.roma.vkclient.User;
import com.vk.sdk.api.VKError;

public class UserProfileActivity extends SingleFragmentActivity implements IUserProfileView {

    @Override
    protected Fragment createFragment() {
        return UserProfileFragment.newInstance();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
     /*   FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.containerFragment,UserProfileFragment.newInstance())
                .commit(); */
    }

    @Override
    public void onUserProfileLoadedSuccess(User user) {
    }

    @Override
    public void onUserProfileLoadedFailure(VKError error) {
        Toast.makeText(this, error.errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserProfileLoading() {


    }

    @Override
    public void setDataInViews(User user) {
    }
}
