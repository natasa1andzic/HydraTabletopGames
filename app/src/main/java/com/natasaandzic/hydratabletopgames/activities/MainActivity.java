package com.natasaandzic.hydratabletopgames.activities;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.natasaandzic.hydratabletopgames.R;
import com.natasaandzic.hydratabletopgames.adapters.MyFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.addTab(tabLayout.newTab().setText("Calendar"));
		tabLayout.addTab(tabLayout.newTab().setText("Games"));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
		MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager); // this will automatically bind tab clicks to viewpager fragments

	}

}


