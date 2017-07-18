package com.client.vk.roma.vkclient.dialogs.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.dialogs.models.Message;
import com.client.vk.roma.vkclient.dialogs.presenters.DialogPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roma on 13.06.2017.
 */

public class DialogFragment extends Fragment implements IDialogView {

    private DialogPresenter dialogPresenter;
    private List<Message> fullListMessages;
    private int userid;
    private final String POSITION_KEY = "position_key";

    public static DialogFragment newInstance(int userid) {
        DialogFragment fragment = new DialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position_key", userid);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Bind(R.id.dialog_fragment_recycler)
    RecyclerView mListOfMessages;
    @Bind(R.id.loading_fragment)
    RelativeLayout mLoadingView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogPresenter = new DialogPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);

        ButterKnife.bind(this, view);

        mLoadingView.setVisibility(View.VISIBLE);

        mListOfMessages.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true);

        mListOfMessages.setLayoutManager(layoutManager);

        Bundle bundle = getArguments();

        if (bundle != null) {
            int id = bundle.getInt(POSITION_KEY);
            dialogPresenter.loadMoreDialog(id, fullListMessages);
            userid = id;
        }

        return view;
    }

    @Override
    public void onDialogLoadedSuccess(List<Message> listMessages, boolean loadMore) {

        fullListMessages = listMessages;




        if (loadMore == true) {
            mListOfMessages.getAdapter().notifyDataSetChanged();

          //  mListOfMessages.scrollToPosition(fullListMessages.size() - 50);
        } else {
            ListDialogArrayAdapter listDialogArrayAdapter = new ListDialogArrayAdapter(getActivity(), listMessages, this, userid);

            mListOfMessages.setAdapter(listDialogArrayAdapter);
        }

        mLoadingView.setVisibility(View.GONE);

        dialogPresenter.markAsRead(userid,fullListMessages.get(fullListMessages.size()-1).getId());
    }

    @Override
    public void onDialogLoadFailure() {

    }

    @Override
    public void onDialogLoading() {

    }

}
