package com.donovan.whatsuptraffic.presenters;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.util.Log;

import com.donovan.whatsuptraffic.models.LoadTrafficModel;
import com.donovan.whatsuptraffic.views.interfaces.ILoadTrafficView;
import com.donovan.whatsuptraffic.views.interfaces.IView;
import twitter4j.*;
import twitter4j.api.HelpResources.Language;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.internal.async.*;

/**
 * @author Donovan Isherwood
 *
 */
public class LoadTrafficPresenter {

	private ILoadTrafficView loadTrafficView;
	private LoadTrafficModel loadTrafficModel;
	
	static final Object LOCK = new Object();
	
	public LoadTrafficPresenter(ILoadTrafficView view){
		this.loadTrafficView = view;
		
		//initialise a new traffic model for this instance
		loadTrafficModel = new LoadTrafficModel();
	}
	
	private Configuration getConfiguration()
	{
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("SmZQc3dDpGaS6GHudV9A")
		  .setOAuthConsumerSecret("LtjABfkW9y6bNYiUwRXdXv47vowPofynsXYUVIsyw")
		  .setOAuthAccessToken("1471770030-gtgAhkDOolOXADKK7wIDyWaOsC2XUjTJrWn2kxZ")
		  .setOAuthAccessTokenSecret("7cp7hNFZYUtWlxJIGvTnr1Fp5bIK3cP8IWiYpoW4FTc");
		
		return cb.build();
	}
	
	public void upDateStatus(String status)
	{
		AsyncTwitterFactory factory = new AsyncTwitterFactory(this.getConfiguration());
		AsyncTwitter twitter = factory.getInstance();
		
		twitter.addListener(new TwitterAdapter() {
			@Override
			public void updatedStatus(Status arg0) {
				// TODO Auto-generated method stub
				Log.i("Twitter", "Status updated: " + arg0.getText());
				synchronized (LOCK) {
                    LOCK.notify();
                }
			}
			
			@Override
			public void onException(TwitterException arg0, TwitterMethod arg1) {
				// TODO Auto-generated method stub
				arg0.printStackTrace();
				
				loadTrafficView.showUserMessage("Status updated.");
				
				synchronized (LOCK) {
                    LOCK.notify();
                }				
			}
		});
		
		twitter.updateStatus(new StatusUpdate("Hello World Test 2"));
		
		try
		{
			synchronized (LOCK) {
				LOCK.wait();
			}
		}catch (InterruptedException e)
		{
			Log.e("LoadTrafficPresenter", e.getMessage());
			this.loadTrafficView.showUserMessage("Exception occurred: " + e.getMessage());
		}
	}
	
	
	public void loadTraffic(){

		AsyncTwitterFactory factory = new AsyncTwitterFactory(getConfiguration());
		AsyncTwitter twitter = factory.getInstance();
		
		twitter.addListener(new TwitterAdapter() {
			@Override
			public void gotHomeTimeline(ResponseList<Status> arg0) {
				Log.i("LoadTrafficPresenter", "Home timeline loaded");
				
				synchronized (LOCK){
					LOCK.notify();
				}
				
				//display timeline list
				loadTrafficView.showTrafficTweets(arg0);				
			}
			
			@Override
			public void onException(TwitterException arg0, TwitterMethod arg1) {
				// TODO Auto-generated method stub
				arg0.printStackTrace();
				
				loadTrafficView.showUserMessage("Unable to retrieve home timeline");
				
				synchronized (LOCK) {
                    LOCK.notify();
                }
				
			}			
		});

		twitter.verifyCredentials();
		twitter.getHomeTimeline();
		
		try
		{
			synchronized (LOCK) {
				LOCK.wait();
			}
		}catch (InterruptedException e)
		{
			Log.e("LoadTrafficPresenter", e.getMessage());
			loadTrafficView.showUserMessage("Exception occurred: " + e.getMessage());
		}
		
	}
}
