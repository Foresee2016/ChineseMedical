package org.foresee.medicine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] titles = new String[]{"理论", "中药", "脉搏", "穴位"};
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private FragmentAdapter adapter;
    //ViewPage选项卡页面集合
    private List<Fragment> mFragments;
    //Tab标题集合
    private List<String> mTitles;
    /**
     * 图片数组
     */
    private int[] mImgs=new int[]{R.drawable.nav_theory, R.drawable.nav_herb, R.drawable.nav_pulse, R.drawable.nav_acupoint};
    private int[] mImgsActive =new int[]{R.drawable.nav_theory_a, R.drawable.nav_herb_a, R.drawable.nav_pulse_a, R.drawable.nav_acupoint_a};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);

        mTitles = new ArrayList<>();
        mTitles.addAll(Arrays.asList(titles));

        mFragments = new ArrayList<>();
        for (int i = 0; i < mTitles.size(); i++) {
            mFragments.add(TabFragment.newInstance(i));
        }
        adapter = new FragmentAdapter(getSupportFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(adapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来

        mTabLayout.setSelectedTabIndicatorHeight(0);
        for (int i = 0; i < mTitles.size(); i++) {
            //获得到对应位置的Tab
            TabLayout.Tab itemTab = mTabLayout.getTabAt(i);
            if (itemTab != null) {
                //设置自定义的标题
                itemTab.setCustomView(R.layout.item_tab);
                TextView textView = (TextView) itemTab.getCustomView().findViewById(R.id.tv_name);
                textView.setText(mTitles.get(i));
                ImageView imageView= (ImageView) itemTab.getCustomView().findViewById(R.id.iv_img);
                imageView.setImageResource(mImgs[i]);
            }
        }
        ImageView firstTabImg = mTabLayout.getTabAt(0).getCustomView().findViewById(R.id.iv_img);
        firstTabImg.setImageResource(mImgsActive[0]); // 设置第一个Tab图标为选中状态
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                ImageView imageView=tab.getCustomView().findViewById(R.id.iv_img);
                imageView.setImageResource(mImgsActive[tab.getPosition()]);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView imageView=tab.getCustomView().findViewById(R.id.iv_img);
                imageView.setImageResource(mImgs[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mTabLayout.getTabAt(0).getCustomView().setSelected(true);
    }

}
