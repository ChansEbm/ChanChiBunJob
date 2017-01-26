package com.app.recall.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Size;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.adapter.CommonRecyclerAdapter;
import com.app.recall.adapter.HostRepository;
import com.app.recall.adapter.RecyclerViewHolder;
import com.app.recall.base.APP;
import com.app.recall.base.BaseRecyclerActivity;
import com.app.recall.contract.AllPlatformContract;
import com.app.recall.entity.item.HostItem;
import com.app.recall.presenter.BindPlatformPresenterImpl;

import java.util.ArrayList;
import java.util.List;

public class AllPlatformActivity extends BaseRecyclerActivity<BindPlatformPresenterImpl,
        HostItem> implements AllPlatformContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected BindPlatformPresenterImpl initPresenter() {
        return new BindPlatformPresenterImpl();
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3);
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {
        switch (id) {
            case R.id.confirm_action:
                ArrayList<Integer> keys = new ArrayList<>();
                for (HostItem hostItem : adapter.getCurrentData()) {
                    if (hostItem.isChecked()) {
                        keys.add(hostItem.what());
                    }
                }
                Intent intent = new Intent(this, BindPlatformActivity.class)
                        .putIntegerArrayListExtra("platforms", keys);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void initAdapter() {
        List<HostItem> items = HostRepository.getItems();
        for (HostItem item : items) {
            if (item.what() == APP.PHONE_WHAT || item.what() == APP.EMAIL_WHAT) {
                item.setChecked(true);
            }
        }
        adapter = new CommonRecyclerAdapter<HostItem>(this, R.layout.item_host_socity, items) {
            @Override
            public void onBind(RecyclerViewHolder recyclerViewHolder, int position, HostItem
                    hostItem, Bundle bundle) {
                recyclerViewHolder.setCheckedBoxBackground(R.id.checkbox, hostItem.getRes());
                recyclerViewHolder.setCheckBoxChecked(R.id.checkbox, hostItem.isChecked());
                if (bundle != null && !bundle.isEmpty()) {
                    recyclerViewHolder.setCheckBoxChecked(R.id.checkbox, bundle.getBoolean
                            ("checked"));
                }
            }
        };

    }

    @Override
    protected RecyclerView.ItemDecoration getDecoration() {
        return new GirdSpaceItemDecoration(5);
    }

    @Override
    protected boolean areItemsTheSame(HostItem oldBi, HostItem updateBi, @Size(2) Integer[]
            position) {
        return oldBi.what() == updateBi.what();
    }

    @Override
    protected boolean areContentsTheSame(HostItem oldBi, HostItem updateBi, @Size(2) Integer[]
            position) {
        return oldBi.isChecked() && updateBi.isChecked();
    }

    @Override
    protected Bundle getChangePayload(HostItem oldBi, HostItem updateBi, @Size(2) Integer[]
            position) {
        Bundle bundle = new Bundle();
        if (!(oldBi.isChecked() && updateBi.isChecked())) {
            bundle.putBoolean("checked", updateBi.isChecked());
        }
        return bundle.isEmpty() ? null : bundle;
    }

    @Override
    public void onRecyclerViewItemClick(View view, int pos) {
        HostItem hostItem = adapter.getCurrentData().get(pos);
        boolean checked = hostItem.isChecked();
        int what = hostItem.what();
        if (what == APP.EMAIL_WHAT || what == APP.PHONE_WHAT) {
            Toast.makeText(this, "This platform must need to be upload", Toast.LENGTH_SHORT).show();
            return;
        }
        hostItem.setChecked(!checked);
        Bundle bundle = new Bundle();
        bundle.putBoolean("checked", hostItem.isChecked());
        adapter.notifyItemChanged(pos, bundle);
    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_all_platform;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {

    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return false;
    }
}
