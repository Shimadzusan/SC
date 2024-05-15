package core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileResearch {
    public static void main(String[] args) {
        String jarFilePath = "C:\\Users\\worker\\IdeaProjects\\javaLab\\JSON-java\\target\\json-20240303.jar"; // Specify the path to the JAR file here
        //String jarFilePath = "C:\\Users\\worker\\.m2\\repository\\org\\springframework\\spring-core\\6.1.3\\spring-core-6.1.3.jar";
//        String jarFilePath = "C:\\Users\\worker\\.m2\\repository\\com\\google\\code\\gson\\gson\\2.8.5\\gson-2.8.5.jar";
        try {
            displayJarElements(jarFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display all elements (classes) in a JAR file
    private static void displayJarElements(String jarFilePath) throws IOException {
        JarFile jarFile = new JarFile(jarFilePath);
        System.out.println("Elements in JAR file " + jarFilePath + ":");

        jarFile.stream().forEach(entry -> {
            if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
                String className = entry.getName().replace("/", ".").replace(".class", "");
                System.out.println(className);
//                String s = "org.json.Cookie";
                Class<?> targetClass = null;
                try {
                    targetClass = Class.forName(className);
                } catch (ClassNotFoundException e) {
//                    throw new RuntimeException(e);
                    System.out.println("ClassNotFoundException !!!");
                } catch (NoClassDefFoundError e) {
                    System.out.println("NoClassDefFoundError !!!");
                } catch (ExceptionInInitializerError e) {
                    System.out.println("ExceptionInInitializerError !!!");
                } catch (UnsupportedClassVersionError e) {
                    System.out.println("UnsupportedClassVersionError !!!");
                }

                try {
                    Method[] methods = targetClass.getDeclaredMethods();

                    // Display the names of all methods
                    System.out.println("methods of class " + targetClass.getSimpleName() + "(count: " + methods.length + "):");
                    for (Method method : methods) {
                        System.out.println(method.getName());
//                        System.out.println("inputParameterCount: " + method.getParameterCount());
                        Parameter[] pr = method.getParameters();
                        for (int i = 0; i < pr.length; i++) {
                            System.out.println("nameMethodsVar: " + pr[i].getName() + " type: " + pr[i].getType().getName());
                        }
                        System.out.println("output --- " + method.getReturnType());
                        System.out.println();
//                        System.out.println("nameMethodsVar: " + pr[0].getName() + " type: " + pr[0].getType().getName());
//                        System.out.println(method.getName() + " --- " + method.getDeclaringClass() + " --- " + method.getReturnType());
                    }

                    Field[] fields = targetClass.getDeclaredFields();

                    // Display the names and types of all fields
                    System.out.println("fields of class " + targetClass.getSimpleName() + ":");
                    for (Field field : fields) {
                        System.out.println("name: " + field.getName() + ", type: " + field.getType().getSimpleName());
                    }
                    System.out.println();
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
    }
}