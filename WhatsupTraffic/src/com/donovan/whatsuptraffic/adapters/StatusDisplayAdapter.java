package com.donovan.whatsuptraffic.adapters;

import java.util.ArrayList;

import com.donovan.whatsuptraffic.R;

import twitter4j.Status;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StatusDisplayAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Status> statuses;
	
	public StatusDisplayAdapter(Context context, ArrayList<Status> statuses)
	{
		super();
		this.context = context;
		this.statuses = statuses;
	}
	
	static class ViewHolder {
	    public TextView userText;
	    public TextView statusText;
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
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.status_row_view, parent, false);
		TextView textUserView = (TextView) rowView.findViewById(R.id.usertext);
		TextView textStatusView = (TextView) rowView.findViewById(R.id.statustext);
		
		Status status = (Status)this.getItem(position);
		textUserView.setText(status.getUser().getName());
		textStatusView.setText(status.getText());
		
	    return rowView;
	}

}
