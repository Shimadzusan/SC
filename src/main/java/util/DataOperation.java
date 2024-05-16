package util;

import java.io.*;

public class DataOperation {
	
	public String readDataFromFile(String fileName) throws FileNotFoundException, IOException {
		String result = "";
		String line;
	        // defaultCharBufferSize = 8192; or 8k
	        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	            
	            while ((line = br.readLine()) != null) {
	               result = result + line + "\n";
	            }
	        }
		return result;
	}
	
	public boolean writeDataToFile(String fileName, String text) throws IOException {
//		try (FileWriter fw = new FileWriter(fileName);
//			       BufferedWriter bw = new BufferedWriter(fw)) {
//			      bw.write(text);
//			      bw.newLine(); // add new line, System.lineSeparator()
//			  }

				 // append mode
			  try (FileWriter fw = new FileWriter(fileName, false);
			       BufferedWriter bw = new BufferedWriter(fw)) {
			      bw.write(text);
			      bw.newLine();
			  }
		return true;
	}

}
