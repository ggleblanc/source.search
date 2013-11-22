package com.ggl.source.search.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JarFile {
	
	protected File sourceFile;
	
	protected List<JarDirectory> directories;
	
	public JarFile(File sourceFile) {
		this.sourceFile = sourceFile;
		this.directories = new ArrayList<JarDirectory>();
	}

	public File getSourceFile() {
		return sourceFile;
	}

	public List<JarDirectory> getDirectories() {
		return directories;
	}
	
	public void addDirectory(JarDirectory directory) {
		this.directories.add(directory);
	}
	
	@Override
	public String toString() {
		return getSourceFile().getName();
	}

}
