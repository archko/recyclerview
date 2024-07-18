package cn.archko.test.item;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import cn.archko.test.BaseRecyclerAdapter;
import cn.archko.test.BaseViewHolder;

/**
 * @author: wushuyong 2018/12/17 :15:44
 */
public class ItemAdapter<T extends IType> extends BaseRecyclerAdapter<T> {

    private DelegateManager delegateManager;

    public ItemAdapter(Context context) {
        this(context, null);
    }

    public ItemAdapter(Context context, List<T> data) {
        super(context, data);
        delegateManager = new DelegateManager();
    }

    public void addAdapterItem(int viewType, AdapterItem clazz) {
        delegateManager.addAdapterItem(viewType, clazz);
    }

    @Override
    public int getItemViewType(int position) {
        return delegateManager.getItemViewType(getData(), position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return delegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder viewHolder, int position) {
        delegateManager.onBindViewHolder(getData(), viewHolder, position);
    }

    @Override
    public void onViewRecycled(@NonNull BaseViewHolder holder) {
        super.onViewRecycled(holder);
        delegateManager.onViewRecycled(holder);
    }

    @Override
    public void onViewAttachedToWindow(@NonNull BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        delegateManager.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull BaseViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        delegateManager.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        delegateManager.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        delegateManager.onDetachedFromRecyclerView(recyclerView);
    }
}
