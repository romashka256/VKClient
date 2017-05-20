package com.client.vk.roma.vkclient.userprofile.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.User;
import com.client.vk.roma.vkclient.userprofile.presenter.ListImagesArrayAdapter;
import com.client.vk.roma.vkclient.userprofile.presenter.UserProfilePresenter;
import com.squareup.picasso.Picasso;
import com.vk.sdk.api.VKError;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roma on 15.05.2017.
 */

public class UserProfileFragment extends Fragment implements IUserProfileView {

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
    @Bind(R.id.list_of_photos)
    RecyclerView mListOfPhotos;

    private UserProfilePresenter presenter;

    public static UserProfileFragment newInstance() {
        UserProfileFragment fragment = new UserProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new UserProfilePresenter(this);


    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadInfo();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user_profile, container, false);

        ButterKnife.bind(this, view);

        mListOfPhotos.setHasFixedSize(true);
        mListOfPhotos.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));

        return view;
    }

    @Override
    public void onUserProfileLoadedSuccess(User user) {
        mRelativeProgress.setVisibility(View.GONE);

        mListOfPhotos.setAdapter(new ListImagesArrayAdapter(getActivity(),user.getImgUrls()));

        Picasso.with(getActivity()).load(user.getPhoto_string()).centerCrop().resize(mPhotoProfileImageView.getMeasuredWidth(), mPhotoProfileImageView.getMeasuredHeight()).into(mPhotoProfileImageView);
        mNameProfileTextView.setText(user.getFirst_name() + " " + user.getLast_name());
        mFriendsCountProfileTextView.setText(String.format("%d %s", user.getFriends_count(), getString(R.string.getFriends_count)));
        mIsOnlineProfileTextView.setText(String.format("%s", user.getIs_online() == 1 ? "онлайн" : " "));
        mStatusProfileTextView.setText(String.format("%s", user.getStatus().equals("") ? "Изменить статус" : user.getStatus()));
        mSubscribersCountProfileTextView.setText(String.format("%s %s", user.getFollowers_count(), getString(R.string.followers_count_string)));
        mStudyProfileTextView.setText(user.getUniversity());
    }

    @Override
    public void onUserProfileLoadedFailure(VKError error) {
        Toast.makeText(getActivity(), error.errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUserProfileLoading() {
        mRelativeProgress.setVisibility(View.VISIBLE);

    }
}
