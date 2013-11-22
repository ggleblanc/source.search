package com.ggl.source.search.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.ggl.source.search.view.extended.LayoutFileFilter;

public class SourceSearchModel {
	
	public static final LayoutFileFilter JAR_FILTER = 
			new LayoutFileFilter("Source Java Archive Files", ".jar", true);
	
	protected File inputDirectory;
	
	protected JavaStyledDocument styledDocument;
	
	protected List<JarFile> sourceFiles;
	
	protected SourceTreeModel treeModel;
	
	public SourceSearchModel() {
		this.inputDirectory = new File("C:/eclipse-4.2-rcp/eclipse/plugins");
		this.sourceFiles = new ArrayList<JarFile>();
		this.treeModel = new SourceTreeModel(this);
		this.styledDocument = new JavaStyledDocument();
	}
	
	public List<JarFile> getSourceFiles() {
		return sourceFiles;
	}

	public File getInputDirectory() {
		return inputDirectory;
	}

	public StyledDocument getStyledDocument() {
		return styledDocument.getDocument();
	}
	
	public DefaultTreeModel getDefaultTreeModel() {
		return treeModel.getDefaultTreeModel();
	}
	
	public DefaultMutableTreeNode getRootNode() {
		return treeModel.getRootNode();
	}

	public void setInputDirectory(File inputDirectory) {
		this.inputDirectory = inputDirectory;
	}
	
	public void setFullTreeModel() {
		this.treeModel.setFullTreeModel();
	}
	
	public void setSelectedTreeModel(String searchTerm, boolean caseSensitive) {
		this.treeModel.setSelectedTreeModel(searchTerm, caseSensitive);
	}
	
	public void setCode(List<String> code) {
		this.styledDocument.setCode(code);
	}
	
	public void addSourceFile(JarFile jarFile) {
		this.sourceFiles.add(jarFile);
	}
	
}
