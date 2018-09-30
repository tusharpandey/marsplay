package com.app.assignment.view.list.adapter.diffutil;

import android.support.v7.util.DiffUtil;

import com.app.fragmentstack.dao.tables.AppPhotos;

public class DiffUtilClass extends DiffUtil.ItemCallback<AppPhotos>{
    @Override
    public boolean areItemsTheSame(AppPhotos oldItem, AppPhotos newItem) {
        return oldItem.getPicId() == newItem.getPicId();
    }

    @Override
    public boolean areContentsTheSame(AppPhotos oldItem, AppPhotos newItem) {
        return oldItem.equals(newItem);
    }
}
