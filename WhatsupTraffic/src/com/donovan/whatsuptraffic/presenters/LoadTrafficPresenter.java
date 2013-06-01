package com.donovan.whatsuptraffic.presenters;

import com.donovan.whatsuptraffic.models.LoadTrafficModel;
import com.donovan.whatsuptraffic.views.interfaces.ILoadTrafficView;
import com.donovan.whatsuptraffic.views.interfaces.IView;

/**
 * @author Donovan Isherwood
 *
 */
public class LoadTrafficPresenter {

	private ILoadTrafficView loadTrafficView;
	private LoadTrafficModel loadTrafficModel;
	
	public LoadTrafficPresenter(ILoadTrafficView view){
		this.loadTrafficView = view;
		
		//initialise a new traffic model for this instance
		loadTrafficModel = new LoadTrafficModel();
	}
	
	public void loadTraffic(){
		
	}
}
