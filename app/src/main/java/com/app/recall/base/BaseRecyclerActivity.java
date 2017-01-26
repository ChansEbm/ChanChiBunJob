package com.app.recall.base;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.recall.R;
import com.app.recall.adapter.CommonRecyclerAdapter;
import com.app.recall.entity.item.BaseItem;
import com.app.recall.impl.OnRecyclerViewItemClickListener;
import com.app.recall.presenter.IPresenter;
import com.app.recall.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KenChan on 17/1/3.
 */

public abstract class BaseRecyclerActivity<IP extends IPresenter, BI extends BaseItem> extends
        BaseActivity<IP> implements OnRecyclerViewItemClickListener {
    protected RecyclerView recyclerView;
    protected CommonRecyclerAdapter<BI> adapter;
    protected List<BI> originList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();
        if (adapter != null) {
            recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
            recyclerView.addItemDecoration(getDecoration());
            recyclerView.setLayoutManager(getLayoutManager());
            recyclerView.setAdapter(adapter);
            adapter.setListener(this);
        }
    }

    protected void dispatchUpdates(List<BI> update) {
        if (adapter == null) return;
        //        Log.i(TAG, "dispatchUpdates: " + updateList.equals(adapter.getCurrentData()));
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffCallback(adapter
                .getCurrentData(), update), false);
        result.dispatchUpdatesTo(adapter);
        adapter.updateList(update);

        //        this.originList.clear();
        //        this.originList.addAll(update);
    }

    protected RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }


    protected abstract void initAdapter();

    protected abstract RecyclerView.ItemDecoration getDecoration();

    protected abstract boolean areItemsTheSame(BI oldBi, BI updateBi, @Size(2) Integer[] position);

    protected abstract boolean areContentsTheSame(BI oldBi, BI updateBi, @Size(2) Integer[]
            position);

    protected abstract Bundle getChangePayload(BI oldBi, BI updateBi, @Size(2) Integer[] position);

    public class DiffCallback extends DiffUtil.Callback {
        private List<BI> originList, updateList;

        DiffCallback(List<BI> originList, List<BI> updateList) {
            this.originList = originList;
            this.updateList = updateList;
        }

        @Override
        public int getOldListSize() {
            return originList == null ? 0 : originList.size();
        }

        @Override
        public int getNewListSize() {
            return updateList == null ? 0 : updateList.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            BI originBi = originList.get(oldItemPosition);
            BI updateBi = updateList.get(newItemPosition);
            return BaseRecyclerActivity.this.areItemsTheSame(originBi, updateBi, new
                    Integer[]{oldItemPosition, newItemPosition});
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            BI originBi = originList.get(oldItemPosition);
            BI updateBi = updateList.get(newItemPosition);
            return BaseRecyclerActivity.this.areContentsTheSame(originBi, updateBi, new
                    Integer[]{oldItemPosition, newItemPosition});
        }

        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            BI originBi = originList.get(oldItemPosition);
            BI updateBi = updateList.get(newItemPosition);
            return BaseRecyclerActivity.this.getChangePayload(originBi, updateBi, new
                    Integer[]{oldItemPosition, newItemPosition});
        }
    }

    protected class GirdSpaceItemDecoration extends RecyclerView.ItemDecoration {
        int spec = 0;

        public GirdSpaceItemDecoration(int spec) {
            this.spec = DensityUtils.dip2px(BaseRecyclerActivity.this, spec);
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView
                .State state) {
            outRect.left = spec;
            outRect.bottom = spec;
            if (parent.getChildLayoutPosition(view) % 3 == 0) {
                outRect.left = 0;
            }
        }
    }
}
