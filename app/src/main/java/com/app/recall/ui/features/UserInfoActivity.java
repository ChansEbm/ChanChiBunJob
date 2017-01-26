package com.app.recall.ui.features;

import android.os.Bundle;
import android.support.annotation.Size;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.recall.R;
import com.app.recall.base.BaseActivity;
import com.app.recall.base.BaseRecyclerActivity;
import com.app.recall.entity.item.BaseItem;
import com.app.recall.presenter.UserInfoPresenterImpl;

public class UserInfoActivity extends BaseRecyclerActivity<UserInfoPresenterImpl, BaseItem> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initAdapter() {

    }

    @Override
    protected RecyclerView.ItemDecoration getDecoration() {
        return null;
    }

    @Override
    protected RecyclerView.LayoutManager getLayoutManager() {
        return super.getLayoutManager();
    }

    @Override
    protected boolean areItemsTheSame(BaseItem oldBi, BaseItem updateBi, @Size(2) Integer[]
            position) {
        return false;
    }

    @Override
    protected boolean areContentsTheSame(BaseItem oldBi, BaseItem updateBi, @Size(2) Integer[]
            position) {
        return false;
    }

    @Override
    protected Bundle getChangePayload(BaseItem oldBi, BaseItem updateBi, @Size(2) Integer[]
            position) {
        return null;
    }

    @Override
    protected UserInfoPresenterImpl initPresenter() {
        return new UserInfoPresenterImpl();
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {

    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_user_info;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("UserInformation");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    @Override
    public void onRecyclerViewItemClick(View view, int pos) {

    }
}
