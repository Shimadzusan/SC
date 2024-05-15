package core;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HammaReflect {
	HammaReflect() throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Betta betta = new Betta();
		Method[] mtd =	betta.getClass().getDeclaredMethods();
		for (int i = 0; i < mtd.length; i++) {
			System.out.println(mtd[i]);
		}
		
		System.out.println("======");
		Field[] field = betta.getClass().getDeclaredFields();
		System.out.println(field.length);
		for (int i = 0; i < field.length; i++) {
			System.out.println(field[i]);
		}
		
		System.out.println("++++");
//		ClassLoader p = ClassLoader.getSystemClassLoader();
//		Package[] pk = Package.getPackages();
//		
//		for (int i = 0; i < pk.length; i++) {
//			System.out.println(pk[i].getName());
//			
//		}

	}

}
