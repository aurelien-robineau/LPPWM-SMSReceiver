package com.lppwm.smsreceiver.ui;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.lppwm.smsreceiver.MainActivity;
import com.lppwm.smsreceiver.R;
import com.lppwm.smsreceiver.ui.tabs.*;

import org.jetbrains.annotations.NotNull;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private final MainActivity mActivity;

    public SectionsPagerAdapter(Context context, MainActivity activity, FragmentManager fm) {
        super(fm);
        mContext = context;
        mActivity = activity;
    }


    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return SMSTab.getInstance();
            case 1:
                return StatsTab.getInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }
}