package core;

import java.io.*;
import java.util.ArrayList;

public class LiteCodeAnalysis {	
	int value_of_symbols = 0;
	int value_of_asserts = 0;
	int value_of_blocks = 0;
	int value_of_imports = 0;
	int value_of_classes = 0;
	int value_of_files = 0;
	int value_of_interfaces = 0;
	int value_of_lines = 0;
	
	byte[] last_array = new byte[0];
	String aggregate_text = "";
	String path;
	
	public LiteCodeAnalysis() {
		
	}
	
	/**
	 * Исходные данные: некоторое множество файлов
	 * Конечный результат: кол-во imports and class
	 * 
	 * Solution:
	 * Version One, работаем с символами
	 * Определяем источник данных, в данном случае это некоторая директория
	 * получаем список всех файлов, это естественно массив
	 * получаем общий текст из всех файлов
	 * 
	 * анализируем текст
	 * то есть ищем: импорты, классы, выражения и т.д.
	 * выводим результат и всё) задача выполнена
	 * 
	 * Version Two, работаем с байтами
	 * 
	 */
	public LiteCodeAnalysis(String address) throws IOException {
		this.path = address;
		ArrayList<String> listNames = getListNames(path);
		initAggregateText(listNames);
		initGeneralArray(listNames);
		getOtherParametrs();
//==========INTERFACE==========
		System.out.println("...scann code started");
		System.out.println("..modul alpha");
		System.out.println("");
		System.out.println("quantity symbols:     " + value_of_symbols);
		System.out.println("quantity rows:        " + getRows(last_array));
		System.out.println("quantity asserts:    " + value_of_asserts);
		System.out.println("quantity blocks(?):       " + value_of_blocks);
		System.out.println("quantity import(s):    " + value_of_imports);
		System.out.println("quantity classe(s):    " + value_of_classes);
		System.out.println("quantity interface(s): " + value_of_interfaces);
		System.out.println("quantity file(s):      " + listNames.size());
		System.out.println();
		System.out.println("..alpha end");
		System.out.println("...scann code complete");
		System.out.println("...program end.");
		System.out.println("====================================================");
		//System.out.println(aggregate_text);
//======END_INTERFACE==========
	}
	
	/** Метод getListNames принимает на вход адрес по которому расположен материал для анализа,
	 * и формирует на выходе список имен файлов */
	public ArrayList<String> getListNames(String address) {
		File folder = new File(address);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> listNames = new ArrayList<String>();

		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile())listNames.add(listOfFiles[i].getName());				
		}
		return listNames;
	}
	
	/**
	 * Метод initAggregateText принимает на вход список имен файлов,
	 * и формирует на выходе файл aggregate_text,
	 * тип String в котором содержится весь код из списка имен файлов  
	 */
	public void initAggregateText(ArrayList<String> listNames) throws IOException {
		for(int i = 0; i < listNames.size(); i++) {
			String sub_path = listNames.get(i);
			String source = path + "\\" + sub_path;

			FileInputStream inputStream = new FileInputStream(source);
			BufferedReader bufferForSymbols = new BufferedReader(new InputStreamReader(inputStream));
					
			String inputLine;
	        StringBuffer response = new StringBuffer();
		        while ((inputLine = bufferForSymbols.readLine()) != null) {
		            response.append(inputLine + "\n");
		        }
		    bufferForSymbols.close();
		    aggregate_text += response.toString() + "\n";
		 }
	}
	
	/**
	 * Метод initGeneralArray принимает на вход список имен файлов,
	 * и формирует на выходе файл last_array,
	 * тип байтовый массив, в котором содержатся все байты кода из списка имен файлов  
	 */
	public void initGeneralArray(ArrayList<String> listNames) throws IOException {
		for(int i = 0; i < listNames.size(); i++) {
			String sub_path = listNames.get(i);
			String source = path + "\\" + sub_path;

			File main_text = new File(source);
			byte[] byte_array = new byte[(int) main_text.length()];
			FileInputStream inputStream = new FileInputStream(source);
			inputStream.read(byte_array);
			inputStream.close();
			last_array = concatArray(last_array, byte_array);
		}		
	}
	

	/**
	 * Метод getRows возвращает количество строк в анализируемом коде,
	 * строки считаются по байтовому коду 10
	 */
	public int getRows(byte[] array) {
		int rows = 1; //так как не считает последнюю строку
		for(int i = 0; i < array.length; i++) {
			if(array[i] == 10)rows++;
		}
		return rows;
	}

	/**
	 * Метод работает с текстом на символьном уровне
	 * для расчета количества блоков и утверждений из общего текста
	 * извлекает символы: ";" and "{"
	 * а так же считает количество: imports, classes, interfaces 
	 */
	public void getOtherParametrs() {
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
					if(words_from_assert[k].equals("import"))value_of_imports++;
					if(words_from_assert[k].equals("class"))value_of_classes++;
					if(words_from_assert[k].equals("interface"))value_of_interfaces++;
				}
			}
	}

	/**
	 * Метод сложения байтовых массивов
	 */
	public static byte[] concatArray(byte[] array_one, byte[] array_two){
		int half_length = 0;
		byte[] result_array = new byte[array_one.length + array_two.length];
			for(int i = 0; i < array_one.length; i++) {
				result_array[i] = array_one[i];
			}
			for(int i = array_one.length; i < result_array.length; i++) {
				result_array[i] = array_two[half_length];
				half_length++;
			}
		return result_array;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String patch) {
		this.path = patch;
	}
}
