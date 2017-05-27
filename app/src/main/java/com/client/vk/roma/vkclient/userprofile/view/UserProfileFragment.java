package com.client.vk.roma.vkclient.userprofile.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.client.vk.roma.vkclient.Dialog;
import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.User;
import com.client.vk.roma.vkclient.userprofile.Friend;
import com.client.vk.roma.vkclient.userprofile.presenter.DialogPresenter;
import com.client.vk.roma.vkclient.userprofile.presenter.FriendsPresenter;
import com.client.vk.roma.vkclient.userprofile.presenter.UserProfilePresenter;
import com.squareup.picasso.Picasso;
import com.vk.sdk.api.VKError;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roma on 15.05.2017.
 */

public class UserProfileFragment extends Fragment implements IFriendsView, IDialogsView, IUserProfileView, NavigationView.OnNavigationItemSelectedListener {

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
    @Bind(R.id.loadding_fragment)
    RelativeLayout mRelativeProgress;
    @Bind(R.id.list_of_dialogs)
    RecyclerView mDialogsList;
    @Bind(R.id.list_of_photos)
    RecyclerView mListOfPhotos;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawer;
    @Bind(R.id.nav_view)
    NavigationView mNav;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private UserProfilePresenter userProfilePresenter;
    private DialogPresenter dialogPresenter;
    private FriendsPresenter friendsPresenter;

    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        friendsPresenter = new FriendsPresenter(getActivity(), this);
        dialogPresenter = new DialogPresenter(this);
        userProfilePresenter = new UserProfilePresenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        friendsPresenter.loadFriends();
        dialogPresenter.loadDialogs();
        userProfilePresenter.loadInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_profile, container, false);

        ButterKnife.bind(this, view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();
        mDialogsList.setHasFixedSize(true);
        mDialogsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        mListOfPhotos.setHasFixedSize(true);
        mListOfPhotos.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, true));

        return view;
    }

    @Override
    public void onUserProfileLoadedSuccess(User user) {
        mRelativeProgress.setVisibility(View.GONE);
        setDataInViews(user);
    }



    @Override
    public void onUserProfileLoadedFailure(VKError error) {
        Toast.makeText(getActivity(), error.errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserProfileLoading() {
        mRelativeProgress.setVisibility(View.VISIBLE);
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
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onDialogsLoadedSuccess(List<Dialog> dialog) {
        mDialogsList.setAdapter(new ListDialogsArrayAdapter(getActivity(), dialog));
    }

    @Override
    public void onDialogLoadFailure(VKError error) {
        Toast.makeText(getContext(), "Dialog Loading Failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDialogLoading() {

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
}
