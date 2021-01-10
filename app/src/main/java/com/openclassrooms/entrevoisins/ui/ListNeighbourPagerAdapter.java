package com.openclassrooms.entrevoisins.ui;

import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.openclassrooms.entrevoisins.Fragments.FavoriteNeighboursFragment;
import com.openclassrooms.entrevoisins.Fragments.NeighbourFragment;

public class ListNeighbourPagerAdapter extends FragmentStatePagerAdapter {
    int numberOfTabs;

    public ListNeighbourPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.numberOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return NeighbourFragment.newInstance();

            case 1:
                return FavoriteNeighboursFragment.newInstance();

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

}