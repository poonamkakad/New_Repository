package Parse;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.util.Properties;

public class Text_To_XML_CSV_Convertor {
	
public static void main(String[] args) throws ParserConfigurationException, TransformerException, IOException {
	
				//Read Properties file
				FileReader reader=new FileReader("FilePath.properties");
				Properties p=new Properties();  
				p.load(reader);

				//Read File 
				String fileInputPath = p.getProperty("inputFilePath");
				File file = new File(fileInputPath);
			    Scanner sc = new Scanner(file);
			    String parseStr = "";
			    while (sc.hasNextLine()) {
			    	parseStr = parseStr+sc.nextLine();
				} 
				
			    //Call to convert to CSV format
			    CSVMapperUtility csvUtil = new CSVMapperUtility();
			    csvUtil.generateCSV(parseStr);
			    
			    //Call to convert to XML format
			    XMLMapperUtility xmlUtil = new XMLMapperUtility();
			    xmlUtil.generateXML(parseStr);
	}
	
}
