import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Exemple {

	public static void main(String[] args) throws IOException {
		String s = "class}{";
		byte[] ar4 = s.getBytes();
		
		for(int j = 0; j < ar4.length; j++){
			System.out.println(ar4[j]);
		}
		
		byte[] ar1 = {104, 101, 108, 32, 47, 108, 111, 32};
		byte[] ar2 = {106, 97, 32, 118, 97};
		byte[] ar22 = {106, 97, 32, 118, 97};
			if(Arrays.equals(ar2, ar22))System.out.println("URRAA!!");
		
		byte[] ar3 = new byte[1];
		
		ArrayList <byte[]> list = new ArrayList<byte[]>();
		
			list.add(ar1);
			list.add(ar2);
			String s2 = "";
				for(int i = 0; i < list.size(); i++){
					ar3 = list.get(i);
					
						for(int k = 0; k < ar3.length; k++){
							System.out.print(ar3[k] + " ");
							s2 = new String(ar3);
						}
						//System.out.println();
						System.out.println(s2);
					
				}
//===================================================================================
	//Method byte_split();
//input byte_array, and split_symbol
//output List<byte[]>
	
				Alpha essence = new Alpha();
				byte[] ar5 = essence.concat_array(ar1, ar2);
					
					for(int k = 0; k < ar5.length; k++){
						System.out.print(ar5[k] + " ");
					}
		
	ArrayList <byte[]> super_list = new ArrayList<byte[]>();
	ArrayList <Byte> byte_list = new ArrayList<Byte>();
	byte[] ar10 = new byte[ar5.length + 1];
	ar10[ar10.length - 1] = 32;
	for(int n = 0; n < ar5.length; n++){
		ar10[n] = ar5[n];
	}
	
	for(int k = 0; k < ar10.length; k++){
		if(ar10[k] != 32){
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
		System.out.println();
		for(int l = 0; l < ar7.length; l++){
			System.out.print(ar7[l] + " ");
			
		}
	}
	System.out.println();
		System.out.println(super_list.size());
//=================================================================================
		
			

	}

}
