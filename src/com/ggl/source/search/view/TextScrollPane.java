package com.ggl.source.search.view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.DefaultCaret;

import com.ggl.source.search.model.SourceSearchModel;

public class TextScrollPane {
	
	protected JScrollPane textScrollPane;
	
	protected JTextPane textPane;
	
	protected SourceSearchModel model;
	
	public TextScrollPane(SourceSearchModel model) {
		this.model = model;
		createPartControl();
	}

	protected void createPartControl() {
		textPane = new JTextPane(model.getStyledDocument());
		textPane.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		Font font = new Font("Courier New", Font.PLAIN, 12);
		textPane.setFont(font);
		textPane.setPreferredSize(new Dimension(660, 540));
		
		DefaultCaret caret = (DefaultCaret) textPane.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
        textScrollPane = new JScrollPane(textPane);
	}

	public JScrollPane getTextScrollPane() {
		return textScrollPane;
	}

	public JTextPane getTextPane() {
		return textPane;
	}
	
}
