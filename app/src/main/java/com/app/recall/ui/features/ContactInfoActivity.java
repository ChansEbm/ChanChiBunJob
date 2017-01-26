package com.app.recall.ui.features;

import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Size;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.recall.ContactInfoLayout;
import com.app.recall.R;
import com.app.recall.adapter.CommonRecyclerAdapter;
import com.app.recall.adapter.RecyclerViewHolder;
import com.app.recall.base.BaseRecyclerActivity;
import com.app.recall.entity.retrofit.MeEntity;
import com.app.recall.manager.ActivityManager;
import com.app.recall.presenter.ContactInfoPresenterImpl;

import org.greenrobot.eventbus.EventBus;

public class ContactInfoActivity extends BaseRecyclerActivity<ContactInfoPresenterImpl, MeEntity
        .ContactsBean.PlatformsBean> {
    private MeEntity.ContactsBean source = new MeEntity.ContactsBean();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        source = getIntent().getParcelableExtra("contact");
        if (source == null) {
            ActivityManager.getActivityManager().finishActivity(this);
            return;
        }
        ContactInfoLayout layout = DataBindingUtil.setContentView(this, contentViewId());
        layout.setContact(source);
    }

    @Override
    protected void initAdapter() {
        adapter = new CommonRecyclerAdapter<MeEntity.ContactsBean.PlatformsBean>(this, R.layout
                .item_host_socity, source.getPlatforms()) {
            @Override
            public void onBind(RecyclerViewHolder recyclerViewHolder, int position, MeEntity
                    .ContactsBean.PlatformsBean platformsBean, Bundle bundle) {
                switch (platformsBean.getName()) {
                    case "facebook":
                        recyclerViewHolder.setCheckedBoxBackground(R.id.checkbox, R.drawable
                                .facebook_nor);
                        break;
                    case "twitter":
                        recyclerViewHolder.setCheckedBoxBackground(R.id.checkbox, R.drawable
                                .twitter_nor);
                        break;
                    case "google+":
                        recyclerViewHolder.setCheckedBoxBackground(R.id.checkbox, R.drawable
                                .google_plus_nor);
                        break;
                    case "wechat":
                        recyclerViewHolder.setCheckedBoxBackground(R.id.checkbox, R.drawable
                                .wechat_nor);
                        break;
                    case "qq":
                        recyclerViewHolder.setCheckedBoxBackground(R.id.checkbox, R.drawable
                                .qq_nor);
                        break;
                    case "email":
                        recyclerViewHolder.setCheckedBoxBackground(R.id.checkbox, R.drawable
                                .email_nor);
                        break;
                }
            }
        };
    }

    @Override
    protected RecyclerView.ItemDecoration getDecoration() {
        return new GirdSpaceItemDecoration(5);
    }

    @Override
    protected boolean areItemsTheSame(MeEntity.ContactsBean.PlatformsBean oldBi, MeEntity
            .ContactsBean.PlatformsBean updateBi, @Size(2) Integer[] position) {
        return oldBi.getUid().equals(updateBi.getUid());
    }

    @Override
    protected boolean areContentsTheSame(MeEntity.ContactsBean.PlatformsBean oldBi, MeEntity
            .ContactsBean.PlatformsBean updateBi, @Size(2) Integer[] position) {

        return oldBi.getName().equals(updateBi.getName()) && oldBi.getNickname().equals(updateBi
                .getNickname()) && oldBi.getProfilePhoto().equals(updateBi.getProfilePhoto());
    }

    @Override
    protected Bundle getChangePayload(MeEntity.ContactsBean.PlatformsBean oldBi, MeEntity
            .ContactsBean.PlatformsBean updateBi, @Size(2) Integer[] position) {
        Bundle bundle = new Bundle();
        if (!TextUtils.equals(updateBi.getName(), oldBi.getName()))
            bundle.putString("name", updateBi.getName());
        if (!TextUtils.equals(updateBi.getNickname(), oldBi.getNickname()))
            bundle.putString("nickName", updateBi.getNickname());
        if (!TextUtils.equals(updateBi.getProfilePhoto(), oldBi.getProfilePhoto()))
            bundle.putString("pto", updateBi.getProfilePhoto());
        return bundle.isEmpty() ? null : bundle;
    }

    @Override
    protected ContactInfoPresenterImpl initPresenter() {
        return new ContactInfoPresenterImpl();
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
        return R.layout.activity_contact;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {

    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_delete) {
            //delete contact
            //            Dialog dialog=new Dialog(this,android.R.style.)
            AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Delete Contact")
                    .setMessage("Are you sure delete contact " + source.getRealName() + "?")
                    .setNegativeButton("N", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }).setPositiveButton("Y", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    MeEntity.ContactsBean contact = ContactInfoActivity.this.getIntent()
                            .getParcelableExtra("contact");
                    EventBus.getDefault().post(contact);
                    ActivityManager.getActivityManager().finishActivity(ContactInfoActivity.this);
                }
            }).create();
            dialog.show();
        }

        return item.getItemId() == R.menu.menu_delete;
    }

    @Override
    public void onRecyclerViewItemClick(View view, int pos) {
        MeEntity.ContactsBean.PlatformsBean platformsBean = adapter.getCurrentData().get(pos);
        String openId = platformsBean.getOpenId();
        switch (platformsBean.getName()) {
            case "email":
                break;
            case "phone":
                break;
        }
        ClipboardManager manager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        //        manager.setPrimaryClip(new ClipData());
    }
}
