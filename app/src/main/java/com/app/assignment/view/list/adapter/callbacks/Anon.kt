package com.app.assignment.view.list.adapter.callbacks

import android.arch.lifecycle.Observer
import android.arch.paging.PagedList
import com.app.assignment.view.list.adapter.AppImageListAdapter
import com.app.fragmentstack.dao.tables.AppPhotos

class Anon(userAdapter: AppImageListAdapter) : Observer<PagedList<AppPhotos>> {
    var adapter = userAdapter;
    override fun onChanged(pagedList: PagedList<AppPhotos>?) {
        if (pagedList == null || pagedList?.isEmpty()) {
            return
        }
        adapter.submitList(pagedList)
    }
}