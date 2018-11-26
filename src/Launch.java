import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.Scanner;

public class Launch {
/*
 * Project SC it is from Scann Code
 */
	public static void main(String[] args) throws IOException {
		new Alpha();

	}

}

class Alpha {
//======BLOCK VARIABLES========	
	int value_of_symbols = 0;
	int value_of_asserts = 0;
	int value_of_blocks = 0;
	int value_of_imports = 0;
	int value_of_classes = 0;
	int value_of_files = 0;
	int value_of_interfaces = 0;
	
	String aggregate_text = "";
	String patch = "C:\\Users\\Stalin\\workspace\\SC\\src";
	//String patch = "C:\\Users\\Stalin\\Desktop\\java_text";
	//C:\Users\Stalin\workspace\Weapon\src
//=============================
	
	Alpha() throws IOException{
//==========INTERFACE==========
		//super_method(1);
		String s = get_total_text(patch);
		write();
		text();
		System.out.println("...scann code started");
		System.out.println();
		System.out.println("количество символов:  " + value_of_symbols);
		System.out.println("количество выражений: " + value_of_asserts);
		System.out.println("количество блоков: " + value_of_blocks);
		System.out.println("количество import(s): " + value_of_imports);
		System.out.println("количество classe(s): " + value_of_classes);
		System.out.println("количество interface(s): " + value_of_interfaces);
		System.out.println("количество file(s):   " + value_of_files);
		System.out.println();
		System.out.println("...scann code complete");
		System.out.println("...program end.");
//======END_INTERFACE==========
		
		/*
		 * Исходные данные: некоторое множество файлов
		 * Конечный результат: кол-во import and class
		 * 
		 * Solution:
		 * Определяем источник данных, в данном случае это некоторая директория
		 * получаем список всех файлов, это естественно массив
		 * получаем общий текст из всех файлов
		 * 
		 * анализируем текст
		 * то есть ищем: импорты, классы, выражения и т.д.
		 * выводим результат и всё) задача выполнена
		 */
	}
	
//public int super_method(int value){
//		File folder = new File("C:\\Users\\Stalin\\Desktop\\java_text");
//		//C:\Users\Stalin\Desktop\java_text
//		File[] listOfFiles = folder.listFiles();
////folder.createNewFile();
//		    for (int i = 0; i < listOfFiles.length; i++) {
//		      if (listOfFiles[i].isFile()) {
//		        System.out.println(listOfFiles[i].getName());
//		      } else if (listOfFiles[i].isDirectory()) {
//		        System.out.println("Directory " + listOfFiles[i].getName());
//		      }
//		    }
//		value_of_files = listOfFiles.length;
//return 10;
//}

public String get_total_text(String patch) throws IOException{
	byte[] last_array = new byte[1];
	
	File folder = new File(patch);
		File[] listOfFiles = folder.listFiles();
		String[] file_name = new String[listOfFiles.length];
			
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
		        //System.out.println(listOfFiles[i].getName());
		        file_name[i] = listOfFiles[i].getName();
		      }
			}
//PART II
	
			for(int i = 0; i < file_name.length; i++){
				String way = file_name[i];
				
//===============================
				String source = patch + "\\" + way;

				FileInputStream inputStream = new FileInputStream(source);
				Reader inputStreamReader = new InputStreamReader(inputStream);
				//(3)	
				
//BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
				BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		        String inputLine;
		        StringBuffer response = new StringBuffer();

		        while ((inputLine = in.readLine()) != null) {
		            response.append(inputLine);
		        }
		        in.close();

		        //print result
//		        System.out.println("You patch: " + source);
//		        System.out.println();
//		        System.out.println("You patch: " + response.toString());
		        
		        aggregate_text += response.toString();
//======================================================================================
				File main_text = new File(source);
				byte[] byte_array = new byte[(int) main_text.length()];
				
			FileInputStream inputStream2 = new FileInputStream(source);
		inputStream2.read(byte_array);
		last_array = concat_array(last_array, byte_array);
		
		
		
		
//======================================================================================
				

				
			}
			int line = 0;
			for(int i = 0; i < last_array.length; i++){
				//System.out.println(byte_array[i]);
				if(last_array[i] == 10){
					line++;
				}
			}
			System.out.println("total rows: " + line);
			value_of_files = file_name.length;
	return "";
}

public void write() throws FileNotFoundException{

File main_text = new File("C:\\Users\\Stalin\\workspace\\SC\\main_super_text.txt");
Scanner scan_main_text = null;

PrintWriter pww7 = null;

try {
pww7 = new PrintWriter(main_text);
} 
catch (FileNotFoundException e) {
e.printStackTrace();
}
//-----------------------------------------------------------------------------------------------//

String j = aggregate_text;
pww7.println(j);
pww7.close();
	
}

public void text(){
	char[] all_symbols = aggregate_text.toCharArray();
	value_of_symbols = all_symbols.length;
		for(int i = 0; i < all_symbols.length; i++){
			if(all_symbols[i] == ';')value_of_asserts++;
			if(all_symbols[i] == '{')value_of_blocks++;
		}
		String[] list = aggregate_text.split(";");
		String my_assert = "";
			for(int i = 0; i < list.length; i++){
				my_assert = list[i];
				
					String[] words_from_assert = my_assert.split(" ");
					for(int k = 0; k < words_from_assert.length; k++){
						
						if(words_from_assert[k].equals("import")){
							value_of_imports++;
						}
						
						if(words_from_assert[k].equals("class")){
							value_of_classes++;
						}
						
						if(words_from_assert[k].equals("interface")){
							value_of_interfaces++;
						}
					}
				
			}
}

public static byte[] concat_array(byte[] array_one, byte[] array_two){
	byte[] last_array = new byte[array_one.length + array_two.length];
	
	for(int i = 0; i < array_one.length; i++){
		last_array[i] = array_one[i];
	}
	int len = 0;
	for(int i = array_one.length; i < last_array.length; i++){
		last_array[i] = array_two[len];
		len++;
	}
		
		
	return last_array;
	
}
	
}
//привет
