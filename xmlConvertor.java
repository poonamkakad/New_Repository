package com.demo.parse;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class xmlConvertor {

	public void generateXML(String parseStr) throws ParserConfigurationException, TransformerException, IOException {
		
		FileReader reader=new FileReader("FilePath.properties");
		Properties p=new Properties();  
		p.load(reader);
		
		String[] sentenceArr = parseStr.split("(?<!Mr|Mrs)[?!.]\\\\s+");
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		
		Element rootElement = doc.createElement("text");
		doc.appendChild(rootElement);
		
		//comparator to sort String array in alphabetical order
				Comparator<String> alphabeticalSort =  (String o1, String o2) -> o1.toLowerCase().compareTo(o2.toLowerCase());
				
				for(String sentence : sentenceArr) {
					
					Element sentenceEle = doc.createElement("sentence");
					rootElement.appendChild(sentenceEle);
					
					String[] wordArr =  sentence.split("[\\\\s:!.,-]+");
					
					//Sorting words using comparator 
					Arrays.sort(wordArr, alphabeticalSort);
					System.out.println(Arrays.asList(wordArr));
					
					for(String word : wordArr) {
						
						Element wordEle = doc.createElement("word");
						wordEle.setTextContent(word);
						sentenceEle.appendChild(wordEle);
						
					}
				}
				
				//Output path read from properties file
				String xmlOutputPath = p.getProperty("xmlOutputPath");
				try (FileOutputStream output =new FileOutputStream(xmlOutputPath)) 
				{
					writeXml(doc, output);
					
			    } catch (IOException e) {
			    	e.printStackTrace();
			    }
		
	}
	
	private static void writeXml(Document doc, OutputStream output)
			throws TransformerException {
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();

		transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //for xml indentation 

		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(output);

		transformer.transform(source, result);
	}

}
