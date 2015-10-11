package com.fransson.leonard.oskar.learnrussiannow;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.fransson.leonard.oskar.learnrussiannow.MyClasses.LoadToSharedPreferences;
import com.fransson.leonard.oskar.learnrussiannow.MyClasses.SPHelper;
import com.fransson.leonard.oskar.learnrussiannow.sliding_tabs_google.SlidingTabLayout;
import com.fransson.leonard.oskar.learnrussiannow.util.MyDebugger;


public class MainActivity extends AppCompatActivity {


    private static final String SP_FIRST_TIME = "FIRST_TIME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = (ViewPager) findViewById(R.id.startViewPager);
        viewPager.setAdapter(new StartViewPagerAdapter(getSupportFragmentManager()));
        viewPager.setCurrentItem(1);//open app in the middle tab


        firstTimeInitialization();

        //Decrease Questions statisticsCorrectness since time makes you forget a question
        decreaseStatisticsCorrectness();

        //Give the viewPager to the slidingTabLayout
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.startSlidingTabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
    }

    private void decreaseStatisticsCorrectness() {

    }

    public void firstTimeInitialization(){
        //Load lession object to shared preferences.
        SharedPreferences sp = getSharedPreferences(SP_FIRST_TIME, 0);

        if (sp.getBoolean(SP_FIRST_TIME, true)) {

            if (MyDebugger.ON) {
                Log.i("MainActivity", "INSIDE FIRST TIME LOOP");
            }
            LoadToSharedPreferences.loadData(this);
//            SharedPreferences.Editor editor = sp.edit();
//            editor.putBoolean(SP_FIRST_TIME, false);
//            editor.apply();

        }
    }

    //Change to private here, was public before
    private static class StartViewPagerAdapter extends FragmentPagerAdapter {
        final int PAGES = 3;
        private String tabTitles[] = new String[]{"MODULES", "HOME", "LIB"};

        public StartViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    return new ModuleFragment();
                case 1:
                    return new HomeFragment();
                case 2:
                    return new PractiseFragment();
                default:
                    return new HomeFragment();
            }
        }

        @Override
        public int getCount() {
            return PAGES;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
