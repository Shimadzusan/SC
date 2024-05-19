package core;

import javax.lang.model.element.Element;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.management.ClassLoadingMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarFile;

public class Research {

    public Research() throws IOException, ClassNotFoundException {
        JarFile jar = new JarFile("C:\\Users\\worker\\IdeaProjects\\Anno3\\anno3.jar");
        System.out.println("jar.size(): " + jar.size());

                jar.stream().forEach(jarEntry -> {
                    String atr = jarEntry.getName();
                    jarEntry.getCodeSigners();
                    System.out.println(atr);
                });
        Class<?> targetClass = Class.forName("com.google.gson.JsonElement");
        Method[] methods =targetClass.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        ClassLoadingMXBean runtimeMxBean = ManagementFactory.getClassLoadingMXBean();

        // Get the list of all loaded class names
        int loadedClassNames = runtimeMxBean.getLoadedClassCount();
        System.out.println(loadedClassNames);
        // Display the loaded class names
//        System.out.println("Loaded classes:");
//        for (String className : loadedClassNames) {
//            System.out.println(className);
//        }

    }
}
