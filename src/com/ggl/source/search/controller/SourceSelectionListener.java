package com.ggl.source.search.controller;

import javax.swing.SwingUtilities;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.ggl.source.search.model.JavaClass;
import com.ggl.source.search.model.SourceSearchModel;
import com.ggl.source.search.view.SourceSearchFrame;

public class SourceSelectionListener implements TreeSelectionListener {

	protected SourceSearchFrame frame;
	
	protected SourceSearchModel model;
	
	public SourceSelectionListener(SourceSearchFrame frame, SourceSearchModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void valueChanged(TreeSelectionEvent event) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) event.getPath()
				.getLastPathComponent();
		Object object = node.getUserObject();
		if (object instanceof JavaClass) {
			JavaClass javaClass = (JavaClass) object;
			model.setCode(javaClass.getCode());
			if (SwingUtilities.isEventDispatchThread()) {
				frame.getTextPane().setCaretPosition(0);
				frame.getTextPane().invalidate();
			} else {		
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						frame.getTextPane().setCaretPosition(0);
						frame.getTextPane().invalidate();
					}
				});
			}
		}
	}

}
