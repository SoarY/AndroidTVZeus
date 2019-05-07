package com.soar.zeus.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentPagerAdapter  在销毁Fragment时只是销毁了View视图，实例依然保存在FragmentManager中(适合少量Fragment时使用,避免频繁创建)
 *
 * FragmentStatePagerAdapter 在销毁Fragment时 视图以及实例都将被销毁(适合很多Fragment时使用,及时释放省内存)
 */
public class ContentPagerAdapter extends FragmentPagerAdapter {

    private final List<String> mTitleList = new ArrayList<>();
    private final List<Fragment> mFragmentList = new ArrayList<>();

    public ContentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(String title, Fragment fragment) {
        mTitleList.add(title);
        mFragmentList.add(fragment);
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }
}