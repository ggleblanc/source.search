package com.ggl.source.search.thread;

import com.ggl.source.search.model.SourceSearchModel;
import com.ggl.source.search.view.SourceSearchFrame;

public class SearchActionThread implements Runnable {

	protected boolean			caseSensitive;
	
	protected SourceSearchFrame frame;

	protected SourceSearchModel	model;

	protected String			searchTerm;

	public SearchActionThread(SourceSearchFrame frame, SourceSearchModel model,
			String searchTerm, boolean caseSensitive) {
		this.frame = frame;
		this.model = model;
		this.searchTerm = searchTerm;
		this.caseSensitive = caseSensitive;
	}

	@Override
	public void run() {
		frame.startWaitCursor();
		frame.closeTree();
		model.setSelectedTreeModel(searchTerm, caseSensitive);
		frame.stopWaitCursor();
	}

}
