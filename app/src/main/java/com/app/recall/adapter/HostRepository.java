package com.app.recall.adapter;

import com.app.recall.R;
import com.app.recall.base.APP;
import com.app.recall.entity.item.HostItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KenChan on 17/1/17.
 */

public class HostRepository {
    //    private static List<HostItem> items = new ArrayList<>();

    public static List<HostItem> getItems() {
        List<HostItem> items = new ArrayList<>();
        items.clear();
        items.add(new HostItem(R.drawable.facebook_selector, false, APP.FACEBOOK_WHAT));
        items.add(new HostItem(R.drawable.google_plus_selector, false, APP.GOOGLE_WHAT));
        items.add(new HostItem(R.drawable.twitter_selector, false, APP.TWITTER_WHAT));
        items.add(new HostItem(R.drawable.qq_selector, false, APP.QQ_WHAT));
        items.add(new HostItem(R.drawable.whats_app_selector, false, APP.WHATSAPP_WHAT));
        items.add(new HostItem(R.drawable.wechat_selector, false, APP.WECHAT_WHAT));
        items.add(new HostItem(R.drawable.emial_selector, false, APP.EMAIL_WHAT));
        items.add(new HostItem(R.drawable.phone_selector, false, APP.PHONE_WHAT));
        return items;
    }
}
