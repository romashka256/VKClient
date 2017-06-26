package com.client.vk.roma.vkclient.userprofile.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.client.vk.roma.vkclient.Message;
import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.userprofile.presenter.DialogPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roma on 13.06.2017.
 */

public class DialogFragment extends Fragment implements IDialogView {

    private DialogPresenter dialogPresenter;
    private final String POSITION_KEY = "position_key";

    public static DialogFragment newInstance() {
        DialogFragment fragment = new DialogFragment();
        return fragment;
    }

    @Bind(R.id.dialog_msg_body_textview)
    TextView mBodyMsgTextView;

    @Bind(R.id.dialog_fragment_recycler)
    RecyclerView mListOfMessages;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dialogPresenter = new DialogPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment,container);

        ButterKnife.bind(this,view);

        mListOfMessages.setHasFixedSize(true);

        Bundle bundle = getArguments();

        if(bundle != null){
            int id = bundle.getInt(POSITION_KEY);
            dialogPresenter.loadDialog(id);
        }

        return  view;
    }

    @Override
    public void onDialogLoadedSuccess(List<Message> dialog) {
        mListOfMessages.setAdapter(new ListDialogArrayAdapter(getContext(),dialog));
    }

    @Override
    public void onDialogLoadFailure() {

    }

    @Override
    public void onDialogLoading() {

    }
}
