package com.app.recall.ui.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Size;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.adapter.CommonRecyclerAdapter;
import com.app.recall.adapter.RecyclerViewHolder;
import com.app.recall.base.APP;
import com.app.recall.base.BaseRecyclerActivity;
import com.app.recall.contract.AllContactContract;
import com.app.recall.entity.retrofit.MeEntity;
import com.app.recall.presenter.AllContactPresenterImpl;
import com.github.pwittchen.prefser.library.Prefser;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Set;

public class AllContactActivity extends BaseRecyclerActivity<AllContactPresenterImpl, MeEntity
        .ContactsBean> implements AllContactContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initAdapter() {
        Prefser prefser = new Prefser(this);
        MeEntity meEntity = prefser.get(APP.ME, MeEntity.class, new MeEntity());
        if (meEntity.getContacts().isEmpty()) {
            Toast.makeText(this, "You have no contacts", Toast.LENGTH_SHORT).show();
        }
        adapter = new CommonRecyclerAdapter<MeEntity.ContactsBean>(this, R.layout.item_contacts,
                meEntity.getContacts()) {
            @Override
            public void onBind(RecyclerViewHolder recyclerViewHolder, int position, MeEntity
                    .ContactsBean contactsBean, Bundle bundle) {
                recyclerViewHolder.loadImageUrl(R.id.avatar, contactsBean.getProfilePhoto());
                recyclerViewHolder.setText(R.id.name, contactsBean.getRealName());
                if (bundle != null) {
                    Set<String> keys = bundle.keySet();
                    for (String key : keys) {
                        if (key.equals("pto"))
                            recyclerViewHolder.loadImageUrl(R.id.avatar, bundle.getString(key));
                        else recyclerViewHolder.setText(R.id.name, bundle.getString(key));
                    }
                }
            }
        };
    }

    @Override
    protected RecyclerView.ItemDecoration getDecoration() {
        return new GirdSpaceItemDecoration(5);
    }

    @Override
    protected boolean areItemsTheSame(MeEntity.ContactsBean oldBi, MeEntity.ContactsBean
            updateBi, @Size(2) Integer[] position) {
        return oldBi.getCid().equals(updateBi.getCid());
    }

    @Override
    protected boolean areContentsTheSame(MeEntity.ContactsBean oldBi, MeEntity.ContactsBean
            updateBi, @Size(2) Integer[] position) {
        return oldBi.getRealName().equals(updateBi.getRealName()) && oldBi.getProfilePhoto()
                .equals(updateBi.getProfilePhoto());
    }

    @Override
    protected Bundle getChangePayload(MeEntity.ContactsBean oldBi, MeEntity.ContactsBean
            updateBi, @Size(2) Integer[] position) {
        Bundle bundle = new Bundle();
        if (!oldBi.getProfilePhoto().equals(updateBi.getProfilePhoto()))
            bundle.putString("pto", updateBi.getProfilePhoto());
        if (!oldBi.getRealName().equals(updateBi.getRealName()))
            bundle.putString("realName", updateBi.getRealName());

        return bundle.isEmpty() ? null : bundle;
    }

    @Override
    protected AllContactPresenterImpl initPresenter() {
        return new AllContactPresenterImpl();
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
        return R.layout.activity_all_contact;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        toolbar.setTitle("Contacts");
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    @Override
    public void onRecyclerViewItemClick(View view, int pos) {
        MeEntity.ContactsBean contactsBean = adapter.getCurrentData().get(pos);
        startActivity(new Intent(this, ContactInfoActivity.class).putExtra("contact",
                contactsBean));
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void deleteContact(MeEntity.ContactsBean contact) {
        if (adapter.getCurrentData().contains(contact)) {
            adapter.getCurrentData().remove(contact);
            adapter.notifyDataSetChanged();
        }
    }
}
