package com.app.recall.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.recall.impl.OnRecyclerViewItemClickListener;
import com.bumptech.glide.Glide;

/**
 * Created by ChanZeeBm on 2015/9/19.
 * ViewHolder
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private View view;
    private SparseArray<View> sparseArray;
    private OnRecyclerViewItemClickListener listener;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.view = itemView;
        this.view.setOnClickListener(this);
        sparseArray = new SparseArray<>();
    }

    public void setText(int id, String text) {
        TextView textView = getViews(id);
        textView.setText(text);
    }

    public void setCheckedBoxBackground(int id, int res) {
        CheckBox box = getViews(id);
        box.setBackgroundResource(res);
    }

    public void setCheckBoxChecked(int id, boolean checked) {
        CheckBox box = getViews(id);
        box.setChecked(checked);
    }

    public void loadImageUrl(int id, String url) {
        ImageView imageView = getViews(id);
        Glide.with(view.getContext()).load(url).override(100, 100).into(imageView);
    }

    public <T extends View> T getViews(int id) {
        View v = sparseArray.get(id);
        if (v == null) {
            v = view.findViewById(id);
            sparseArray.put(id, v);
        }
        return (T) v;
    }

    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        Log.d("recall", "onClick() returned: " + (listener == null));
        if (listener != null) listener.onRecyclerViewItemClick(v, getAdapterPosition());
    }
}
