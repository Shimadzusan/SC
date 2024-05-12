package core;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarFileReserch {
    public static void main(String[] args) {
        String jarFilePath = "C:\\Users\\worker\\IdeaProjects\\javaLab\\JSON-java\\target\\json-20240303.jar"; // Specify the path to the JAR file here

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
                    throw new RuntimeException(e);
                }

                Method[] methods = targetClass.getDeclaredMethods();

                // Display the names of all methods
                System.out.println("methods of class " + targetClass.getSimpleName() + ":");
                for (Method method : methods) {
                    System.out.println(method.getName() + " --- " + method.getDeclaringClass() + " --- " + method.getReturnType());
                }

                Field[] fields = targetClass.getDeclaredFields();

                // Display the names and types of all fields
                System.out.println("fields of class " + targetClass.getSimpleName() + ":");
                for (Field field : fields) {
                    System.out.println("name: " + field.getName() + ", type: " + field.getType().getSimpleName());
                }
            }
        });

        jarFile.close();
    }
}