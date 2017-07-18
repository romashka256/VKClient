package com.client.vk.roma.vkclient.userprofile.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.dialogs.view.DialogsActivity;
import com.client.vk.roma.vkclient.userprofile.models.Friend;
import com.client.vk.roma.vkclient.userprofile.models.User;
import com.client.vk.roma.vkclient.userprofile.presenter.FriendsPresenter;
import com.client.vk.roma.vkclient.userprofile.presenter.UserProfilePresenter;
import com.client.vk.roma.vkclient.userprofile.view.adapters.ListImagesArrayAdapter;
import com.squareup.picasso.Picasso;
import com.vk.sdk.api.VKError;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roma on 15.05.2017.
 */

public class UserProfileFragment extends Fragment implements IFriendsView, IUserProfileView, View.OnClickListener {

    @Bind(R.id.name_profile_textView)
    TextView mNameProfileTextView;
    @Bind(R.id.count_profile_friends_textView)
    TextView mFriendsCountProfileTextView;
    @Bind(R.id.count_profile_subscribers_textView)
    TextView mSubscribersCountProfileTextView;
    @Bind(R.id.status_profile_textView)
    TextView mStatusProfileTextView;
    @Bind(R.id.university_profile_textView)
    TextView mStudyProfileTextView;
    @Bind(R.id.is_online_profile_textView)
    TextView mIsOnlineProfileTextView;
    @Bind(R.id.photo_profile_imageView)
    ImageView mPhotoProfileImageView;
    @Bind(R.id.loading_view_userprofile)
    RelativeLayout mLoadingView;
    @Bind(R.id.list_of_photos)
    RecyclerView mListOfPhotos;
    @Bind(R.id.toolbar_actionbar)
    Toolbar mToolbar;

    @Bind(R.id.messages_button_userprofile)
    Button mMessageButton;


    private UserProfilePresenter userProfilePresenter;

    private FriendsPresenter friendsPresenter;

    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        friendsPresenter = new FriendsPresenter(getActivity(), this);
        userProfilePresenter = new UserProfilePresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        friendsPresenter.loadFriends();
        userProfilePresenter.loadInfo();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.userprofile_content, container, false);

        ButterKnife.bind(this, view);

//        ((UserProfileActivity) getActivity()).setSupportActionBar(mToolbar);

        if ( ((UserProfileActivity) getActivity()).getSupportActionBar() != null) {
            ((UserProfileActivity) getActivity()).getSupportActionBar().setTitle("Main Page");
        }

      //  mToolbar.setSubtitle("Test Subtitle");

        mMessageButton.setOnClickListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        mListOfPhotos.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onUserProfileLoadedSuccess(User user) {
        mLoadingView.setVisibility(View.GONE);
        setDataInViews(user);
    }


    @Override
    public void onUserProfileLoadedFailure(VKError error) {
        Toast.makeText(getActivity(), error.errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserProfileLoading() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setDataInViews(User user) {
        mListOfPhotos.setAdapter(new ListImagesArrayAdapter(getActivity(), user.getImgUrls()));
        Picasso.with(getActivity()).load(user.getPhoto_string()).centerCrop().resize(mPhotoProfileImageView.getMeasuredWidth(), mPhotoProfileImageView.getMeasuredHeight()).into(mPhotoProfileImageView);
        mNameProfileTextView.setText(user.getFirst_name() + " " + user.getLast_name());
        mFriendsCountProfileTextView.setText(String.format("%d %s", user.getFriends_count(), getString(R.string.getFriends_count)));
        mIsOnlineProfileTextView.setText(String.format("%s", user.getIs_online() == 1 ? "онлайн" : " "));
        mStatusProfileTextView.setText(String.format("%s", user.getStatus().equals("") ? "Изменить статус" : user.getStatus()));
        mSubscribersCountProfileTextView.setText(String.format("%s %s", user.getFollowers_count(), getString(R.string.followers_count_string)));
        mStudyProfileTextView.setText(user.getUniversity());
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onFriendsLoadedSuccess(Friend friend) {

    }

    @Override
    public void onFriendsLoadFailure(VKError error) {
        Toast.makeText(getContext(), "Friends List Loading Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFriendsLoading() {

    }

    @Override
    public void onBackPressed() {
        mLoadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.messages_button_userprofile:

                Toast.makeText(getActivity(),"Clicked",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), DialogsActivity.class);
                startActivity(intent);

                break;
        }
    }
}
