package com.client.vk.roma.vkclient.userprofile.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.client.vk.roma.vkclient.Dialog;
import com.client.vk.roma.vkclient.R;

import java.util.List;

/**
 * Created by Roma on 19.05.2017.
 */

public class ListDialogsArrayAdapter extends RecyclerView.Adapter<ListDialogsArrayAdapter.ViewHolder> {


    private static IDialogsView view;
    private Context context;
    private LayoutInflater inflater;
    private static List<Dialog> mDialogList;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //    @Bind(R.id.dialog_item_dialog_photo)
        ImageView mPhotoDialog;
        //    @Bind(R.id.dialog_item_name_dialog)
        TextView mNameOfDialog;
        //    @Bind(R.id.dilalog_item_message_from_dialog)
        TextView mMessageOfDialog;

        ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);

            mPhotoDialog = (ImageView) v.findViewById(R.id.dialog_item_dialog_photo);
            mMessageOfDialog = (TextView) v.findViewById(R.id.dilalog_item_message_from_dialog);
            mNameOfDialog = (TextView) v.findViewById(R.id.dialog_item_name_dialog);

        }

        @Override
        public void onClick(View v) {
            view.onItemClicked(mDialogList.get(getAdapterPosition()));
        }
    }

    ListDialogsArrayAdapter(Context context, List<Dialog> dialogList, IDialogsView view) {
        this.context = context;
        this.mDialogList = dialogList;
        this.view = view;


        // ButterKnife.bind((Activity) context);

        inflater = LayoutInflater.from(context);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.from(context).inflate(R.layout.dialogs_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Dialog dial = mDialogList.get(position);

        holder.mNameOfDialog.setText(dial.getTitle());
        holder.mMessageOfDialog.setText(dial.getBody());

        /*Picasso.with(context)
                .load(imageUrls[position])
                .fit()
                .into((ImageView) holder.itemView); */

    }

    @Override
    public int getItemCount() {
        return mDialogList.size();
    }
}
