package cn.archko.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.archko.test.custom.CircleZoomLayoutManager;
import cn.archko.test.custom.GalleryLayoutManager;
import cn.archko.test.custom.ScrollZoomLayoutManager;
import cn.archko.test.layoutmanager.CardLayoutManager;
import cn.archko.test.layoutmanager.CustomLayoutManager;
import cn.archko.test.layoutmanager.FlowLayoutManager;
import cn.archko.test.layoutmanager.HiveLayoutManager;
import cn.archko.test.layoutmanager.RollingLayoutManager;
import cn.archko.test.layoutmanager.SmartLayoutManager;
import cn.archko.test.layoutmanager.TableLayoutManager;
import cn.archko.test.layoutmanager.TestLayoutManager;
import cn.archko.test.layoutmanager.ZoomRecyclerLayout;
import cn.archko.test.series.LinearLayoutManager1;
import cn.archko.test.series.LinearLayoutManager2;
import cn.archko.test.series.LinearLayoutManager3;
import cn.archko.test.series.LinearLayoutManager4;
import cn.archko.test.series.LinearLayoutManager5;
import cn.archko.test.series.LinearLayoutManager6;
import cn.archko.test.series.LinearLayoutManager7;
import cn.archko.test.series.MostSimpleLayoutManager;
import cn.archko.test.series.MyLinearLayoutManager;

/**
 * @author: wushuyong 2018/12/27 :14:03
 */
public class TestLayoutManagerActivity extends AppCompatActivity {

    public static final int LAYOUT_TEST = 0;
    public static final int LAYOUT_FLOW = 1;
    public static final int LAYOUT_HIVE = 2;
    public static final int LAYOUT_ROLLING = 3;
    public static final int LAYOUT_SMART = 4;
    public static final int LAYOUT_SUITED = 5;
    public static final int LAYOUT_CUSTOM = 6;
    public static final int LAYOUT_CUSTOM2 = 7;
    public static final int LAYOUT_CIRCLE = 8;
    public static final int LAYOUT_CIRCLE_ZOOM = 9;
    public static final int LAYOUT_GALLERY = 10;
    public static final int LAYOUT_SCROLLZOOM = 11;
    public static final int LAYOUT_CARD = 12;
    public static final int LAYOUT_TABLE = 13;
    public static final int LAYOUT_1 = 14;
    public static final int LAYOUT_2 = 15;
    public static final int LAYOUT_3 = 16;
    public static final int LAYOUT_4 = 17;
    public static final int LAYOUT_5 = 18;
    public static final int LAYOUT_6 = 19;
    public static final int LAYOUT_7 = 20;
    public static final int LAYOUT_SIMPLE = 21;
    public static final int LAYOUT_MY = 22;
    public static final int LAYOUT_ZOOM = 23;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layoutmanager);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        BaseRecyclerAdapter<Bean> adapter = new BaseRecyclerAdapter<Bean>(this) {
            @NonNull
            @Override
            public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = mInflater.inflate(R.layout.viewholder, parent, false);
                ViewHolder viewHolder = new ViewHolder(view);
                return viewHolder;
            }
        };
        recyclerView.setAdapter(adapter);

        List<Bean> list = new ArrayList<>();
        Bean bean = new Bean(LAYOUT_TEST, TestLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_FLOW, FlowLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_HIVE, HiveLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_ROLLING, RollingLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_SMART, SmartLayoutManager.class.getSimpleName());
        list.add(bean);
        //bean = new Bean(LAYOUT_SUITED, SuitedLayoutManager.class.getSimpleName());
        //list.add(bean);
        bean = new Bean(LAYOUT_CUSTOM, CustomLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_CIRCLE_ZOOM, CircleZoomLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_GALLERY, GalleryLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_CIRCLE_ZOOM, ScrollZoomLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_CARD, CardLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_1, LinearLayoutManager1.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_2, LinearLayoutManager2.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_3, LinearLayoutManager3.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_4, LinearLayoutManager4.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_5, LinearLayoutManager5.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_6, LinearLayoutManager6.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_7, LinearLayoutManager7.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_SIMPLE, MostSimpleLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_MY, MyLinearLayoutManager.class.getSimpleName());
        list.add(bean);
        bean = new Bean(LAYOUT_ZOOM, ZoomRecyclerLayout.class.getSimpleName());
        list.add(bean);

        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }

    class Bean {
        int layout;
        String name;

        public Bean(int layout, String name) {
            this.layout = layout;
            this.name = name;
        }
    }

    class ViewHolder extends BaseViewHolder<Bean> {

        private final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }

        @Override
        public void onBind(Bean data, int position) {
            textView.setText(data.name);
            textView.setOnClickListener((view) -> {
                if (data.layout == LAYOUT_TABLE) {
                    TableActivity.startUp(TestLayoutManagerActivity.this);
                    return;
                }
                Intent intent = new Intent(TestLayoutManagerActivity.this, TestLayoutActivity.class);
                intent.putExtra("layout", data.layout);
                startActivity(intent);
            });
        }
    }


}
