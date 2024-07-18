package cn.archko.test;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import cn.archko.test.custom.CircleLayoutManager;
import cn.archko.test.custom.CircleZoomLayoutManager;
import cn.archko.test.custom.GalleryLayoutManager;
import cn.archko.test.custom.ScrollZoomLayoutManager;
import cn.archko.test.item.IType;
import cn.archko.test.item.ItemAdapter;
import cn.archko.test.item.StringBean;
import cn.archko.test.item.StringBean2;
import cn.archko.test.item.TestAdapterItem;
import cn.archko.test.item.TestBean;
import cn.archko.test.item.TestItem;
import cn.archko.test.item.TestItem2;
import cn.archko.test.layoutmanager.CardLayoutManager;
import cn.archko.test.layoutmanager.CoverFlowLayoutManger;
import cn.archko.test.layoutmanager.CustomLayoutManager;
import cn.archko.test.layoutmanager.FlowLayoutManager;
import cn.archko.test.layoutmanager.HiveLayoutManager;
import cn.archko.test.layoutmanager.RollingLayoutManager;
import cn.archko.test.layoutmanager.SmartLayoutManager;
import cn.archko.test.layoutmanager.SuitedLayoutManager;
import cn.archko.test.layoutmanager.TestLayoutManager;

/**
 * @author: wushuyong 2018/12/27 :14:03
 */
public class TestLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        int layout = getIntent().getIntExtra("layout", 0);

        switch (layout) {
            case TestLayoutManagerActivity.LAYOUT_TEST: {
                recyclerView.setLayoutManager(new TestLayoutManager());
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_FLOW: {
                recyclerView.setLayoutManager(new FlowLayoutManager());
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_HIVE: {
                recyclerView.setLayoutManager(new HiveLayoutManager(this));
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_ROLLING: {
                recyclerView.setLayoutManager(new RollingLayoutManager(this));
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_SMART: {
                recyclerView.setLayoutManager(new SmartLayoutManager());
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_SUITED: {

                break;
            }
            case TestLayoutManagerActivity.LAYOUT_CUSTOM: {
                recyclerView.setLayoutManager(new CustomLayoutManager());
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_CIRCLE: {
                recyclerView.setLayoutManager(new CircleLayoutManager(this));
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_CIRCLE_ZOOM: {
                recyclerView.setLayoutManager(new CircleZoomLayoutManager(this));
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_GALLERY: {
                recyclerView.setLayoutManager(new GalleryLayoutManager(this, 20));
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_SCROLLZOOM: {
                recyclerView.setLayoutManager(new ScrollZoomLayoutManager(this, 20));
                break;
            }
            case TestLayoutManagerActivity.LAYOUT_CARD: {
                recyclerView.setLayoutManager(new CardLayoutManager(true));
                break;
            }
        }

        //new SmartSnapHelper().attachToRecyclerView(recyclerView);

        ItemAdapter<IType> adapter = new ItemAdapter<>(this);
        adapter.addAdapterItem(TestItem.TYPE, new TestItem(this));
        adapter.addAdapterItem(TestItem2.TYPE, new TestItem2(this));
        adapter.addAdapterItem(TestAdapterItem.TYPE, new TestAdapterItem(this));
        List<IType> list = new ArrayList<>();
        list.add(new StringBean("LAKDFALDKFAKLDF"));
        list.add(new StringBean("P0=2IP45M4TMASLDV"));
        list.add(new StringBean(".M,ZCV-00-79X89ZV756"));
        list.add(new StringBean("/.,1/5.,SDF"));
        list.add(new StringBean("1M,NKZXCJHVI"));
        list.add(new StringBean(",.AJDOU9ADF"));
        list.add(new StringBean("8913KLZDVKZ"));
        list.add(new StringBean(",.MLK1J90X8DV"));
        list.add(new StringBean("89Y19322KLAR"));
        list.add(new StringBean("KLAJDPFOIUA"));
        list.add(new StringBean("KLZCXOUF"));
        list.add(new StringBean("JK0afad09PZdfg"));
        list.add(new StringBean("JK09X34dfPZX sfg"));
        list.add(new StringBean("Jadfadf9XDPZXO"));
        list.add(new StringBean("1M,NKZXCJHVI"));
        list.add(new StringBean(",.AJDOU9ADF"));
        list.add(new StringBean("8913KLZDVKZ"));
        list.add(new StringBean(",.MLK1J90X8DV"));
        list.add(new StringBean2("2222222adf9X xcbvO"));
        list.add(new StringBean2("3333333Kvxczxv9PZXO"));
        list.add(new StringBean2("4444444JK09X8902435PZXO"));
        list.add(new StringBean2("5555555JK09XadfadfPZXO"));
        list.add(new StringBean2("6666666JK0sdfgh09PZXO"));
        list.add(new StringBean("JK0zxcvD09PZXO"));
        list.add(new StringBean("adfadJK09O"));
        list.add(new StringBean("Jzcvz9adf PZXO"));
        list.add(new StringBean("JK0adfadD09PZXO"));
        list.add(new StringBean("J adf9XD09adf"));
        list.add(new TestBean("4444444JK09XD09PZXO"));
        list.add(new TestBean("2222222adf9XD09xcbvO"));
        list.add(new TestBean("JK09X34dfPZXO"));
        list.add(new TestBean("89Y19322KLAR"));
        adapter.setData(list);
        recyclerView.setAdapter(adapter);
    }


}
