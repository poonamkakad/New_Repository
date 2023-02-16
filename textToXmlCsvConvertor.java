package com.demo.parse;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class textToXmlCsvConvertor {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, TransformerException {
		
		//Read Properties file
		FileReader reader=new FileReader("application.properties");
		Properties p=new Properties();  
		p.load(reader);

		//Read Input File 
		String fileInputPath = p.getProperty("inputFilePath");
		File file = new File(fileInputPath);
	    Scanner sc = new Scanner(file);
	    
	    String parseStr = "";
	    
	    while (sc.hasNextLine()) {
	    	parseStr = parseStr + sc.nextLine();
		}
	    
	    //Method call to convert to XML format
	    xmlConvertor xmlUtil = new xmlConvertor();
	    xmlUtil.generateXML(parseStr);	
		
	}

}
