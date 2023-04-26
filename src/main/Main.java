package main;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Main {
	
	public static void main(String[] args) {
		
		try {
			if (args.length == 0) {
				throw new Exception("No argument provided");
			}
			
			String filename = args[0];
			if (!filename.endsWith(".arxml")) {
				throw new NotValidAutosarFileException("The file does not a have a proper extension");
			}
			
			File autosarXML = new File(filename);
			if (autosarXML.length() == 0L) {
				throw new EmptyAutosarFileException("File input provided is either empty or does not exist");
			}
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder Builder = dbFactory.newDocumentBuilder();
			Document ParsedXML = Builder.parse(autosarXML);
			
			NodeList Containers = ParsedXML.getElementsByTagName("CONTAINER");
			
			ArrayList<Element> elements = new ArrayList<Element>();
			
			for (int i = 0; i < Containers.getLength(); i++) {
				elements.add((Element)Containers.item(i));
				
			}
			
			XMLWriter file = new XMLWriter(autosarXML);
			file.sortElements(elements);
			file.generate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
