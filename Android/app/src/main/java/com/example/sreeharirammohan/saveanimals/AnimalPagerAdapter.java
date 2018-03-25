package com.example.sreeharirammohan.saveanimals;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by sreeharirammohan on 3/24/18.
 */

public class AnimalPagerAdapter extends FragmentPagerAdapter {

    public AnimalPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Map";
            case 1:
                return "Take Photo";
            default:
                return "View";
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                System.out.println("Creating map fragment");
                return new MapFragmentScreen();
            case 1:
                System.out.println("Creating take picture");
                return new Camera2BasicFragment();
            default:
                return new MapFragmentScreen();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }
}
