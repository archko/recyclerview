package cn.archko.test.item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import cn.archko.test.BaseViewHolder;

/**
 * @author: wushuyong 2019/5/30 :9:58
 */
public abstract class AdapterItem {

    LayoutInflater inflater;

    public AdapterItem(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public abstract BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

    public abstract <T extends IType> void onBind(BaseViewHolder viewHolder, IType tItemBean, int position);

    public void onViewRecycled(@NonNull BaseViewHolder holder) {

    }

    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {

    }

    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {

    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

    }

    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
    }
}
