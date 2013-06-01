package com.donovan.whatsuptraffic.views;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

import com.donovan.whatsuptraffic.R;
import com.donovan.whatsuptraffic.adapters.StatusDisplayAdapter;
import com.donovan.whatsuptraffic.presenters.LoadTrafficPresenter;
import com.donovan.whatsuptraffic.views.interfaces.ILoadTrafficView;
import com.donovan.whatsuptraffic.views.interfaces.IView;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class LoadTrafficActivity extends Activity implements ILoadTrafficView{

	private LoadTrafficPresenter loadTrafficPresenter;
	private ArrayList<Status> statuses;
	private StatusDisplayAdapter statusDisplayAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_traffic);
		
		//initialise LoadTrafficPresenter
		loadTrafficPresenter = new LoadTrafficPresenter(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * This methods instructs the presenter that it should load traffic
	 * @param view The view for which the onClick event occurs
	 */
	public void onClickLoadTraffic(View view){
		Log.d("Hello", "Clicked load traffic");
		loadTrafficPresenter.loadTraffic();
		
	}

	@Override
	public void showUserMessage(String message) {
		Log.d("Hello", "Showing toast");
		Context context = getApplicationContext();
		CharSequence text = message;
		int duration = Toast.LENGTH_LONG;
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
	}

	@Override
	public void showTrafficTweets(List<Status> tweetList) {
		Log.i("Hello", "No. Of Tweets: " + tweetList.size());
		
		ListView lstView = (ListView)findViewById(R.id.list_statuses);
		
		statusDisplayAdapter = new StatusDisplayAdapter(this, new ArrayList<Status>(tweetList));
		lstView.setAdapter(statusDisplayAdapter);	
		
	}

}
