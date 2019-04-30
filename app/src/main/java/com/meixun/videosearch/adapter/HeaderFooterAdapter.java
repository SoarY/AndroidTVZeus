package com.meixun.videosearch.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * NAME：YONG_
 * Created at: 2019/1/14
 * Describe: 装饰者模式的头尾Adapter
 */
public class HeaderFooterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //Type
    private int TYPE_HEADER = 1000;
    private int TYPE_FOOTER = 1002;
    //    private int TYPE_NORMAL = 1001;

    private View mHeaderView;
    private View mFooterView;

    private RecyclerView.Adapter adapter;

    public HeaderFooterAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
        this.adapter.registerAdapterDataObserver(mDataObserver);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_FOOTER)
            return new HeadFootHolder(mFooterView);
        else if (viewType == TYPE_HEADER)
            return new HeadFootHolder(mHeaderView);
        else
            return adapter.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == TYPE_HEADER) {
            //TEXT
        } else if (viewType == TYPE_FOOTER) {
            //TEXT
        } else {
            adapter.onBindViewHolder(holder, getRealPosition(position));
        }
    }

    public int getRealPosition(int position) {
        int mPosition = position;
        if (mHeaderView != null)
            mPosition--;
        return mPosition;
    }

    @Override
    public int getItemCount() {
        int count = adapter.getItemCount();
        if (mHeaderView != null)
            count++;
        if (mFooterView != null)
            count++;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        if (isHeaderView(position))
            return TYPE_HEADER;
        else if (isFooterView(position))
            return TYPE_FOOTER;
        else
            return adapter.getItemViewType(getRealPosition(position));
    }

    private boolean isHeaderView(int position) {
        return mHeaderView != null && position == 0;
    }

    private boolean isFooterView(int position) {
        return mFooterView != null && position == getItemCount() - 1;
    }

    public void addHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    public void addFooterView(View footerView) {
        mFooterView = footerView;
        notifyItemInserted(getItemCount() - 1);
    }

    public class HeadFootHolder extends RecyclerView.ViewHolder {

        public HeadFootHolder(View view) {
            super(view);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager)
            ((GridLayoutManager) layoutManager).setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return (isHeaderView(position) || isFooterView(position)) ? ((GridLayoutManager) layoutManager).getSpanCount() : 1;
                }
            });
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp instanceof StaggeredGridLayoutManager.LayoutParams
                && (isHeaderView(holder.getLayoutPosition()) || isFooterView(holder.getLayoutPosition())))
            ((StaggeredGridLayoutManager.LayoutParams) lp).setFullSpan(true);
    }

    private final RecyclerView.AdapterDataObserver mDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            notifyDataSetChanged();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            notifyItemMoved(fromPosition, toPosition);
        }
    };
}