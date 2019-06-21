package cn.archko.test.item;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.archko.test.BaseViewHolder;
import cn.archko.test.R;

/**
 * @author: wushuyong 2019/5/30 :13:48
 */
public class TestItem extends AdapterItem {

    public final static int TYPE = 1;

    public TestItem(Context context) {
        super(context);
    }

    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(inflater.inflate(R.layout.viewholder, parent, false));
    }

    public <T extends IType> void onBind(BaseViewHolder viewHolder, IType tItemBean, int position) {
        viewHolder.onBind(tItemBean, position);
    }

    class ViewHolder extends BaseViewHolder<StringBean> {

        TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
        }

        public void onBind(StringBean data, int position) {
            text.setText(data.str);
        }
    }
}