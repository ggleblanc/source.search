package com.ggl.source.search;

import javax.swing.SwingUtilities;

import com.ggl.source.search.model.SourceSearchModel;
import com.ggl.source.search.view.SourceSearchFrame;

public class SourceSearch implements Runnable {

	@Override
	public void run() {
		new SourceSearchFrame(new SourceSearchModel());		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new SourceSearch());
	}	
	
}
