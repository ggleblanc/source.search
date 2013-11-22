package com.ggl.source.search.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class SourceTreeModel {

	protected DefaultMutableTreeNode rootNode;
	
	protected DefaultTreeModel defaultTreeModel;
	
	protected SourceSearchModel model;
	
	public SourceTreeModel(SourceSearchModel model) {
		this.model = model;
		this.rootNode = new DefaultMutableTreeNode("Source Code");
		this.defaultTreeModel = new DefaultTreeModel(this.rootNode);
	}
	
	public DefaultTreeModel getDefaultTreeModel() {
		return defaultTreeModel;
	}
	
	public DefaultMutableTreeNode getRootNode() {
		return rootNode;
	}
	
	public void setFullTreeModel() {
		this.rootNode.removeAllChildren();
		defaultTreeModel.reload();
		for (JarFile jarFile : model.getSourceFiles()) {
			DefaultMutableTreeNode jarNode = new DefaultMutableTreeNode(jarFile);
			rootNode.add(jarNode);
			for (JarDirectory jarDirectory : jarFile.getDirectories()) {
				DefaultMutableTreeNode directoryNode = new DefaultMutableTreeNode(
						jarDirectory);
				jarNode.add(directoryNode);
				for (JavaClass javaClass : jarDirectory.getClassList()) {
					DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(
							javaClass);
					directoryNode.add(classNode);
				}
			}
		}
		defaultTreeModel.reload();
	}
	
	public void setSelectedTreeModel(String searchTerm, boolean caseSensitive) {
		this.rootNode.removeAllChildren();
		defaultTreeModel.reload();
		for (JarFile jarFile : model.getSourceFiles()) {
			DefaultMutableTreeNode jarNode = null;
			for (JarDirectory jarDirectory : jarFile.getDirectories()) {
				DefaultMutableTreeNode directoryNode = null;
				for (JavaClass javaClass : jarDirectory.getClassList()) {
					if (javaClass.isFound(searchTerm, caseSensitive)) {
						if (jarNode == null) {
							jarNode = new DefaultMutableTreeNode(jarFile);
							rootNode.add(jarNode);
						}
						if (directoryNode == null) {
							directoryNode = new DefaultMutableTreeNode(
									jarDirectory);
							jarNode.add(directoryNode);
						}
						DefaultMutableTreeNode classNode = new DefaultMutableTreeNode(
								javaClass);
						directoryNode.add(classNode);
					}
				}
			}
		}
		defaultTreeModel.reload();
	}
}
