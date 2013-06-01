package com.donovan.whatsuptraffic.views;

import twitter4j.Status;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

public class StatusView extends LinearLayout {
	
	private TextView userTextView;
	private TextView statusMessageTextView;
	
	public StatusView(Context context, Status status) {
		super(context);
		this.setOrientation(HORIZONTAL);
		
		userTextView = new TextView(context);
		statusMessageTextView = new TextView(context);
		
		userTextView.setText(status.getSource());
		statusMessageTextView.setText(status.getText());
		
		addView(userTextView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		addView(statusMessageTextView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
	}
	
	public void setUserTextView(String value){
		userTextView.setText(value);
	}
	
	public void setStatusMessageTextView(String value){
		statusMessageTextView.setText(value);
	}
	

}
