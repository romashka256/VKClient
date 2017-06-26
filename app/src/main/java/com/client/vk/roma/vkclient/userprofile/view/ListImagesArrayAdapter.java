package com.client.vk.roma.vkclient.userprofile.view;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.client.vk.roma.vkclient.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Roma on 19.05.2017.
 */

public class ListImagesArrayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private String[] imageUrls;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.imageview_item_from_list)
        public ImageView mImageView;

        public ViewHolder(View v) {
            super(v);
        }
    }

     ListImagesArrayAdapter(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;

        ButterKnife.bind((Activity) context);

        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.from(context).inflate(R.layout.imageview_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Picasso.with(context)
                .load(imageUrls[position])
                .fit()
                .into((ImageView) holder.itemView);
    }

    @Override
    public int getItemCount() {
        return imageUrls.length;
    }
}
