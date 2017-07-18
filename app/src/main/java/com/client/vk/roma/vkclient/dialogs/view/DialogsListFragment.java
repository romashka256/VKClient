package com.client.vk.roma.vkclient.dialogs.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.dialogs.models.Dialog;
import com.client.vk.roma.vkclient.dialogs.presenters.DialogsPresenter;
import com.vk.sdk.api.VKError;

import org.joda.time.DateTime;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roma on 13.07.2017.
 */

public class DialogsListFragment extends Fragment implements IDialogsView {

    @Bind(R.id.list_of_dialogs)
    RecyclerView mDialogsList;
    @Bind(R.id.toolbar_actionbar)
    Toolbar mToolbar;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private DialogsPresenter dialogsPresenter;
    private Context context;
    private ListDialogsArrayAdapter dialogsArrayAdapter;

    public static DialogsListFragment newInstance() {
        DialogsListFragment fragment = new DialogsListFragment();

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogsPresenter = new DialogsPresenter(this);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogs_list_fragment, container, false);

        ButterKnife.bind(this, view);


        mDialogsList.setHasFixedSize(true);
        mDialogsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        dialogsPresenter.loadDialogs(0, 0);

        mSwipeRefreshLayout.setOnRefreshListener(refreshListener);


        return view;
    }

    private SwipeRefreshLayout.OnRefreshListener refreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {

            dialogsPresenter.loadDialogs(0, 0);

        }
    };


    @Override
    public void onDialogsLoadedSuccess(List<Dialog> dialog, int more) {


        if (more == 0) {
            dialogsArrayAdapter = new ListDialogsArrayAdapter(getActivity(), dialog, this);
            mDialogsList.setAdapter(dialogsArrayAdapter);

        }

        dialogsArrayAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void onDialogsLoadFailure(VKError error) {
        Toast.makeText(getActivity(), "Dialog Loading Failed", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDialogsLoading() {

    }

    @Override
    public void onItemClicked(Dialog dialog) {

        FragmentManager fm = getActivity().getSupportFragmentManager();
        dialogsPresenter.onItemClicked(fm, dialog);
    }

    @Override
    public String dialogTimeCount(DateTime dateTime) {
        return dialogsPresenter.dialogTimeCount(dateTime);
    }
}
