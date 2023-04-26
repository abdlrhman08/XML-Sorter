package main;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class XMLWriter {
	private DocumentBuilderFactory dbFactory;
	private DocumentBuilder builder;
	private Document newDoc;
	
	private TransformerFactory tFactory;
	private Transformer transformer;
	
	private Element root;
	
	private ArrayList<Element> List;
	
	private String newName;
	
	public XMLWriter(File file) {
		try { 
			
			this.newName = file.getName().replace(".arxml", "_mod.arxml");
			
			this.dbFactory = DocumentBuilderFactory.newInstance();
			this.builder = this.dbFactory.newDocumentBuilder();
			this.newDoc= this.builder.newDocument();
			
			this.root = this.newDoc.createElement("AUTOSAR");
			this.newDoc.appendChild(this.root);
			
			this.tFactory = TransformerFactory.newInstance();
			this.transformer = this.tFactory.newTransformer();
			this.transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			this.transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void generate() throws TransformerException {
		
		for (Element element : this.List) {
			//Node must be imported because it is a property of another document instance
			Node newNode = this.newDoc.importNode(element, true);
			this.root.appendChild(newNode);
		}
		
		//Generate new file
		DOMSource src = new DOMSource(this.newDoc);
		StreamResult result = new StreamResult(new File(this.newName));
		this.transformer.transform(src, result);
		System.out.println(this.newName + " generated");
	}
	
	//Sort the elements in the array list
	public void sortElements(ArrayList<Element> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			Element min = list.get(i);
			
			for (int j = i + 1; j < list.size(); j++) {
				String name2 = list.get(j).getElementsByTagName("SHORT-NAME").item(0).getTextContent();
				
				if (min.getElementsByTagName("SHORT-NAME").item(0).getTextContent().get.compareTo(name2) > 0) {
					// Random element to instantiate a temporary element object
					min = list.get(j);
					
				}
			
			}
			
			Element temp = list.get(i);
				
			list.set(i, min);
			list.set(min, temp);
			}
		}
		
		this.List = list;
		
	}
}
