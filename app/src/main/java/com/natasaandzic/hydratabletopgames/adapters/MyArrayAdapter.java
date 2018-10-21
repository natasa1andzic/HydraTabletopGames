package com.natasaandzic.hydratabletopgames.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.natasaandzic.hydratabletopgames.R;
import com.natasaandzic.hydratabletopgames.model.MyDataModel;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<MyDataModel>{

	List<MyDataModel> modelList;
	Context context;
	private LayoutInflater mInflater;

	// Constructors
	public MyArrayAdapter(Context context, List<MyDataModel> objects) {
		super(context, 0, objects);
		this.context = context;
		this.mInflater = LayoutInflater.from(context);
		modelList = objects;
	}

	@Override
	public MyDataModel getItem(int position) {

		return modelList.get(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder vh;
		if (convertView == null) {
			View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
			vh = ViewHolder.create((RelativeLayout) view);
			view.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		MyDataModel item = getItem(position);

		vh.eventNameTv.setText(item.getEventName());
		vh.eventDateTv.setText(item.getEventDate());
		vh.eventTimeTv.setText(item.getEventTime());
		vh.eventDayTv.setText(item.getEventDay());
		vh.eventDescriptionTv.setText(item.getEventDescription());

		return vh.rootView;
	}


	private static class ViewHolder {

		public final RelativeLayout rootView;

		public final TextView eventNameTv;
		public final TextView eventDateTv;
		public final TextView eventTimeTv;
		public final TextView eventDayTv;
		public final TextView eventDescriptionTv;

		private ViewHolder(RelativeLayout rootView, TextView eventNameTv, TextView eventDateTv, TextView eventTimeTv, TextView eventDayTv, TextView eventDescriptionTv) {
			this.rootView = rootView;
			this.eventNameTv = eventNameTv;
			this.eventDateTv = eventDateTv;
			this.eventTimeTv = eventTimeTv;
			this.eventDayTv = eventDayTv;
			this.eventDescriptionTv = eventDescriptionTv;
		}

		public static ViewHolder create(RelativeLayout rootView) {
			TextView eventNameTv = (TextView) rootView.findViewById(R.id.eventNameTv);
			TextView eventDateTv = (TextView) rootView.findViewById(R.id.eventDateTv);
			TextView eventTimeTv = (TextView) rootView.findViewById(R.id.eventTimeTv);
			TextView eventDayTv = (TextView) rootView.findViewById(R.id.eventDayTv);
			TextView eventDescriptionTv = (TextView) rootView.findViewById(R.id.eventDescriptionTv);
			return new ViewHolder(rootView, eventNameTv, eventDateTv, eventTimeTv, eventDayTv, eventDescriptionTv);
		}
	}
}
