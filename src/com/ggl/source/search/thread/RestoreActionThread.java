package com.ggl.source.search.thread;

import com.ggl.source.search.model.SourceSearchModel;
import com.ggl.source.search.view.SourceSearchFrame;

public class RestoreActionThread implements Runnable {
	
	protected SourceSearchFrame frame;
	
	protected SourceSearchModel model;
	
	public RestoreActionThread(SourceSearchFrame frame, 
			SourceSearchModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void run() {
		frame.startWaitCursor();
		frame.closeTree();
		model.setFullTreeModel();
		frame.stopWaitCursor();
	}

}
