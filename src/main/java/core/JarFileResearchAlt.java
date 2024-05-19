package core;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.jar.JarFile;

public class JarFileResearchAlt {

    public  JarFileResearchAlt() throws IOException {
        DataBase dataBase = new DataBase();
//        String jarFilePath = "C:\\Users\\worker\\IdeaProjects\\Anno3\\anno3.jar";
        String jarFilePath = "C:\\Users\\worker\\IdeaProjects\\javaLab\\JSON-java\\target\\json-20240303.jar";
        JarFile jarFile = new JarFile(jarFilePath);
        System.out.println("Elements in JAR file " + jarFilePath + ":");
        AtomicReference<String> dataBaseRow = new AtomicReference<>("");
        AtomicInteger classCount = new AtomicInteger();
        AtomicInteger classNotFoundCount = new AtomicInteger();
        AtomicInteger classDefFoundCount = new AtomicInteger();

        jarFile.stream().forEach(entry -> {
            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                dataBaseRow.set("");
                classCount.getAndIncrement();//classCount++
                String className = entry.getName().replace("/", ".").replace(".class", "");
                System.out.println("class:");
                System.out.println(className);

                Class<?> targetClass = null;
                try {
                    targetClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
                    System.out.println("ClassNotFoundException !!!");
                    classNotFoundCount.getAndIncrement();//classNotFoundCount++
                } catch (NoClassDefFoundError e) {
                    System.out.println("NoClassDefFoundError !!!");
                    classDefFoundCount.getAndIncrement();//classDefFoundCount++
                } catch (ExceptionInInitializerError e) {
                    System.out.println("ExceptionInInitializerError !!!");
                } catch (UnsupportedClassVersionError e) {
                    System.out.println("UnsupportedClassVersionError !!!");
                }



/* Methods */
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
                            System.out.println("input: " + pr[i].getName() + " type: " + pr[i].getType().getName());
                            input = input + pr[i].getType().getName() + ",";
                        }
                        System.out.println("output: " + method.getReturnType());
                        dataBaseRow.set(targetClass.getName() + ";" + method.getName() + ";" + input + ";" + method.getReturnType().getName());
                        System.out.println(dataBaseRow.get());
                        System.out.println();
                        String[] arr = dataBaseRow.get().split(";");
                        dataBase.sendData(arr[0],arr[1],arr[2],arr[3]);
                    }

/* Fields */
//                    Field[] fields = targetClass.getDeclaredFields();
//                    // Display the names and types of all fields
//                    System.out.println("fields of class " + targetClass.getSimpleName() + ":");
//                    for (Field field : fields) {
//                        System.out.println("name: " + field.getName() + ", type: " + field.getType().getSimpleName());
//                    }
//                    System.out.println();
                }
                catch (NoClassDefFoundError e) {
                    System.out.println("NoClassDefFoundError !!!");
                }
                catch (NullPointerException e) {
                    System.out.println("NullPointerException !!!");
                }

            }
        });

        jarFile.close();
        System.out.println("classCount: " + classCount + " classNotFoundCount: " + classNotFoundCount + " classDefFoundCount: " + classDefFoundCount);
dataBase.closeConnection();
    }
}
