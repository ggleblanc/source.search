package com.ggl.source.search.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.ggl.source.search.controller.SearchDirectoryActionListener;
import com.ggl.source.search.model.SourceSearchModel;

public class SourceSearchMenuBar {
	
	protected JMenu resourceMenu;
	protected JMenu taskMenu;
	protected JMenu holidayMenu;

	protected JMenuBar menuBar;
	
	protected JMenuItem deleteProjectMenuItem;
	
	protected SourceSearchFrame frame;
	
	protected SourceSearchModel model;
	
	public SourceSearchMenuBar(SourceSearchFrame frame, 
			SourceSearchModel model) {
		this.frame = frame;
		this.model = model;
		createPartControl();
	}
	
	protected void createPartControl() {
		menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fileMenu);
		
		JMenuItem searchMenuItem = 
				new JMenuItem("Search Directory", KeyEvent.VK_D);
		searchMenuItem.addActionListener(
				new SearchDirectoryActionListener(frame, model));
		fileMenu.add(searchMenuItem);
		
		fileMenu.addSeparator();
		
		JMenuItem exitMenuItem = new JMenuItem("Exit", KeyEvent.VK_X);
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.exitProcedure();
			}
		});
		fileMenu.add(exitMenuItem);
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}

}
