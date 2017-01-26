package com.app.recall.ui.features;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Size;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.adapter.CommonRecyclerAdapter;
import com.app.recall.adapter.HostRepository;
import com.app.recall.adapter.RecyclerViewHolder;
import com.app.recall.base.APP;
import com.app.recall.base.BaseRecyclerActivity;
import com.app.recall.contract.SendMyNameCardContract;
import com.app.recall.entity.item.HostItem;
import com.app.recall.entity.retrofit.MeEntity;
import com.app.recall.manager.ActivityManager;
import com.app.recall.presenter.SendMyNameCardPresenterImpl;
import com.app.recall.widget.HostSwipeLayout;
import com.github.pwittchen.prefser.library.Prefser;

public class SendMyNameCardActivity extends BaseRecyclerActivity<SendMyNameCardPresenterImpl,
        HostItem> implements SendMyNameCardContract.View, HostSwipeLayout.Callback {
    private FrameLayout topView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View
                .SYSTEM_UI_FLAG_LAYOUT_STABLE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, android.R.color
                    .transparent));
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            ((FrameLayout) decorView).removeViewAt(0);
        }
    }

    @Override
    protected void initAdapter() {
        adapter = new CommonRecyclerAdapter<HostItem>(this, R.layout.item_host_socity,
                HostRepository.getItems()) {
            @Override
            public void onBind(RecyclerViewHolder recyclerViewHolder, int position, HostItem
                    hostItem, Bundle bundle) {
                recyclerViewHolder.setCheckedBoxBackground(R.id.checkbox, hostItem.getRes());
                recyclerViewHolder.setCheckBoxChecked(R.id.checkbox, hostItem.isChecked());
                if (bundle != null) {
                    boolean check = bundle.getBoolean("check");
                    recyclerViewHolder.setCheckBoxChecked(R.id.checkbox, check);
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
        bundle.putBoolean("check", updateBi.isChecked());
        return bundle.isEmpty() ? null : bundle;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return new GridLayoutManager(this, 3);
    }


    @Override
    protected SendMyNameCardPresenterImpl initPresenter() {
        return new SendMyNameCardPresenterImpl();
    }

    @Override
    protected void initViews() {
        topView = (FrameLayout) findViewById(R.id.top_content);
        HostSwipeLayout swipeLayout = (HostSwipeLayout) findViewById(R.id.swipeLayout);
        swipeLayout.setCallback(this);

        Bundle extras = getIntent().getExtras();
        String uid = extras.getString(HostActivity.KEY_MATCH_UID);
        presenter.searchUID(uid);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {
        if (id == R.id.confirm_action) {
            presenter.startTopAnimation(topView, R.anim.anim_top_out);
        }
    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_send_my_name_card;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {

    }


    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return false;
    }

    @Override
    public boolean isPlatformSelected() {
        boolean isPlatformSelect = false;
        for (HostItem hostItem : adapter.getCurrentData()) {
            if (hostItem.isChecked()) {
                isPlatformSelect = true;
                break;
            }
        }
        return isPlatformSelect;
    }

    @Override
    public void onLayoutDone() {
        ActivityManager.getActivityManager().finishActivity(this);
    }

    @Override
    public void onRecyclerViewItemClick(View view, int pos) {
        Toast.makeText(this, "开发中...", Toast.LENGTH_SHORT).show();
        Prefser prefser = new Prefser(this);
        switch (adapter.getCurrentData().get(pos).what()) {
            case APP.FACEBOOK_WHAT:

                break;
            case APP.EMAIL_WHAT:
                break;
            case APP.TWITTER_WHAT:
                break;
            case APP.GOOGLE_WHAT:
                break;
            case APP.QQ_WHAT:
                break;
            case APP.WECHAT_WHAT:
                break;
        }
        if (prefser.contains(APP.ME)) {
            MeEntity meEntity = prefser.get(APP.ME, MeEntity.class, new MeEntity());

        }
        adapter.getCurrentData().get(pos).toggleCheck();
        adapter.notifyItemChanged(pos, adapter.getCurrentData().get(pos));

    }


    @Override
    public void handshakeFailed() {
        Toast.makeText(this, "User not response", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handshakeSuccess() {
        View successView = LayoutInflater.from(this).inflate(R.layout.host_match_success,
                topView, false);
        topView.addView(successView);
        presenter.startTopAnimation(topView, R.anim.anim_top_in);
    }
}
