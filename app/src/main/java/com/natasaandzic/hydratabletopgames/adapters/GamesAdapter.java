package com.natasaandzic.hydratabletopgames.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.google.android.gms.games.Game;
import com.natasaandzic.hydratabletopgames.R;
import com.natasaandzic.hydratabletopgames.model.GamesDataModel;

import java.util.ArrayList;

public class GamesAdapter extends RecyclerView.Adapter<GamesAdapter.MyViewHolder> {

	private ArrayList<GamesDataModel> modelList;

	public GamesAdapter(ArrayList<GamesDataModel> modelList) {
		this.modelList = modelList;
	}


	@NonNull
	@Override
	public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_view_games, parent, false);
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
		GamesDataModel model = modelList.get(position);

		String gameName = model.getGameName();
		String gamePrice = model.getGamePrice();
		String gameDescription = model.getGameDescription();
		String gameGenre = model.getGameGenre();

		holder.gameNameTv.setText(gameName);
		holder.gamePriceTv.setText(gamePrice);
		holder.gameDescriptionTv.setText(gameDescription);
		holder.gameGenreTv.setText(gameGenre);

	}
	@Override
	public void onAttachedToRecyclerView(RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
	}


	@Override
	public int getItemCount() {
		return modelList.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder{

		public final TextView gameNameTv;
		public final TextView gamePriceTv;
		public final TextView gameDescriptionTv;
		public final TextView gameGenreTv;

		private MyViewHolder(View itemView) {
			super(itemView);
			gameNameTv = (TextView)itemView.findViewById(R.id.gameNameTv);
			gamePriceTv = (TextView)itemView.findViewById(R.id.gamePriceTv);
			gameDescriptionTv = (TextView)itemView.findViewById(R.id.gameDescriptionTv);
			gameGenreTv = (TextView)itemView.findViewById(R.id.gameGenreTv);
		}


	}
}
