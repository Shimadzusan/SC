package core;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Betta{
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
	String address = "";
	
	String aggregate_text = "";

	public Betta() {
		
	}
	
	public Betta(String address) throws IOException {
		this.address = address;
		define_general_byte_array();
		
		byte b = 10;//так как 10 это конец строки
		rows_of_bytes = splitByteArray(general_byte_array, b);
		
		deleteComments();
//==========INTERFACE==========
				System.out.println("...scann code started");
				System.out.println("..modul betta");
				System.out.println();
				System.out.println("количество байт    :     " + general_byte_array.length);
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
				getNameOfClass();
//======END_INTERFACE==========
	}
		
	public void define_general_byte_array() throws IOException{
		LiteCodeAnalysis instance = new LiteCodeAnalysis();
		instance.setPath(address);
		instance.initGeneralArray(instance.getListNames(address));
		
		general_byte_array = instance.last_array;
	}

	/**
	 * Метод splitByteArray работает по принципу String.split
	 * @param array
	 * @param split_value
	 * @return
	 */
	public ArrayList<byte[]> splitByteArray(byte[] array, byte split_value) {
		ArrayList <byte[]> super_list = new ArrayList<byte[]>();
		ArrayList <Byte> byte_list = new ArrayList<Byte>();		//.. byte_list в итоге должен быть преобразован
										//..в обычный байтовый массив и добавлен в super_list
		
		byte[] op_array = new byte[array.length + 1];		//..op_array is operational_array
		op_array[op_array.length - 1] = split_value;		//..выражение только для того чтобы считалась последняя строка
		for(int i = 0; i < array.length; i++) {
			op_array[i] = array[i];
		}							//..end
		
		for(int i = 0; i < op_array.length; i++) {
			if(op_array[i] != split_value)byte_list.add(op_array[i]);
			
			else {
				byte[] for_sl = new byte[byte_list.size()];			//..for variable: super_list
					for(int j = 0; j < for_sl.length; j++) {
						for_sl[j] = byte_list.get(j);			//.. simple convertation
					}							//..FROM byte_list TO for_sl
				byte_list.removeAll(byte_list);					//..clearing for next operations
				super_list.add(for_sl);						//..finish operation
			}
		}
		return super_list;
	}

	/**
	 * Метод deleteComments удаляет все двойные последовательности байтовых кодов "47"
	 * т.е. комемнтарии обозначенные как "//"
	 * а так же считает количество таких последовательностей
	 */
	public void deleteComments() {
		ArrayList<byte[]> list = new ArrayList<byte[]>(rows_of_bytes);
		byte[] array = new byte[0];
		boolean flag = false;
		int count = 0;
		int count_list = 0;
		
		for(int i = 0; i < list.size(); i++) {
			array = list.get(i);
			count_list++;
				
			for(int k = 0; k < array.length - 1; k++) {
				if(array[k] == 47 && array[k + 1] == 47) {		//..здесь происходит поиск "//" "47-47"
					list.remove(i);
					flag = true;
					i--;						//..уменьшаем счетчик т.к. имеется удаление
					count++;
					break;
				}	
			}
				
			if(flag == false)rows_of_bytes_without_comments.add(array);
			flag = false;
		}
		System.out.println("count rows with comment: " + count + " count_list: " + count_list);
	}
	
	/**
	 * Метод getNameOfClass извлекает из байтовых массивов имена классов,
	 * по маркеру "class"
	 */
	public void getNameOfClass(){
		ArrayList<byte[]> list = new ArrayList<byte[]>(rows_of_bytes_without_comments);
		ArrayList<byte[]> list_names = new ArrayList<byte[]>();	
		
		byte[] array = new byte[0];
			for(int i = 0; i < list.size(); i++){
				array = list.get(i);			
					for(int k = 0; k < array.length; k++) {		//..extracting upon marker "class"	
						if(array[k] == 99 && array[k + 1] == 108 && array[k + 2] == 97 && array[k + 3] == 115 && 
						array[k + 4] == 115 && array[k + 5] == 32) {
						list_names.add(array);
						break;
						}
					}
			}
			
		String names = "";							//..displays names of classes
			for(int i = 0; i < list_names.size(); i++) {
				names = new String(list_names.get(i));		
				byte[] probe = list_names.get(i);
				System.out.println((i + 1) + ". " + "\t" + get_class_size(probe) + "\t" +  names);			
			}
	}
	
	/**
	 * Ищем совпадение строки
	 * далее активация буллевой переменной
	 * далее считаем фигурные скобки
	 * public class+Launch+{
	 * РАБОТАЕТ НЕ ПРАВИЛЬНО
	 */
	public String get_class_size(byte[] probe) {		
		ArrayList<byte[]> list = new ArrayList<byte[]>(rows_of_bytes);
		ArrayList<byte[]> list_names = new ArrayList<byte[]>();
		String type_return = " ";
		int rows = 0;
		int bytes = 0;
		byte[] array = new byte[0];
		boolean flag = false;	
			for(int i = 0; i < list.size(); i++) {
				array = list.get(i);
				int byte_size = 0;	
					if(Arrays.equals(probe, array)) {
						flag = true;
//считаем фигурные скобки
						int bracket_count = 0;
						byte_size = array.length;		
						int k = 0;
						byte[] array_two = new byte[0];
				
							for(k = i + 1; k < list.size(); k++) {					//..cycle for searshing
								array_two = list.get(k);					//.."125} -1"
								byte_size += array_two.length;					//.."123{ +1"
									for(int l = 0; l < array_two.length; l++) {
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
