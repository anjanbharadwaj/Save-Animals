package com.example.sreeharirammohan.saveanimals;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TakePicture.OnFragmentInteractionListener, MapFragmentScreen.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        PagerAdapter adapter = new AnimalPagerAdapter(getSupportFragmentManager());
       viewPager.setAdapter(adapter);
       tabLayout.setupWithViewPager(viewPager);



    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

