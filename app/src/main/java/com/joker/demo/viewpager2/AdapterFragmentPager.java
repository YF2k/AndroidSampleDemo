package com.joker.demo.viewpager2;

import android.util.Log;
import android.util.SparseArray;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class AdapterFragmentPager extends FragmentStateAdapter {
    List<Fragment> fragments;

    public AdapterFragmentPager(@NonNull FragmentActivity fragmentActivity,List<Fragment> fragments) {
        super(fragmentActivity);
        this.fragments=fragments;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.e("xiang","0000000000");
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
