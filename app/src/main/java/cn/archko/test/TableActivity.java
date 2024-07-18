package cn.archko.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import cn.archko.test.layoutmanager.TableItemDecoration;
import cn.archko.test.layoutmanager.TableLayoutManager;

public class TableActivity extends AppCompatActivity {

    public static void startUp(Context context) {
        context.startActivity(new Intent(context, TableActivity.class));
    }

    public static final int COLUMN_COUNT = 10;

    private Context mContext;
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initEvent();
        initData();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        TableLayoutManager layoutManager = new TableLayoutManager.Build(mContext).setColumnCount(COLUMN_COUNT)
                .setFixColumnCount(1)
                .setFixHeader(true)
                .setHeadHeight(DensityUtils.dip2px(mContext, 32))
                .setRowHeight(DensityUtils.dip2px(mContext, 48))
                .setWidgetCount(3)
                .build();
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addItemDecoration(new TableItemDecoration(mContext));
    }

    private void initEvent() {

    }

    private void initData() {
        BaseRecyclerAdapter<String> adapter = new BaseRecyclerAdapter<String>(this) {
            @NonNull
            @Override
            public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ItemViewHolder(mInflater.inflate(R.layout.test_table_item, parent, false));
            }
        };
        adapter.setData(obtainDataList());
        mRecyclerView.setAdapter(adapter);
    }

    private List<String> obtainDataList() {
        List<String> dataList = new ArrayList<>();
        for (int column = 0; column < COLUMN_COUNT; column++) {
            if (column == 0) {
                dataList.add("身高/体重");
            } else {
                dataList.add(String.valueOf(152 + (column - 1) * 4) + "cm");
            }
        }
        for (int row = 0; row < 60; row++) {
            for (int column = 0; column < COLUMN_COUNT; column++) {
                if (column == 0) {
                    dataList.add(String.valueOf(19 + row) + "岁");
                } else {
                    dataList.add(String.valueOf(50 + row + (column - 1) * 2));
                }
            }
        }
        return dataList;
    }

    public interface OnItemClickListener {

        void onItemClick(int position);
    }

    static class ItemViewHolder extends BaseViewHolder<String> {

        TextView mTextItem;

        ItemViewHolder(View itemView) {
            super(itemView);
            mTextItem = itemView.findViewById(R.id.text_content_item);
        }

        @Override
        public void onBind(String data, int position) {
            mTextItem.setText(data);
            if (position / TableActivity.COLUMN_COUNT % 2 == 0) {
                mTextItem.setBackgroundColor(R.color.colorAccent);
            } else {
                mTextItem.setBackgroundColor(R.color.colorPrimary);
            }
            mTextItem.setTag(position);
        }
    }
}