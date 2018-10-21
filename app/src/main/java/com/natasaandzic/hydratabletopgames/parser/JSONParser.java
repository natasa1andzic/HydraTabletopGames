package com.natasaandzic.hydratabletopgames.parser;

import android.support.annotation.NonNull;
import android.util.Log;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class JSONParser {

	private static final String MAIN_URL = "https://script.googleusercontent.com/macros/echo?user_content_key=JWCo4tZnq20qKRdvQ2jJE6qTghfiO0pZqmSwLUcnA-OTjNDvTNz71VaEszWWWzA2UX8JtvX_Z-CR600fCREt7AKFqRTvsn13OJmA1Yb3SEsKFZqtv3DaNYcMrmhZHmUMWojr9NvTBuBLhyHCd5hHa1GhPSVukpSQTydEwAEXFXgt_wltjJcH3XHUaaPC1fv5o9XyvOto09QuWI89K6KjOu0SP2F-BdwUIZa56hM3G_iu58eX_8Kdkiovyis5xZs8r3sQH5mkjunC1S3Gg8qT4lL99vHHnDc05y7FLqOV0Tk27B8Rh4QJTQ&lib=MnrE7b2I2PjfH799VodkCPiQjIVyBAxva";
	public static final String TAG = "TAG";

	private static final String KEY_USER_ID = "user_id";

	private static Response response;

	public static JSONObject getDataFromWeb() {
		try {
			OkHttpClient client = new OkHttpClient();
			Request request = new Request.Builder()
					.url(MAIN_URL)
					.build();
			response = client.newCall(request).execute();
			return new JSONObject(response.body().string());
		} catch (@NonNull IOException | JSONException e) {
			Log.e(TAG, "" + e.getLocalizedMessage());
		}
		return null;
	}

	public static JSONObject getDataById(int userId) {

		try {
			OkHttpClient client = new OkHttpClient();

			RequestBody formBody = new FormEncodingBuilder()
					.add(KEY_USER_ID, Integer.toString(userId))
					.build();

			Request request = new Request.Builder()
					.url(MAIN_URL)
					.post(formBody)
					.build();

			response = client.newCall(request).execute();
			return new JSONObject(response.body().string());

		} catch (IOException | JSONException e) {
			Log.e(TAG, "" + e.getLocalizedMessage());
		}
		return null;
	}
}
