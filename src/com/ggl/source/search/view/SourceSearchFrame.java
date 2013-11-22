package com.ggl.source.search.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.RootPaneContainer;

import com.ggl.source.search.model.SourceSearchModel;

public class SourceSearchFrame {
	
	protected JFrame frame;
	
	protected JPanel panel;
	
	protected SourceSearchModel model;
	
	protected TextPanel					textPanel;
	
	protected TreeScrollPane			treeScrollPane;
	
	public SourceSearchFrame(SourceSearchModel model) {
		this.model = model;
		createPartControl();
	}

	protected void createPartControl() {
		SourceSearchMenuBar menuBar = new SourceSearchMenuBar(this, model);
		textPanel = new TextPanel(this, model);
		treeScrollPane = new TreeScrollPane(this, model);
			
		frame = new JFrame();
		frame.setTitle("Source Search");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				exitProcedure();
			}
		});
		
		panel = new JPanel();
		LayoutManager layout = new BorderLayout();
		panel.setLayout(layout);
		
		panel.add(textPanel.getPanel(), BorderLayout.WEST);
		panel.add(treeScrollPane.getTreeScrollPane(), BorderLayout.CENTER);
		
		frame.setLayout(new FlowLayout());
		frame.setJMenuBar(menuBar.getMenuBar());
		frame.add(panel);
//		frame.setSize(640, 480);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void exitProcedure() {	
		frame.dispose();
		System.exit(0);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JTextPane getTextPane() {
		return textPanel.getTextScrollPane().getTextPane();
	}
	
	public void closeTree() {
		this.treeScrollPane.closeTree();
	}
	
	public void startWaitCursor() {
		RootPaneContainer root = (RootPaneContainer) frame.getRootPane()
				.getTopLevelAncestor();
		root.getGlassPane().setCursor(
				Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		root.getGlassPane().setVisible(true);
	}

	public void stopWaitCursor() {
		RootPaneContainer root = (RootPaneContainer) frame.getRootPane()
				.getTopLevelAncestor();
		root.getGlassPane().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		root.getGlassPane().setVisible(false);
	}
}
