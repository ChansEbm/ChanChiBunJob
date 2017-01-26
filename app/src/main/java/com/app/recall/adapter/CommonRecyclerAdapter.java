package com.app.recall.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.app.recall.entity.item.BaseItem;
import com.app.recall.impl.OnRecyclerViewItemClickListener;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChanZeeBm on 2015/9/19.
 */
public abstract class CommonRecyclerAdapter<T extends BaseItem> extends RecyclerView
        .Adapter<RecyclerViewHolder> {
    private List<T> list;
    private int resId;
    private Context context;
    private OnRecyclerViewItemClickListener listener;

    protected CommonRecyclerAdapter(Context context, int resId, List<T> list) {
        if (list == null) {
            Logger.e("list is null!!!!!");
            list = new ArrayList<>();
        }
        this.list = null;
        this.list = new ArrayList<>(list);
        this.resId = resId;
        this.context = context;
    }

    public List<T> getCurrentData() {
        return list;
    }

    public void updateList(List<T> list) {
        this.list.clear();
        this.list.addAll(list);
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(resId, viewGroup,
                false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        recyclerViewHolder.setListener(listener);
        onBind(recyclerViewHolder, position, list.get(position), null);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position, List<Object> payloads) {
        if (payloads.isEmpty()) onBindViewHolder(holder, position);
        else onBind(holder, position, list.get(position), null);
        holder.setListener(listener);
    }

    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public abstract void onBind(RecyclerViewHolder recyclerViewHolder, int position, T t, Bundle
            bundle);


    @Override
    public int getItemCount() {
        return list.size();
    }
}
