package com.natasaandzic.hydratabletopgames.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.natasaandzic.hydratabletopgames.R;

public class SplashActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		Intent i = new Intent(SplashActivity.this, MainActivity.class);
		startActivity(i);
		finish();
	}
}
