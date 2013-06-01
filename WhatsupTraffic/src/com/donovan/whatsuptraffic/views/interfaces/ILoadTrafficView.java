/**
 * 
 */
package com.donovan.whatsuptraffic.views.interfaces;

import java.util.List;

import twitter4j.Status;

/**
 * @author Donovan Isherwood
 *
 */
public interface ILoadTrafficView extends IView {
	
	public void showTrafficTweets(List<Status> tweetList);
}
