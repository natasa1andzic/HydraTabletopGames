package com.natasaandzic.hydratabletopgames.activities;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.natasaandzic.hydratabletopgames.R;
import com.natasaandzic.hydratabletopgames.adapters.MyFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {
	/**
	 * Za rad sa fragmentima, potreban nam je TabLayout, ViewPager i FragmentPagerAdapter
	 */
	private TabLayout tabLayout;
	private ViewPager viewPager;
	private MyFragmentPagerAdapter adapter;

	private Button eventsBtn;
	private Button gamesBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		eventsBtn = (Button) findViewById(R.id.eventsBtn);
		gamesBtn = (Button) findViewById(R.id.gamesBtn);

		eventsBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this,CalendarActivity.class);
				startActivity(i);
			}
		});

		gamesBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, GamesActivity.class);
				startActivity(i);
			}
		});




		//Ako koristimo fragmente
		/*tabLayout = (TabLayout) findViewById(R.id.tab_layout);
		tabLayout.addTab(tabLayout.newTab().setText("Calendar"));
		tabLayout.addTab(tabLayout.newTab().setText("Games"));
		tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

		viewPager = (ViewPager) findViewById(R.id.pager);
		adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
		viewPager.setAdapter(adapter);

		tabLayout.setupWithViewPager(viewPager); // this will automatically bind tab clicks to viewpager fragments
*/
	}

}


