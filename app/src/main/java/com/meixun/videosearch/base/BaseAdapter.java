package com.meixun.videosearch.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * NAME：YONG_
 * Created at: 2018/12/24 13
 * Describe:
 */
public abstract class BaseAdapter<T, B extends ViewDataBinding> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    public List<T> datas;
    private OnItemClickListener onItemClickListener;

    protected abstract int initLayoutId();

    protected abstract void onBindView(RecyclerHolder<B> holder, T t, int position);

    public void setData(List<T> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public T getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        B binding = DataBindingUtil.inflate(LayoutInflater.from(context = parent.getContext()), initLayoutId(), parent, false);
        if (binding == null)
            throw new RuntimeException("DataBinding Root layout must by <layout/>");
        return new RecyclerHolder<B>(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RecyclerHolder<B> bindHolder = (RecyclerHolder<B>) holder;
        onBindView(bindHolder, getItem(position), position);
        if (onItemClickListener != null)
            holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(position));
        bindHolder.binding.executePendingBindings();
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public class RecyclerHolder<BD extends ViewDataBinding> extends RecyclerView.ViewHolder {

        public BD binding;

        public RecyclerHolder(BD binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
