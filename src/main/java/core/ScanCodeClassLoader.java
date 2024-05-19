package core;
import java.io.FileInputStream;
import java.io.IOException;

public class ScanCodeClassLoader extends ClassLoader {
    private String classPath;

    public ScanCodeClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            FileInputStream fileInputStream = new FileInputStream(classPath);
            byte[] bytes = fileInputStream.readAllBytes();
            fileInputStream.close();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
