package com.natasaandzic.hydratabletopgames.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


import com.natasaandzic.hydratabletopgames.R;
import com.natasaandzic.hydratabletopgames.adapters.MyArrayAdapter;
import com.natasaandzic.hydratabletopgames.model.InternetConnection;
import com.natasaandzic.hydratabletopgames.model.Keys;
import com.natasaandzic.hydratabletopgames.model.MyDataModel;
import com.natasaandzic.hydratabletopgames.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {

	private ListView listView;
	private ArrayList<MyDataModel> list;
	private MyArrayAdapter adapter;
	private Toast toastMsg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		list = new ArrayList<>();
		adapter = new MyArrayAdapter(this, list);
		listView = (ListView) findViewById(R.id.listView);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				makeDialog(position);
			}
		});


		if (InternetConnection.checkConnection(getApplicationContext()))
			new GetDataTask().execute();
		else
			toastMsg.makeText(getApplicationContext(), "Internet connection is not available", Toast.LENGTH_LONG).show();
	}


	class GetDataTask extends AsyncTask<Void, Void, Void> {

		ProgressDialog dialog;
		int jIndex;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			jIndex=list.size();

			dialog = new ProgressDialog(CalendarActivity.this);
			dialog.setTitle("Reading from database...");
			dialog.setMessage("Go make some coffee ^_^");
			dialog.show();
		}

		@Nullable
		@Override
		protected Void doInBackground(Void... params) {
			JSONObject jsonObject = JSONParser.getDataFromWeb();
			try {
				if (jsonObject != null) {
					if(jsonObject.length() > 0) {
						JSONArray array = jsonObject.getJSONArray(Keys.KEY_EVENTS);
						int lenArray = array.length();
						if(lenArray > 0) {
							for( ; jIndex < lenArray; jIndex++) {

								MyDataModel model = new MyDataModel();
								JSONObject innerObject = array.getJSONObject(jIndex);
								String eventName = innerObject.getString(Keys.KEY_EVENTNAME);
								String eventDate = innerObject.getString(Keys.KEY_EVENTDATE);
								String eventTime = innerObject.getString(Keys.KEY_EVENTTIME);
								String eventDay = innerObject.getString(Keys.KEY_EVENTDAY);
								String eventDescription = innerObject.getString(Keys.KEY_EVENTDESCRIPTION);

								model.setEventName(eventName);
								model.setEventDate(eventDate);
								model.setEventTime(eventTime);
								model.setEventDay(eventDay);
								model.setEventDescription(eventDescription);

								list.add(model);
							}
						}
					}
				}
			} catch (JSONException je) {
				Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			dialog.dismiss();
			if(list.size() > 0) {
				adapter.notifyDataSetChanged();
			} else {
				Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
			}
		}
	}

	private void makeDialog(int position) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(CalendarActivity.this);
		alertDialogBuilder.setTitle(list.get(position).getEventName());
		alertDialogBuilder.setMessage(list.get(position).getEventDescription());

		alertDialogBuilder.setPositiveButton("Notify me!", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				//Upisi korisnika u Firebase Cloud Messaging bazu
			}});

		alertDialogBuilder.setNegativeButton("Go back", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				//nazad
			}
		});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}