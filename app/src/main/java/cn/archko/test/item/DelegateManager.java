package cn.archko.test.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.archko.test.BaseViewHolder;
import cn.archko.test.item.AdapterItem;
import cn.archko.test.item.IType;

/**
 * @author: wushuyong 2018/12/17 :15:44
 */
public class DelegateManager {

    private Map<Integer, AdapterItem> viewTypeMap = new HashMap<>();

    public DelegateManager() {
    }

    public void addAdapterItem(int viewType, AdapterItem adapterItem) {
        AdapterItem old = viewTypeMap.get(viewType);
        if (null != old) {
            System.err.println("alread has the same type.");
        }
        viewTypeMap.put(viewType, adapterItem);
    }

    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterItem adapterItem = viewTypeMap.get(viewType);
        return adapterItem.onCreateViewHolder(parent, viewType);
    }

    public <T extends IType> int getItemViewType(List<T> data, int position) {
        IType itemBean = data.get(position);
        int vt = itemBean.getViewType();
        AdapterItem adapterItem = viewTypeMap.get(vt);
        if (null != adapterItem) {
            return vt;
        }
        return 0;
    }


    public <T extends IType> void onBindViewHolder(List<T> data, BaseViewHolder viewHolder, int position) {
        AdapterItem adapterItem = viewTypeMap.get(viewHolder.getItemViewType());
        adapterItem.onBind(viewHolder, data.get(position), position);
    }

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
