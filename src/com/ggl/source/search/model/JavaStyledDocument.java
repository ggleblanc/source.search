package com.ggl.source.search.model;

import java.util.List;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class JavaStyledDocument {
	
	protected StyleContext styleContext;
	
	protected StyledDocument document;
	
	public JavaStyledDocument() {
		this.styleContext = new StyleContext();
		this.document = new DefaultStyledDocument(this.styleContext);
	}
	
	public void setCode(List<String> code) {
		Style style = styleContext.getStyle(StyleContext.DEFAULT_STYLE);
		StyleConstants.setFontSize(style, 12);
		StyleConstants.setSpaceBelow(style, 4);
		try {
			int offset = 0;
			String lineEnd = System.getProperty("line.separator");
			int lineEndLength = lineEnd.length();
			document.remove(offset, document.getLength());
			for (String line : code) {
				document.insertString(offset, line, style);
				offset += line.length();
				document.insertString(offset, lineEnd, style);
				offset += lineEndLength;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public StyledDocument getDocument() {
		return document;
	}
}
