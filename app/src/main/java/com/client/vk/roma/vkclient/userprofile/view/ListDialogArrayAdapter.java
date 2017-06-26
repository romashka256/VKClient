package com.client.vk.roma.vkclient.userprofile.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.client.vk.roma.vkclient.Message;
import com.client.vk.roma.vkclient.R;

import java.util.List;

/**
 * Created by Roma on 13.06.2017.
 */

public class ListDialogArrayAdapter extends RecyclerView.Adapter<ListDialogArrayAdapter.ViewHolder> {

    private Context context;
    private List<Message> listMessages;
    private LayoutInflater inflater;

    public ListDialogArrayAdapter(Context context, List<Message> listMessages) {
        this.context = context;
        this.listMessages = listMessages;

        inflater = LayoutInflater.from(context);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mBodyMsgTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mBodyMsgTextView = (TextView) itemView.findViewById(R.id.dialog_msg_body_textview);

        }
    }

    @Override
    public ListDialogArrayAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.message_item,parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListDialogArrayAdapter.ViewHolder holder, int position) {

        holder.mBodyMsgTextView.setText(listMessages.get(position).getBody());

    }

    @Override
    public int getItemCount() {
        return listMessages.size();
    }
}
