package com.ggl.source.search.view;

import java.awt.Dimension;
import java.util.Enumeration;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import com.ggl.source.search.controller.SourceSelectionListener;
import com.ggl.source.search.model.SourceSearchModel;

public class TreeScrollPane {
	
	protected JScrollPane treeScrollPane;
	
	protected JTree tree;
	
	protected SourceSearchFrame frame;
	
	protected SourceSearchModel model;
	
	public TreeScrollPane(SourceSearchFrame frame, 
			SourceSearchModel model) {
		this.frame = frame;
		this.model = model;
		createPartControl();
	}

	protected void createPartControl() {
		tree = new JTree(model.getDefaultTreeModel());
		tree.setVisibleRowCount(25);
//		tree.addMouseListener(new TreeMouseListener(frame, model));
		tree.addTreeSelectionListener(new SourceSelectionListener(frame, model));
		tree.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		treeScrollPane = new JScrollPane(tree);
		treeScrollPane.getViewport().setPreferredSize(new Dimension(500, 640));
	}

	public JScrollPane getTreeScrollPane() {
		return treeScrollPane;
	}
	
	public void closeTree() {
		closeTreePath(model.getRootNode());
	}
	
	public void openTreePath(TreePath treePath) {
		TreeNode node = (TreeNode) treePath.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (@SuppressWarnings("unchecked")
			Enumeration<TreeNode> e = node.children(); e.hasMoreElements();) {
				TreeNode n = e.nextElement();
				TreePath path = treePath.pathByAddingChild(n);
				openTreePath(path);
			}
		}
		tree.expandPath(treePath);
		;
	}

	public void closeTreePath(TreePath treePath) {
		TreeNode node = (TreeNode) treePath.getLastPathComponent();
		if (node.getChildCount() >= 0) {
			for (@SuppressWarnings("unchecked")
			Enumeration<TreeNode> e = node.children(); e.hasMoreElements();) {
				TreeNode n = e.nextElement();
				TreePath path = treePath.pathByAddingChild(n);
				closeTreePath(path);
			}
		}
		tree.collapsePath(treePath);
	}
	
	public void closeTreePath(DefaultMutableTreeNode treeNode) {
		if (treeNode.getChildCount() >= 0) {
			for (@SuppressWarnings("unchecked")
			Enumeration<DefaultMutableTreeNode> e = treeNode.children(); e
					.hasMoreElements();) {
				DefaultMutableTreeNode n = e.nextElement();
				closeTreePath(n);
			}
		}
		TreePath path = new TreePath(treeNode.getPath());
		tree.collapsePath(path);
	}
	
	public boolean isCollapsed(TreePath treePath) {
		return tree.isCollapsed(treePath);
	}
	
	public TreePath getTreePath(int x, int y) {
		int selRow = tree.getRowForLocation(x, y);
		TreePath treePath = tree.getClosestPathForLocation(x, y);

		if (selRow >= 0) {
			tree.setSelectionPath(treePath);
			return treePath;
		} else {
			return null;
		}
	}
	
}
