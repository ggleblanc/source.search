package com.ggl.source.search.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ggl.source.search.model.SourceSearchModel;
import com.ggl.source.search.thread.RestoreActionThread;
import com.ggl.source.search.thread.SearchActionThread;

public class SearchPanel {

	protected JCheckBox caseCheckBox;
	
	protected JPanel panel;
	
	protected JTextField findTextField;
	
	protected SourceSearchFrame frame;
	
	protected SourceSearchModel model;
	
	public SearchPanel(SourceSearchFrame frame,
			SourceSearchModel model) {
		this.frame = frame;
		this.model = model;
		createPartControl();
	}

	protected void createPartControl() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		
		JLabel findLabel = new JLabel("Search:");
		panel.add(findLabel);
		
		panel.add(Box.createRigidArea(new Dimension(6, 0)));
		
		findTextField = new JTextField(30);
		panel.add(findTextField);
		
		panel.add(Box.createRigidArea(new Dimension(6, 0)));
		
		caseCheckBox = new JCheckBox("Case sensitive?");
		panel.add(caseCheckBox);
		
		panel.add(Box.createRigidArea(new Dimension(6, 0)));
		
		JButton findButton = new JButton("Search");
		findButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				String s = findTextField.getText().trim();
				if (!s.equals("")) {
					boolean caseSensitive = caseCheckBox.getModel().isSelected();
					findTextField.setText(s);
					frame.closeTree();
					Thread thread = new Thread(new SearchActionThread(frame,
							model, s, caseSensitive));
					thread.start();
				}
			}
		});
		panel.add(findButton);
		
		panel.add(Box.createRigidArea(new Dimension(6, 0)));
		
		JButton restoreButton = new JButton("Restore");
		restoreButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				frame.closeTree();
				Thread thread = new Thread(new RestoreActionThread(frame, model));
				thread.start();
			}
		});
		panel.add(restoreButton);
	}

	public JPanel getPanel() {
		return panel;
	}
	
}
