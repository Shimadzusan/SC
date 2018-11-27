import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Launch {
/*
 * Project SC it is from Scann Code
 */
	public static void main(String[] args) throws IOException {
		new Alpha();
		new Betta();

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
	int value_of_lines = 0;
	
	byte[] last_array = new byte[0];
	
	
	String aggregate_text = "";
	//String patch = "C:\\Users\\Stalin\\workspace\\SC\\src";
	String patch = "C:\\Users\\Stalin\\Desktop\\java_text";
	//C:\Users\Stalin\workspace\Weapon\src
//=============================
	
	Alpha() throws IOException{
//==========INTERFACE==========
		String s = get_total_text(patch);
		write();
		text();
		
		System.out.println("...scann code started");
		System.out.println("..modul alpha");
		System.out.println();
		System.out.println("количество символов:     " + value_of_symbols);
		System.out.println("количество строк:        " + value_of_lines);
		System.out.println("количество выражений:    " + value_of_asserts);
		System.out.println("количество блоков:       " + value_of_blocks);
		System.out.println("количество import(s):    " + value_of_imports);
		System.out.println("количество classe(s):    " + value_of_classes);
		System.out.println("количество interface(s): " + value_of_interfaces);
		System.out.println("количество file(s):      " + value_of_files);
		System.out.println();
		System.out.println("..alpha end");
		System.out.println("...scann code complete");
		System.out.println("...program end.");
		System.out.println("====================================================");
		System.out.println();
//======END_INTERFACE==========
		
		/*
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
	}

public String get_total_text(String patch) throws IOException{
	/*
	 * Метод get_total_text() работает следующим образом:
	 * извлекает в массив имена всех файлов целевой директории
	 * 
	 */
	
	
//PART I
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
			String sub_patch = file_name[i];
				String source = patch + "\\" + sub_patch;
//===block for read symbols==================================================
					FileInputStream inputStream = new FileInputStream(source);
						Reader inputStreamReader = new InputStreamReader(inputStream);
							BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
		        
				String inputLine;
		        StringBuffer response = new StringBuffer();

		        while ((inputLine = in.readLine()) != null) {
		            response.append(inputLine);
		        }
		        in.close();

		        aggregate_text += response.toString();
//===block for read bytes===================================================
				File main_text = new File(source);
					byte[] byte_array = new byte[(int) main_text.length()];
				
			FileInputStream inputStream2 = new FileInputStream(source);
				inputStream2.read(byte_array);
					last_array = concat_array(last_array, byte_array);
					
			}
//считаем строки по байтовому коду 10
	int line = 1; //так как не считает последнюю строку
		for(int i = 0; i < last_array.length; i++){
			if(last_array[i] == 10){
					line++;
			}
		}
			value_of_lines = line;
			value_of_files = file_name.length;
			
//general_byte_array = last_array;
	return "";
}

public void write() throws FileNotFoundException{
	/*
	 * Метод работает с текстом на символьном уровне, все символы прочитвнные из файлов
	 * объединяет в один файл
	 */

	File main_text = new File("C:\\Users\\Stalin\\workspace\\SC\\main_super_text.txt");
		Scanner scan_main_text = null;
			PrintWriter writer = null;
	
				try{
					writer = new PrintWriter(main_text);
				} 
				catch (FileNotFoundException e) {
				e.printStackTrace();
				}

					String text = aggregate_text;
						writer.println(text);
							writer.close();
							
}

public void text(){
	/*
	 * Метод работает с текстом на символьном уровне
	 * для расчета количества блоков и утверждений из общего текста
	 * извлекает символы: ";" and "{"
	 * а так же считает количество: imports, classes, interfaces 
	 */
	
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
	/*
	 * Метод сложения байтовых массивов
	 */
	
	int half_length = 0;
	byte[] last_array = new byte[array_one.length + array_two.length];
	
		for(int i = 0; i < array_one.length; i++){
			last_array[i] = array_one[i];
		}
	
			for(int i = array_one.length; i < last_array.length; i++){
				last_array[i] = array_two[half_length];
				half_length++;
			}
		
	return last_array;
}
	
}
 
