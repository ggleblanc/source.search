package com.ggl.source.search.thread;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import com.ggl.source.search.model.JarDirectory;
import com.ggl.source.search.model.JarFile;
import com.ggl.source.search.model.JavaClass;
import com.ggl.source.search.model.SourceSearchModel;
import com.ggl.source.search.view.SourceSearchFrame;

public class SourceFilesThread implements Runnable {
	
	protected SourceSearchFrame frame;
	
	protected SourceSearchModel model;

	public SourceFilesThread(SourceSearchFrame frame, 
			SourceSearchModel model) {
		this.frame = frame;
		this.model = model;
	}

	@Override
	public void run() {
		frame.startWaitCursor();
		setSourceFiles(model.getInputDirectory());
		try {
			processSourceFiles();
			model.setFullTreeModel();
		} catch (ZipException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int directoryCount = 0;
		int sourceCount = 0;
		for (JarFile file : model.getSourceFiles()) {
			directoryCount += file.getDirectories().size();
			for (JarDirectory directory : file.getDirectories()) {
				sourceCount += directory.getClassList().size();
			}
		}
		
		final String s = buildCountsMessage(directoryCount, sourceCount);	
		showMessageDialog(s);
		
		frame.stopWaitCursor();
	}
	
	protected void setSourceFiles(File directory) {
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				setSourceFiles(file);
			} else if (file.isFile()) {
				String fileName = file.getName().toLowerCase();
				if ((fileName.endsWith(".jar"))
						&& (fileName.contains("source"))) {
					JarFile jarFile = new JarFile(file);
					model.addSourceFile(jarFile);
//					System.out.println(fileName);
				}
			}
		}
	}
	
	protected void processSourceFiles() throws ZipException, 
			UnsupportedEncodingException, IOException {
		for (JarFile jarFile : model.getSourceFiles()) {
			File file = jarFile.getSourceFile();
			ZipFile zipFile = new ZipFile(file);
			Enumeration<? extends ZipEntry> zipEntries = zipFile.entries();
			String previousPathName = "";
			JarDirectory jarDirectory = new JarDirectory();
			while (zipEntries.hasMoreElements()) {
				ZipEntry zipEntry = zipEntries.nextElement();
				if (!zipEntry.isDirectory()) {
					String fullName = zipEntry.getName();
					if (fullName.toLowerCase().endsWith(".java")) {
						String pathName = "";
						String className = fullName;
						int pos = fullName.lastIndexOf("/");
						if (pos >= 0) {
							pathName = fullName.substring(0, pos);
							className = fullName.substring(pos + 1);
						}
						JavaClass javaClass = setJavaClass(zipFile, zipEntry,
								className);
						if (previousPathName.equals(pathName)) {
							jarDirectory.addJavaClass(javaClass);
						} else {
							if (previousPathName.equals("")) {
								jarDirectory.setDirectory(pathName);
							} else {
								jarFile.addDirectory(jarDirectory);
								jarDirectory = new JarDirectory(pathName);
							}
							jarDirectory.addJavaClass(javaClass);
							previousPathName = pathName;
						}
					}
				}
			}
			jarFile.addDirectory(jarDirectory);
		}
	}

	protected JavaClass setJavaClass(ZipFile zipFile, ZipEntry zipEntry,
			String className) throws IOException, UnsupportedEncodingException {
		JavaClass javaClass = new JavaClass(className);
		InputStream inputStream = zipFile.getInputStream(zipEntry);
		BufferedReader br = new BufferedReader(
				new InputStreamReader(inputStream, "UTF-8"));
		String line;
		while ((line = br.readLine()) != null) {
			javaClass.addCodeLine(line);
		}
		br.close();
		return javaClass;
	}
	
	protected String buildCountsMessage(int directoryCount, int sourceCount) {
		StringBuilder builder = new StringBuilder();
		builder.append("JAR file count     : ");
		builder.append(String.format("%,8d", model.getSourceFiles().size()));
		builder.append(System.getProperty("line.separator"));
		builder.append("JAR directory count: ");
		builder.append(String.format("%,8d", directoryCount));
		builder.append(System.getProperty("line.separator"));
		builder.append("JAR class count    : ");
		builder.append(String.format("%,8d", sourceCount));

		return builder.toString();
	}

	protected void showMessageDialog(final String s) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JTextArea message = new JTextArea(s);
				Font font = new Font("Courier New", Font.PLAIN, 12);
				message.setFont(font);
				message.setOpaque(false);

				JOptionPane.showMessageDialog(frame.getFrame(), message,
						"Source Code Counts", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	}

}
