package com.client.vk.roma.vkclient.login.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.client.vk.roma.vkclient.userprofile.view.UserProfileActivity;
import com.client.vk.roma.vkclient.login.presenter.ILoginView;
import com.client.vk.roma.vkclient.login.presenter.LoginPresenter;
import com.client.vk.roma.vkclient.R;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

/**
 * Created by Roma on 10.05.2017.
 */

public class LoginActivity extends AppCompatActivity implements ILoginView {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] scope = new String[]{VKScope.MESSAGES, VKScope.FRIENDS, VKScope.WALL, VKScope.STATUS};

        UserProfileActivity activity = new UserProfileActivity();

        presenter = new LoginPresenter(this);
        presenter.loginAttempt(this,this, scope);
    }

    @Override
    public void navigateToProfileActivity() {
        Toast.makeText(this, "Login Succes", Toast.LENGTH_SHORT).show();
    //    Intent intent = new Intent(this, UserProfileActivity.class);
     //      startActivity(intent);
    }

    @Override
    public void onError() {
        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                presenter.onLoginSucess();
            }

            @Override
            public void onError(VKError error) {
                presenter.onLoginFailure();

            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
