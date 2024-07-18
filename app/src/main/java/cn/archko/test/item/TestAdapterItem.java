package cn.archko.test.item;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import cn.archko.test.BaseViewHolder;
import cn.archko.test.R;
import cn.archko.test.TestViewHolder;

/**
 * @author: wushuyong 2019/5/30 :11:14
 */
public class TestAdapterItem extends AdapterItem {

    public final static int TYPE = 4;

    public TestAdapterItem(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new TestViewHolder(inflater.inflate(R.layout.test_viewholder, parent, false));
    }

    @Override
    public <T extends IType> void onBind(BaseViewHolder viewHolder, IType tItemBean, int position) {
        viewHolder.onBind(tItemBean, position);
    }
}