class Betta{
//======BLOCK VARIABLES========	
		int value_of_symbols = 0;
		int value_of_asserts = 0;
		int value_of_blocks = 0;
		int value_of_imports = 0;
		int value_of_classes = 0;
		int value_of_files = 0;
		int value_of_interfaces = 0;
		int value_of_lines = 0;
		
		byte[] general_byte_array = new byte[0];
		int value_of_bytes = 0;
		ArrayList<byte[]> rows_of_bytes = new ArrayList<byte[]>();
		ArrayList<byte[]> rows_of_bytes_without_comments = new ArrayList<byte[]>();
		
		String aggregate_text = "";
		String patch = "C:\\Users\\Stalin\\workspace\\SC\\src";
		//String patch = "C:\\Users\\Stalin\\Desktop\\java_text";
		//C:\Users\Stalin\workspace\Weapon\src
//=============================
  
	  Betta() throws IOException{
//==========INTERFACE==========
		  define_general_byte_array();
		  define_rows_of_bytes();
		 
		  delete_comments();
//				String s = get_total_text(patch);
//				write();
//				text();
				
				System.out.println("...scann code started");
				System.out.println("..modul betta");
				System.out.println();
				System.out.println("количество байт    :     " + value_of_bytes);
				System.out.println("количество символов:     " + value_of_symbols);
				System.out.println("количество строк:        " + rows_of_bytes.size());
				System.out.println("кол-во строк с комент. : " + (rows_of_bytes.size() - rows_of_bytes_without_comments.size()));
				System.out.println("количество выражений:    " + value_of_asserts);
				System.out.println("количество блоков:       " + value_of_blocks);
				System.out.println("количество import(s):    " + value_of_imports);
				System.out.println("количество classe(s):    " + value_of_classes);
				System.out.println("количество interface(s): " + value_of_interfaces);
				System.out.println("количество file(s):      " + value_of_files);
				System.out.println();
				System.out.println("..betta end");
				System.out.println("...scann code complete");
				System.out.println("...program end.");
				System.out.println("====================================================");
				System.out.println();
				get_name_of_class();
				 //get_class_size();
				//System.out.println(rows_of_bytes.size());
				//System.out.println(rows_of_bytes_without_comments.size());
//======END_INTERFACE==========
	  }
		
public void define_general_byte_array() throws IOException{
	Alpha instance = new Alpha();
		  
		general_byte_array = instance.last_array;
			value_of_bytes = general_byte_array.length;
			
/*
 * 
 */
}

public void define_rows_of_bytes(){
	byte row = 10;
	rows_of_bytes = split_byte_array(general_byte_array, row);
	

}

public ArrayList<byte[]> split_byte_array(byte[] array, byte byte_value){
	//===================================================================================
	//Method byte_split();
//input byte_array, and split_symbol
//output List<byte[]>
	
//				Alpha essence = new Alpha();
//				byte[] ar5 = essence.concat_array(ar1, ar2);
//					
//					for(int k = 0; k < ar5.length; k++){
//						System.out.print(ar5[k] + " ");
//					}
		
	ArrayList <byte[]> super_list = new ArrayList<byte[]>();
	ArrayList <Byte> byte_list = new ArrayList<Byte>();
	byte[] ar10 = new byte[array.length + 1];
	ar10[ar10.length - 1] = byte_value;
	for(int n = 0; n < array.length; n++){
		ar10[n] = array[n];
	}
	
	for(int k = 0; k < ar10.length; k++){
		if(ar10[k] != byte_value){
			byte_list.add(ar10[k]);
			
		}
		else{
			//1
			byte[] ar6 = new byte[byte_list.size()];
			for(int j = 0; j < ar6.length; j++){
				ar6[j] = byte_list.get(j);
			}
			//2
			byte_list.removeAll(byte_list);
			//3
			super_list.add(ar6);
		}
	}
	byte[] ar7 = new byte[0];
	for(int k = 0; k < super_list.size(); k++){
		ar7 = super_list.get(k);
		//System.out.println();
		for(int l = 0; l < ar7.length; l++){
			//System.out.print(ar7[l] + " ");
			
		}
	}
	//System.out.println();
		//System.out.println(super_list.size());
//=================================================================================	
	return super_list;
}
/*
 * 
 */
public void delete_comments(){
	
	ArrayList<byte[]> list = new ArrayList<byte[]>(rows_of_bytes);
	//list = rows_of_bytes;
	//System.out.println(rows_of_bytes.size());
	
	byte symbol = 47; 
	//code 47  = "/"
	byte[] array = new byte[0];
	boolean flag = false;
	int count = 0;
	int count_list = 0;
	
		for(int i = 0; i < list.size(); i++){
			array = list.get(i);
			count_list++;
				for(int k = 0; k < array.length - 1; k++){
					if(array[k] == 47 && array[k + 1] == 47){
						list.remove(i);
						//i = i - 2;
						flag = true;
						i--;
						count++;
						break;
					}
					
//					else{
//						rows_of_bytes_without_comments.add(array);
//					}

					
				}
				if(flag == false)rows_of_bytes_without_comments.add(array);
				flag = false;
				//System.out.println(rows_of_bytes.size());
			
		}
		System.out.println("count: " + count + " count_list: " + count_list);
	//rows_of_bytes_without_comments = null;
}
public void get_name_of_class(){
	ArrayList<byte[]> list = new ArrayList<byte[]>(rows_of_bytes_without_comments);
	ArrayList<byte[]> list_names = new ArrayList<byte[]>();	
	
	//we search "class"
		byte[] array = new byte[0];
		boolean flag = false;
//		int count = 0;
//		int count_list = 0;
		
			for(int i = 0; i < list.size(); i++){
				array = list.get(i);
			
					for(int k = 0; k < array.length; k++){
						
						if(array[k] == 99 && array[k + 1] == 108 && array[k + 2] == 97 && array[k + 3] == 115 && array[k + 4] == 115 && array[k + 5] == 32){
						
							list_names.add(array);
							
							break;
						}
					
					}
					
			}
			
			String names = "";
			for(int i = 0; i < list_names.size(); i++){
				names = new String(list_names.get(i));
					
					byte[] probe = list_names.get(i);
					System.out.println((i + 1) + ". " +  names + get_class_size(probe));
					
			}
		
		
}
public String get_class_size(byte[] probe){
	/*
	 * Ищем совпадение строки
	 * далее активация буллевой переменной
	 * далее считаем фигурные скобки
	 * public class+Launch+{
	 */
	
		ArrayList<byte[]> list = new ArrayList<byte[]>(rows_of_bytes);
		ArrayList<byte[]> list_names = new ArrayList<byte[]>();
			String type_return = " ";
			int rows = 0;
			int bytes = 0;
				byte[] array = new byte[0];
					boolean flag = false;
				
			for(int i = 0; i < list.size(); i++){
				array = list.get(i);
				int byte_size = 0;
					
						if(Arrays.equals(probe, array)){
							flag = true;
//считаем фигурные скобки
							//System.out.println("!!!");
							int bracket_count = 0;
							//int byte_size = 0;
							byte_size = array.length;
							
							int k = 0;
							byte[] array_two = new byte[0];
								for(k = i + 1; k < list.size(); k++){
									//125} -1
									//123{ +1
									array_two = list.get(k);
									byte_size += array_two.length;
									for(int l = 0; l < array_two.length; l++){
										if(array_two[l] == 125)bracket_count--;
										if(array_two[l] == 123)bracket_count++;
										
										
									}
									
									if(bracket_count < 0)break;
								}
							//System.out.println("rows: " + (k - i));
							rows = k - i;
							
								//System.out.println("bytes: " + byte_size);
								bytes = byte_size;
								i = k;
								
								
						}
				
				
				
			}
	return type_return + rows + " " + bytes;		
}
	  }
//привет