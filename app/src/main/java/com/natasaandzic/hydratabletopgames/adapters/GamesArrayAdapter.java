package com.natasaandzic.hydratabletopgames.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.natasaandzic.hydratabletopgames.R;
import com.natasaandzic.hydratabletopgames.model.GamesDataModel;

import java.util.List;

public class GamesArrayAdapter extends ArrayAdapter<GamesDataModel> {

	List<GamesDataModel> modelList;
	Context context;
	private LayoutInflater mInflater;

	// Constructors
	public GamesArrayAdapter(Context context, List<GamesDataModel> objects) {
		super(context, 0, objects);
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		modelList = objects;
	}

	@Override
	public GamesDataModel getItem(int position) {
		return modelList.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final GamesArrayAdapter.ViewHolder vh;
		if (convertView == null) {
			View view = mInflater.inflate(R.layout.layout_row_view_games, parent, false);
			vh = GamesArrayAdapter.ViewHolder.create((RelativeLayout) view);
			view.setTag(vh);
		} else {
			vh = (GamesArrayAdapter.ViewHolder) convertView.getTag();
		}

		GamesDataModel item = getItem(position);

		vh.gameNameTv.setText(item.getGameName());
		vh.gamePriceTv.setText(item.getGamePrice());
		vh.gameDescriptionTv.setText(item.getGameDescription());
		vh.gameGenreTv.setText(item.getGameGenre());

		return vh.rootView;
	}


	private static class ViewHolder {

		public final RelativeLayout rootView;

		public final TextView gameNameTv;
		public final TextView gamePriceTv;
		public final TextView gameDescriptionTv;
		public final TextView gameGenreTv;

		private ViewHolder(RelativeLayout rootView, TextView gameNameTv, TextView gamePriceTv, TextView gameDescriptionTv, TextView gameGenreTv) {
			this.rootView = rootView;
			this.gameNameTv = gameNameTv;
			this.gamePriceTv = gamePriceTv;
			this.gameDescriptionTv = gameDescriptionTv;
			this.gameGenreTv = gameGenreTv;
		}

		public static GamesArrayAdapter.ViewHolder create(RelativeLayout rootView) {
			TextView gameNameTv = (TextView) rootView.findViewById(R.id.gameNameTv);
			TextView gamePriceTv = (TextView) rootView.findViewById(R.id.gamePriceTv);
			TextView gameDescriptionTv = (TextView) rootView.findViewById(R.id.gameDescriptionTv);
			TextView gameGenreTv = (TextView) rootView.findViewById(R.id.gameGenreTv);
			return new GamesArrayAdapter.ViewHolder(rootView, gameNameTv, gamePriceTv, gameDescriptionTv, gameGenreTv);
		}
	}
}
