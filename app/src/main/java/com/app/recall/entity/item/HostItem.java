package com.app.recall.entity.item;

/**
 * Created by KenChan on 17/1/17.
 */

public class HostItem extends BaseItem {
    private int res;
    private boolean checked;
    private int what;


    public HostItem(int res, boolean checked, int what) {
        this.res = res;
        this.checked = checked;
        this.what = what;
    }

    public int getRes() {
        return res;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int what() {
        return what;
    }

    public void toggleCheck() {
        this.checked = !checked;
    }

    @Override
    protected HostItem clone() throws CloneNotSupportedException {
        HostItem hostItem = null;
        hostItem = HostItem.this.clone();
        return hostItem;
    }
}
