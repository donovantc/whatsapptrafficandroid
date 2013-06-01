package com.donovan.whatsuptraffic.adapters;

import java.util.ArrayList;

import com.donovan.whatsuptraffic.views.StatusView;

import twitter4j.Status;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class StatusDisplayAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Status> statuses;
	
	public StatusDisplayAdapter(Context context, ArrayList<Status> statuses)
	{
		this.context = context;
		this.statuses = statuses;
	}
	
	@Override
	public int getCount() {
		if( statuses != null)
			return statuses.size();
		else
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return statuses.get(position);
	}

	@Override
	public long getItemId(int position) {
		return statuses.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		StatusView statusView;
		
		if (convertView == null)
			statusView = new StatusView(this.context, statuses.get(position));
		else{
			statusView = (StatusView) convertView;
			statusView.setUserTextView(statuses.get(position).getSource());
			statusView.setStatusMessageTextView(statuses.get(position).getText());
		}
		
		return statusView;
	}

}
