package com.client.vk.roma.vkclient.dialogs.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.client.vk.roma.vkclient.dialogs.models.Message;
import com.client.vk.roma.vkclient.R;
import com.client.vk.roma.vkclient.dialogs.presenters.DialogPresenter;

import java.util.List;

/**
 * Created by Roma on 13.06.2017.
 */

public class ListDialogArrayAdapter extends RecyclerView.Adapter<ListDialogArrayAdapter.ViewHolder> {

    private DialogPresenter dialogPresenter;

    private Context context;
    private List<Message> listMessages;
    private LayoutInflater inflater;
    private String curMessage;
    private int userid;

    public ListDialogArrayAdapter(Context context, List<Message> listMessages, IDialogView view,int userid) {
        this.context = context;
        this.listMessages = listMessages;
        this.userid = userid;

        dialogPresenter = new DialogPresenter(view);

        inflater = LayoutInflater.from(context);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mOutBodyMsgTextView;
        private TextView mInBodyMsgTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mOutBodyMsgTextView = (TextView) itemView.findViewById(R.id.dialog_out_msg_body_textview);
            mInBodyMsgTextView = (TextView) itemView.findViewById(R.id.dialog_in_msg_body_textview);

        }
    }

    @Override
    public ListDialogArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.message_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListDialogArrayAdapter.ViewHolder holder, int position) {

        curMessage = listMessages.get(position).getBody();

        if(listMessages.get(position).getOut() == 0){
            holder.mOutBodyMsgTextView.setText(curMessage);
            holder.mInBodyMsgTextView.setBackgroundColor(Color.TRANSPARENT);
            if(listMessages.get(position).isReaded() == 0){
                holder.mOutBodyMsgTextView.setBackgroundColor(context.getResources().getColor(R.color.unreaded_message));
            }
        } else{
            holder.mInBodyMsgTextView.setText(curMessage);
            holder.mOutBodyMsgTextView.setBackgroundColor(Color.TRANSPARENT);
            if(listMessages.get(position).isReaded() == 0){
                holder.mInBodyMsgTextView.setBackgroundColor(context.getResources().getColor(R.color.unreaded_message));
            }
        }


        if(position == 1){
            dialogPresenter.loadMoreDialog(userid,listMessages);
        }
    }

    @Override
    public int getItemCount() {
        return listMessages.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
