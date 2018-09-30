package com.app.assignment.view.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.app.assignment.MarsplayApplication;
import com.app.assignment.R;
import com.app.assignment.view.list.adapter.AppImageListAdapter;
import com.app.assignment.view.list.adapter.callbacks.Anon;
import com.app.assignment.view.list.adapter.paging.AppViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppImageListLayout extends ConstraintLayout {

    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    public AppImageListLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize() {
        inflate(getContext(), R.layout.layout_list, this);
        MarsplayApplication.get().getApplicationComponent().inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        AppImageListAdapter adapter = new AppImageListAdapter(getContext());
        recyclerview.setAdapter(adapter);
        AppCompatActivity appCompatActivity = (AppCompatActivity) getContext();
        AppViewModel appViewModel = ViewModelProviders.of(appCompatActivity).get(AppViewModel.class);
        appViewModel.getApplicationLiveData().observe(appCompatActivity, new Anon(adapter));
    }
}
