package com.app.assignment.view.list.adapter;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.assignment.R;
import com.app.assignment.view.list.adapter.diffutil.DiffUtilClass;
import com.app.fragmentstack.dao.tables.AppPhotos;
import com.bumptech.glide.Glide;

public class AppImageListAdapter extends PagedListAdapter<AppPhotos, AppImageListAdapter.ItemViewHolder> {

    private final Context mCtx;

    public AppImageListAdapter(Context context) {
        super(new DiffUtilClass());
        this.mCtx = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.adapter_photo, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        AppPhotos item = getItem(position);
        Glide.with(mCtx).load(item.getPath()).into(holder.imageViewName);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewName;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imageViewName = itemView.findViewById(R.id.imageViewName);
        }
    }
}
