source.search
=============

Eclipse Source Search
---------------------

###Introduction

The Eclipse IDE for RCP and RAP developers contains the source code
for Eclipse.  The source code is packaged in plugin JAR files 
containing the word "source".

The plugin directory is located at /eclipse/plugins.

Three of the source JAR files in Eclipse 4.2 are:

* com.ibm.icu.source_4.4.2.v20110823.jar
* org.eclipse.core.commands.source_3.6.2.v20130123-162658.jar
* org.eclipse.core.databinding.source_1.4.1.v20120912-132807.jar

Looking inside of these source JAR files has been a painful task.

To make it easier, I decided to write a Java Swing application to
extract the source classes and allow for a limited search to find
the Eclipse class that you're looking for.

###Usage

First, extract the source search source code into a Java project.  
Compile and run.

Here's what the main window of the application looks like:

![main window](SourceSearch.png "Main Window")

