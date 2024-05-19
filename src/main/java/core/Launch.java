package core;
import util.DataOperation;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicReference;

public class Launch {
    /*
     * Project SC it is from Scan Code
     */
    public static void main(String[] args) throws IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, ClassNotFoundException {
//        new Research();
        java_io();
//        new PackageResearch();
//        new JarFileResearchAlt();
//        DataOperation dataOperation = new DataOperation();
//        String s = dataOperation.readDataFromFile("input.txt");
////        System.out.println(s);
//        String[] ss = s.split(",");
//        HashSet<String> set = new HashSet<>();
//        for(String row : ss) {
//            set.add(row);
//        }
//        for(String text : set) {
//            System.out.println(text);
//        }

        //        new core.HammaReflect();
//        new StreamDetector();
        //new Betta("C:\\Users\\user\\point_one");
        //new LiteCodeAnalysis("C:\\Users\\tokug\\Documents\\Anno\\Anno-master\\src\\core");
    }
    public  static void java_io() throws ClassNotFoundException, IOException {
        DataOperation dataOperation = new DataOperation();
        AtomicReference<String> dataBaseRow = new AtomicReference<>("");
        DataBase dataBase = new DataBase();
        String[] arr = (dataOperation.readDataFromFile("java_io_list.txt")).split("\n");
        for (String s : arr) {
            String className = "java.io." + s;
            dataBaseRow.set("");
            Class<?> targetClass = Class.forName(className);

            try {
                Method[] methods = targetClass.getDeclaredMethods();
                // Display the names of all methods
                System.out.println("methods of class " + targetClass.getSimpleName() + "(count: " + methods.length + "):");
                for (Method method : methods) {
                    System.out.println(method);
                    System.out.println(method.getName());
                    Parameter[] pr = method.getParameters();
                    String input = "";
                    for (int i = 0; i < pr.length; i++) {
                        System.out.println("input: " + pr[i].getName() + " type: " + pr[i].getType().getCanonicalName());
                        input = input + pr[i].getType().getName() + ",";
                    }
                    System.out.println("output: " + method.getReturnType().getCanonicalName());
                    dataBaseRow.set(targetClass.getName() + ";" + method.getName() + ";" + input + ";" + method.getReturnType().getName());
                    //System.out.println(dataBaseRow.get());
                    System.out.println();
                    String[] arrr = dataBaseRow.get().split(";");
                    dataBase.sendData(arrr[0],arrr[1],arrr[2],arrr[3]);
                }} catch (SecurityException e) {
                throw new RuntimeException(e);
            }
        }
    }
}