package Parse;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import com.opencsv.CSVWriter;


public class CSVMapperUtility {

	public void generateCSV(String parseStr) throws IOException {
		
		FileReader reader=new FileReader("FilePath.properties");
		Properties p=new Properties();  
		p.load(reader);
		
		//CSV output path read from properties file
		String csvOutputPath = p.getProperty("csvOutputPath");
		
		CSVWriter writer = new CSVWriter(new FileWriter(csvOutputPath),',',
                CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
		
		//comparator to sort String array in alphabetical order
		Comparator<String> alphabeticalSort =  (String o1, String o2) -> o1.toLowerCase().compareTo(o2.toLowerCase());
		
		parseStr = parseStr.replaceAll(",", "").replaceAll("-", "").replaceAll("[?]", "").replaceAll("[(]", "").replaceAll("[)]", "");
		
		String[] sentenceArr = parseStr.split("[.]");
		List<String[]> wordArrList = new ArrayList<String[]>();
		int maximumWordCount = 0;
		
		for(String sentence : sentenceArr) {
			
			String[] wordArr =  sentence.trim().split("\\s+");
			//Sorting words using comparator 
			Arrays.sort(wordArr, alphabeticalSort);
			wordArrList.add(wordArr);
			//logic to find maximum word count
			if(maximumWordCount < wordArr.length) {
				maximumWordCount = wordArr.length;
			}
		}
		
		//maximum word count used to write header for csv
		String[] wordHeader = new String[maximumWordCount];
		for(int cnt=0; cnt<maximumWordCount; cnt++) {
			wordHeader[cnt] = "word"+(cnt+1);
		}
		writer.writeNext(wordHeader); //writing csv headers
		writer.writeAll(wordArrList); //writing csv word data
		writer.flush();
		 
	}
}
