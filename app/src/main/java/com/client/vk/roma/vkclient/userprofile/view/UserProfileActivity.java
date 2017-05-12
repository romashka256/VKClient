package com.client.vk.roma.vkclient.userprofile.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.userprofile.presenter.UserProfilePresenter;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.model.VKApiUserFull;

import butterknife.Bind;
import butterknife.ButterKnife;

public class UserProfileActivity extends AppCompatActivity implements IUserProfileView{

    @Bind(R.id.name_profile_textView)
    TextView mNameProfileTextView;
    @Bind(R.id.count_profile_friends_textView)
    TextView mFriendsCountProfileTextView;
    @Bind(R.id.count_profile_subscribers_textView)
    TextView mSubscribersCountProfileTextView;
    @Bind(R.id.status_profile_textView)
    TextView mStatusProfileTextView;
    @Bind(R.id.study_profile_textView)
    TextView mStudyProfileTextView;

    private UserProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        ButterKnife.bind(this);
        presenter = new UserProfilePresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadInfo();
    }

    @Override
    public void onUserProfileLoadedSuccess(VKApiUserFull vkApiUserFull) {
        mNameProfileTextView.setText(vkApiUserFull.first_name + " " + vkApiUserFull.last_name);
        //mFriendsCountProfileTextView.setText(vkApiUserFull);
        //mStatusProfileTextView(vkApiUserFull);
        //mSubscribersCountProfileTextView.setText(vkApiUserFull);
      //  for(VKApiUniversity university : vkApiUserFull.universities){
      //      mStudyProfileTextView.setText(university.name);
     //   }
    }

    @Override
    public void onUserProfileLoadedFailure(VKError error) {
        Toast.makeText(this,error.errorMessage,Toast.LENGTH_SHORT).show();
    }
}
