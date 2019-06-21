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
public class TestItem2 extends AdapterItem {

    public final static int TYPE = 2;

    public TestItem2(Context context) {
        super(context);
    }

    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder2(inflater.inflate(R.layout.viewholder, parent, false));
    }

    public <T extends IType> void onBind(BaseViewHolder viewHolder, IType tItemBean, int position) {
        viewHolder.onBind(tItemBean, position);
    }

    class ViewHolder2 extends BaseViewHolder<StringBean2> {

        TextView text;

        public ViewHolder2(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            text.setTextColor(text.getContext().getResources().getColor(R.color.colorAccent));
        }

        public void onBind(StringBean2 data, int position) {
            text.setText(data.str);
        }
    }
}
