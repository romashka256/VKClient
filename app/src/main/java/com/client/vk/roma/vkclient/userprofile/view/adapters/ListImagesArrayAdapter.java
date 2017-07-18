package com.client.vk.roma.vkclient.userprofile.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.client.vk.roma.vkclient.R;
import com.squareup.picasso.Picasso;

/**
 * Created by Roma on 19.05.2017.
 */

public class ListImagesArrayAdapter extends RecyclerView.Adapter<ListImagesArrayAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private String[] imageUrls;


    public static class ViewHolder extends RecyclerView.ViewHolder {

      //  @Bind(R.id.imageview_item_from_list)
        public ImageView mImageView;

        ViewHolder(View v) {
            super(v);

            mImageView = (ImageView)v.findViewById(R.id.imageview_item_from_list);

        }
    }

     public ListImagesArrayAdapter(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.imageview_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListImagesArrayAdapter.ViewHolder holder, int position) {

        Picasso.with(context)
                .load(imageUrls[position])
                .resize(150,150)
                .centerCrop()
                .into((ImageView) holder.itemView);
    }

    @Override
    public int getItemCount() {
        return imageUrls.length;
    }
}
