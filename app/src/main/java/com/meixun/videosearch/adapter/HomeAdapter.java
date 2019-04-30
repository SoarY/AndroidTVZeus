package com.meixun.videosearch.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.meixun.videosearch.R;
import com.meixun.videosearch.base.BaseAdapter;
import com.meixun.videosearch.bean.AndroidBean;
import com.meixun.videosearch.constant.ItemStyle;
import com.meixun.videosearch.databinding.ItemTypeAdapterBinding;
import com.meixun.videosearch.databinding.ItemTypeTextBinding;
import com.meixun.videosearch.event.eventbus.AmongScrollEvent;
import com.meixun.videosearch.view.FocusStableLinearLayoutManager;
import com.meixun.videosearch.view.LinearItemDecoration;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


/**
 * NAME：YONG_
 * Created at: 2019/1/7
 * Describe:
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<List<AndroidBean>> datas;
    private BaseAdapter.OnItemClickListener onChildItemClickListener;

    public void setData(List<List<AndroidBean>> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public List<AndroidBean> getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return datas.get(position).get(0).style_type;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case ItemStyle.Style.TEXT:
                return new TitleHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_type_text, parent, false));
            case ItemStyle.Style.ADAPTER:
                return new AdapterHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_type_adapter, parent, false));
            default:
                return new AdapterHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_type_adapter, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case ItemStyle.Style.TEXT:
                bindTitleHolder((TitleHolder) holder, position);
                break;
            case ItemStyle.Style.ADAPTER:
                bindAdapterHolder((AdapterHolder) holder, position);
                break;
        }
    }

    private void bindTitleHolder(TitleHolder holder, int position) {
        String title = getItem(position).get(0).getDesc();
        holder.binding.tvItemText.setText(title);
        holder.binding.executePendingBindings();
    }

    private void bindAdapterHolder(AdapterHolder holder, int position) {
        List<AndroidBean> item = getItem(position);
        if (!TextUtils.isEmpty(item.get(0).getType_title())) {
            holder.binding.tvItemTitle.setText(item.get(0).getType_title());
            holder.binding.tvItemTitle.setVisibility(View.VISIBLE);
        }
        holder.binding.recyclerView.setLayoutManager(new FocusStableLinearLayoutManager(holder.binding.getRoot().getContext(), LinearLayoutManager.HORIZONTAL, false));
        holder.binding.recyclerView.addItemDecoration(new LinearItemDecoration((int) holder.binding.getRoot().getContext().getResources().getDimension(R.dimen.dp_30)));
        CommonAdapter commonAdapter = new CommonAdapter();
        commonAdapter.setData(item);
        commonAdapter.setPosition(position);
        commonAdapter.setTotalCount(getItemCount());
        commonAdapter.setOnItemClickListener(onChildItemClickListener);
        holder.binding.recyclerView.setAdapter(commonAdapter);
        holder.itemView.setOnFocusChangeListener((v, hasFocus) -> EventBus.getDefault().post(new AmongScrollEvent(v, hasFocus)));

        holder.binding.executePendingBindings();
    }

    public void setOnChildItemClickListener(BaseAdapter.OnItemClickListener onChildItemClickListener) {
        this.onChildItemClickListener = onChildItemClickListener;
    }

    public class TitleHolder extends RecyclerView.ViewHolder {
        public ItemTypeTextBinding binding;

        public TitleHolder(ItemTypeTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public class AdapterHolder extends RecyclerView.ViewHolder {
        public ItemTypeAdapterBinding binding;

        public AdapterHolder(ItemTypeAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
