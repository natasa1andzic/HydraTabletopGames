package com.natasaandzic.hydratabletopgames.fragments;

import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.TextView;

import com.natasaandzic.hydratabletopgames.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GamesFragment extends ListFragment {

	public String[] values = new String[] { "Android", "iPhone", "WebOS", "Ubuntu", "Windows7", "Max OS X",};
	ListView lv;

	public GamesFragment(){

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_games, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Planets, android.R.layout.simple_list_item_1);
		setListAdapter(adapter);
	}

}
