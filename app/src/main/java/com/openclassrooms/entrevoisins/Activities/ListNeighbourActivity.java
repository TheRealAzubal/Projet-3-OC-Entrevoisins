package com.openclassrooms.entrevoisins.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteFavoriteNeighbourEvent;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;
import com.openclassrooms.entrevoisins.ui.ListNeighbourPagerAdapter;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListNeighbourActivity extends AppCompatActivity {

    // UI Components
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container)
    ViewPager mViewPager;

    ListNeighbourPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_neighbour);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);


        mPagerAdapter = new ListNeighbourPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tabs) {
                mViewPager.setCurrentItem(tabs.getPosition());


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tabs) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tabs) {
            }
        });



    }


}
