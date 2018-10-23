package com.natasaandzic.hydratabletopgames.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import android.widget.Toast;

import com.natasaandzic.hydratabletopgames.R;
import com.natasaandzic.hydratabletopgames.adapters.GamesAdapter;
import com.natasaandzic.hydratabletopgames.model.GamesDataModel;
import com.natasaandzic.hydratabletopgames.model.InternetConnection;
import com.natasaandzic.hydratabletopgames.model.Keys;
import com.natasaandzic.hydratabletopgames.parser.JSONParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GamesActivity extends AppCompatActivity {

	private RecyclerView recyclerView;
	private ArrayList<GamesDataModel> list;
	private GamesAdapter adapter;
	private Toast toastMsg;

	private LinearLayoutManager lm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_games);

		recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
		recyclerView.setHasFixedSize(true);
		lm = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(lm);

		DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), lm.getOrientation());
		recyclerView.addItemDecoration(mDividerItemDecoration);

		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		list = new ArrayList<>();
		adapter = new GamesAdapter(list);

		recyclerView.setAdapter(adapter);
		/*recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				makeDialog(position);
			}
		});*/


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

			dialog = new ProgressDialog(GamesActivity.this);
			dialog.setTitle("Reading from database...");
			dialog.setMessage("Gamessss ^_^");
			dialog.show();
		}

		@Nullable
		@Override
		protected Void doInBackground(Void... params) {
			JSONObject jsonObject = JSONParser.getGamesDataFromWeb();
			try {
				if (jsonObject != null) {
					if(jsonObject.length() > 0) {
						JSONArray array = jsonObject.getJSONArray(Keys.KEY_GAMES);
						int lenArray = array.length();
						if(lenArray > 0) {
							for( ; jIndex < lenArray; jIndex++) {

								GamesDataModel model = new GamesDataModel();
								JSONObject innerObject = array.getJSONObject(jIndex);
								String gameName = innerObject.getString(Keys.KEY_GAMENAME);
								String gamePrice = innerObject.getString(Keys.KEY_GAMEPRICE);
								String gameDescription = innerObject.getString(Keys.KEY_GAMEDESCRIPTION);
								String gameGenre = innerObject.getString(Keys.KEY_GAMEGENRE);

								model.setGameName(gameName);
								model.setGamePrice(gamePrice);
								model.setGameDescription(gameDescription);
								model.setGameGenre(gameGenre);

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
				Log.i("List size", Integer.toString(list.size()));
				adapter.notifyDataSetChanged();
			} else {
				Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
			}
		}
	}

	private void makeDialog(int position) {

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GamesActivity.this);
		alertDialogBuilder.setTitle(list.get(position).getGameName());
		alertDialogBuilder.setMessage(list.get(position).getGameDescription());

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
