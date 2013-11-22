package com.ggl.source.search.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import com.ggl.source.search.model.SourceSearchModel;
import com.ggl.source.search.thread.SourceFilesThread;
import com.ggl.source.search.view.SourceSearchFrame;
import com.ggl.source.search.view.extended.ExtensionFileFilter;

public class SearchDirectoryActionListener implements ActionListener {
	
	protected SourceSearchFrame frame;
	
	protected SourceSearchModel model;

	public SearchDirectoryActionListener(SourceSearchFrame frame,
			SourceSearchModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		chooseSearchDirectory();
	}
	
	protected int chooseSearchDirectory() {
		JFileChooser fileChooser = new JFileChooser(model.getInputDirectory());
		fileChooser.setDialogTitle("Search Directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		ExtensionFileFilter pFilter = new ExtensionFileFilter(SourceSearchModel.JAR_FILTER);
		fileChooser.setFileFilter(pFilter);
		
		if (model.getInputDirectory() != null) {
			fileChooser.setSelectedFile(model.getInputDirectory());
		}
		
		int status = fileChooser.showOpenDialog(frame.getFrame());
		
		if (status == JFileChooser.APPROVE_OPTION) {
			model.setInputDirectory(fileChooser.getSelectedFile());
			Thread thread = new Thread(new SourceFilesThread(frame, model));
			thread.start();
		}
		
		return status;
	}

}
