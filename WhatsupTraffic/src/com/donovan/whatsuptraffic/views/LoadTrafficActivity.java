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
import android.app.ListActivity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class LoadTrafficActivity extends Activity implements ILoadTrafficView{

	private LoadTrafficPresenter loadTrafficPresenter;
	private ArrayList<Status> statuses;
	private StatusDisplayAdapter statusDisplayAdapter;
	private ListView listView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_traffic);
		
		listView = (ListView)findViewById(R.id.list_statuses);
		
		//initialise LoadTrafficPresenter
		loadTrafficPresenter = new LoadTrafficPresenter(this);
		loadTrafficPresenter.loadTraffic();
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
		Log.d("LoadTrafficActivity", "Clicked load traffic");
		loadTrafficPresenter.loadTraffic();
	}

	@Override
	public void showUserMessage(String message) {
		Log.d("LoadTrafficActivity", "Showing toast");
		Context context = getApplicationContext();
		CharSequence text = message;
		int duration = Toast.LENGTH_LONG;
		
		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
	}

	@Override
	public void showTrafficTweets(List<Status> tweetList) {
		Log.i("Hello", "No. Of Tweets: " + tweetList.size());
		
		ArrayList<String> array = new ArrayList<String>();
		for(Status s : tweetList){
			array.add(s.getText());
		}
		
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);
		statusDisplayAdapter = new StatusDisplayAdapter(this, new ArrayList<Status>(tweetList));
		listView.setAdapter(statusDisplayAdapter);
		
		
	}

}
