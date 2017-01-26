package com.app.recall.ui.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.adapter.CommonRecyclerAdapter;
import com.app.recall.adapter.HostRepository;
import com.app.recall.adapter.RecyclerViewHolder;
import com.app.recall.base.APP;
import com.app.recall.base.BaseRecyclerActivity;
import com.app.recall.contract.HostContract;
import com.app.recall.entity.item.HostItem;
import com.app.recall.entity.retrofit.UserEntity;
import com.app.recall.presenter.HostPresenter;
import com.app.recall.util.DensityUtils;
import com.bumptech.glide.Glide;
import com.github.pwittchen.prefser.library.Prefser;
import com.orhanobut.logger.Logger;
import com.yancy.gallerypick.utils.ScreenUtils;

public class HostActivity extends BaseRecyclerActivity<HostPresenter, HostItem> implements
        HostContract.View {
    private FrameLayout topContent;
    private View searchingView;
    private View nobodyFoundView;
    private View matchView;

    public static final int SEARCHING = 0;
    public static final int NOBODY = 1;
    public static final int MATCH = 2;

    public static final String KEY_STATE = "state";
    public static final String KEY_USER = "userName";
    public static final String KEY_AVATAR = "userImg";
    public static final String KEY_MATCH_UID = "match_uid";
    public static final String KEY_CANCEL = "cancel";
    public static final String KEY_HOST = "host";

    private Bundle bundle = new Bundle();

    static {
        System.loadLibrary("sinvoice");
        Log.d("tag", "static initializer() called");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle.putBoolean(KEY_CANCEL, false);
        if (savedInstanceState != null) {
            this.bundle.clear();
            this.bundle = savedInstanceState.getBundle(KEY_HOST);
        }
        addTopContent();
        presenter.startSinvoice();
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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.clear();
        outState.putBundle(KEY_HOST, bundle);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.lifeCircle(HostContract.Model.PAUSE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.lifeCircle(HostContract.Model.DESTROY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }


    @Override
    protected HostPresenter initPresenter() {
        return new HostPresenter();
    }

    @Override
    protected void initViews() {
        topContent = (FrameLayout) findViewById(R.id.top_content);
        LayoutInflater inflater = LayoutInflater.from(this);

        searchingView = inflater.inflate(R.layout.host_searching, topContent, false);
        nobodyFoundView = inflater.inflate(R.layout.host_nobody_found, topContent, false);
        matchView = inflater.inflate(R.layout.host_match, topContent, false);

        Prefser prefser = new Prefser(this);
        UserEntity.UserBean userBean = prefser.get(APP.USER_INFO, UserEntity.UserBean.class, new
                UserEntity.UserBean());
        String profilePhoto = userBean.getProfilePhoto();
        Log.i(TAG, "initViews: " + profilePhoto);
        int av = ScreenUtils.getScreenWidth(this) / 4;
        Glide.with(this).load(profilePhoto).override(av, av).into((ImageView) findViewById(R.id
                .avatar));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {
        switch (id) {
            case R.id.cancel_action:
                findViewById(R.id.match_action).setEnabled(true);
                presenter.startTopAnimation(topContent, R.anim.anim_top_out);
                bundle.putBoolean(KEY_CANCEL, true);
                presenter.clearSinvoice();
                break;
            case R.id.confirm_action:
                if (bundle != null && !bundle.isEmpty() && bundle.containsKey(KEY_MATCH_UID)) {
                    startActivity(new Intent(this, SendMyNameCardActivity.class).putExtras(bundle));
                    Toast.makeText(this, "waiting user confirm", Toast.LENGTH_SHORT).show();
                } else Toast.makeText(this, "get user profile failed", Toast.LENGTH_SHORT).show();

                presenter.clearSinvoice();
                presenter.startTopAnimation(topContent, R.anim.anim_top_out);
                break;
            case R.id.match_action:
                v.setEnabled(false);
                bundle.putInt(KEY_STATE, SEARCHING);
                bundle.putBoolean(KEY_CANCEL, false);
                addTopContent();
                presenter.startSinvoice();
                break;

            case R.id.setting_action:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case R.id.contact_action:
                startActivity(new Intent(this, AllContactActivity.class));
                break;
        }
    }

    @Override
    public void networkError() {
        Logger.i("error");
    }

    @Override
    public int contentViewId() {
        return R.layout.activity_host;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Recall");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return false;
    }

    @Override
    public void matcher(String userName, String profileImg, String uid) {
        findViewById(R.id.match_action).setEnabled(true);

        bundle.putInt(KEY_STATE, MATCH);
        bundle.putString(KEY_USER, userName);
        bundle.putString(KEY_AVATAR, profileImg);
        bundle.putBoolean(KEY_CANCEL, false);
        bundle.putString(KEY_MATCH_UID, uid);
        addTopContent();
    }

    @Override
    public void nobodyFound() {
        findViewById(R.id.match_action).setEnabled(true);

        bundle.putInt(KEY_STATE, NOBODY);
        bundle.putBoolean(KEY_CANCEL, false);
        addTopContent();
    }


    private void addTopContent() {
        if (topContent == null) return;
        anim();
        topContent.removeAllViews();
        int state = bundle.getInt(KEY_STATE, SEARCHING);
        switch (state) {
            case MATCH:
                if (matchView != null) {
                    String img = bundle.getString(KEY_AVATAR, "");
                    String userName = bundle.getString(KEY_USER, "user");
                    ImageView avatar = (ImageView) matchView.findViewById(R.id.avatar);
                    TextView name = (TextView) matchView.findViewById(R.id.name);
                    if (!TextUtils.isEmpty(img)) {
                        int size = DensityUtils.dip2px(this, 24);
                        Glide.with(this).load(img).override(size, size).into(avatar);
                    }
                    if (!TextUtils.isEmpty(userName)) {
                        name.setText(userName);
                    } else {
                        name.setText("User");
                    }
                    topContent.addView(matchView);
                }
                break;
            case NOBODY:
                if (nobodyFoundView != null) {
                    topContent.addView(nobodyFoundView);
                }
                break;
            case SEARCHING:
                if (searchingView != null) {
                    topContent.addView(searchingView);
                }
                break;
        }
    }

    private void anim() {
        boolean cancel = bundle.getBoolean(KEY_CANCEL, false);
        if (cancel) {
            presenter.startTopAnimation(topContent, R.anim.anim_top_out);
        } else {
            presenter.startTopAnimation(topContent, R.anim.anim_top_in);
        }
    }

    @Override
    public void onRecyclerViewItemClick(View view, int pos) {

    }
}
