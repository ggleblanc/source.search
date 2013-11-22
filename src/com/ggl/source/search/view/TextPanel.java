package com.ggl.source.search.view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import com.ggl.source.search.model.SourceSearchModel;

public class TextPanel {
	
	protected JPanel panel;
	
	protected SourceSearchFrame frame;
	
	protected SourceSearchModel model;
	
	protected TextScrollPane textScrollPane;
	
	public TextPanel(SourceSearchFrame frame,
			SourceSearchModel model) {
		this.frame = frame;
		this.model = model;
		createPartControl();
	}

	protected void createPartControl() {
		SearchPanel searchPanel = new SearchPanel(frame, model);
		textScrollPane = new TextScrollPane(model);
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		
		panel.add(searchPanel.getPanel(), BorderLayout.NORTH);
		panel.add(textScrollPane.getTextScrollPane(), BorderLayout.CENTER);
	}

	public JPanel getPanel() {
		return panel;
	}

	public TextScrollPane getTextScrollPane() {
		return textScrollPane;
	}

}
