package com.ggl.source.search.model;

import java.util.ArrayList;
import java.util.List;

public class JarDirectory {
	
	protected List<JavaClass> classList;
	
	protected String directory;
	
	public JarDirectory() {
		this.classList = new ArrayList<JavaClass>();
	}
	
	public JarDirectory(String directory) {
		this.directory = directory;
		this.classList = new ArrayList<JavaClass>();
	}

	public List<JavaClass> getClassList() {
		return classList;
	}

	public String getDirectory() {
		return directory;
	}
	
	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public void addJavaClass(JavaClass javaClass) {
		this.classList.add(javaClass);
	}
	
	@Override
	public String toString() {
		return getDirectory();
	}

}
