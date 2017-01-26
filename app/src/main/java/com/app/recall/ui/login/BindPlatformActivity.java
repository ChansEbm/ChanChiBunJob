package com.app.recall.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.recall.R;
import com.app.recall.base.APP;
import com.app.recall.base.BasePlatformActivity;
import com.app.recall.contract.BindPlatformContract;
import com.app.recall.presenter.BindPlatformPresenterImpl;

import java.util.ArrayList;

import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.twitter.Twitter;
import cn.sharesdk.wechat.friends.Wechat;

public class BindPlatformActivity extends BasePlatformActivity<BindPlatformPresenterImpl>
        implements BindPlatformContract.View {
    private Toolbar toolbar;
    private ArrayList<Integer> platforms = new ArrayList<>();
    private AppCompatEditText openid;
    private int what = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String openid = this.openid.getText().toString();
        if (TextUtils.isEmpty(openid)) {
            Toast.makeText(this, "Please input your openId", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (item.getItemId() == R.id.menu_done) {
            if (what == APP.EMAIL_WHAT) {
                presenter.uploadPlatform("email", openid, "email token");
            } else if (what == APP.PHONE_WHAT) {
                presenter.uploadPlatform("phone", openid, "phone token");
            } else presenter.uploadPlatform();
        }

        return true;
    }

    @Override
    protected void onPlatformSuccess(String name, String userName, String token) {
        openid.setText(userName);
        presenter.savePlatformField(name, userName, token);
    }

    @Override
    protected BindPlatformPresenterImpl initPresenter() {
        return new BindPlatformPresenterImpl();
    }

    @Override
    protected void initViews() {
        openid = (AppCompatEditText) findViewById(R.id.openid);
        platforms = getIntent().getIntegerArrayListExtra("platforms");
        authPlatform(platforms.get(0));
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void onClick(View v, int id) {

    }

    @Override
    protected void onPlatformFailed(String name) {

    }

    @Override
    public void networkError() {

    }

    @Override
    public int contentViewId() {
        return R.layout.activity_bind_platform;
    }

    @Override
    public void toolbarPrepared(Toolbar toolbar) {
        this.toolbar = toolbar;
        presenter.titleWhat(platforms.get(0), toolbar);
    }

    @Override
    public boolean isSetupToolbarDefaultConfig() {
        return true;
    }

    @Override
    public void uploadPlatformSuccess(int what) {
        this.platforms.remove(what);
        if (platforms.isEmpty()) {
            startActivity(new Intent(this, LoginActivity.class).addFlags(Intent
                    .FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
            return;
        }
        presenter.titleWhat(platforms.get(0), toolbar);
        authPlatform(platforms.get(0));
    }

    private void authPlatform(int what) {
        this.what = what;
        switch (what) {
            case APP.EMAIL_WHAT:
                Toast.makeText(this, "Please input your email address", Toast.LENGTH_SHORT).show();
                break;
            case APP.FACEBOOK_WHAT:
                auth(APP.FACEBOOK);
                break;
            case APP.TWITTER_WHAT:
                auth(Twitter.NAME);
                break;
            case APP.GOOGLE_WHAT:
                auth(APP.GOOGLEPLUS);
                break;
            case APP.QQ_WHAT:
                auth(QQ.NAME);
                break;
            case APP.WECHAT_WHAT:
                auth(Wechat.NAME);
                break;
            case APP.PHONE_WHAT:
                Toast.makeText(this, "Please input your phone", Toast.LENGTH_SHORT).show();
                break;
            case APP.WHATSAPP_WHAT:
                toolbar.setTitle("Bind Whatsapp");
                break;
        }
    }
}
