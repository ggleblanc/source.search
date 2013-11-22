package com.ggl.source.search.model;

import java.util.ArrayList;
import java.util.List;

public class JavaClass {
	
	protected List<String> code;
	
	protected String className;
	
	public JavaClass(String className) {
		this.className = className;
		this.code = new ArrayList<String>();
	}

	public List<String> getCode() {
		return code;
	}

	public String getClassName() {
		return className;
	}
	
	public void addCodeLine(String line) {
		this.code.add(line);
	}
	
	public boolean isFound(String searchString, boolean caseSensitive) {
		for (String line : code) {
			if (caseSensitive) {
				if (line.contains(searchString)) {
					return true;
				}
			} else {
				if (line.toLowerCase().contains(searchString.toLowerCase())) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return getClassName();
	}
}
