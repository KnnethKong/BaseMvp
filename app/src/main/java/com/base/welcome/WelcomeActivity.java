package com.base.welcome;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.base.mvp.R;

/**
 * Created by KXF on 2018/5/20.
 */

public class WelcomeActivity extends FragmentActivity {

    private ViewPager vp;
    private int[] layouts = {
            R.layout.vp_one,
            R.layout.vp_two,
            R.layout.vp_three};
    private WelcomePagerTransformer transformer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_layout);
        vp = (ViewPager) findViewById(R.id.welcome_vp);
        WelcomePagerAdapter adapter = new WelcomePagerAdapter(getSupportFragmentManager());
        System.out.println("offset:" + vp.getOffscreenPageLimit());
        vp.setOffscreenPageLimit(3);
        vp.setAdapter(adapter);

        transformer = new WelcomePagerTransformer();
        vp.setPageTransformer(true, transformer);
        vp.setOnPageChangeListener(transformer);
    }

    class WelcomePagerAdapter extends FragmentPagerAdapter {

        public WelcomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment f = new TranslateFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("layoutId", layouts[position]);
            bundle.putInt("pageIndex", position);
            f.setArguments(bundle);
            return f;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }


    }
}
