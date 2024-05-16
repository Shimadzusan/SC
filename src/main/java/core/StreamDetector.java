package core;

import util.DataOperation;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class StreamDetector {

    public StreamDetector() throws IOException {
        Path dir = Paths.get("C:\\Users\\worker\\IdeaProjects\\Anno2\\src\\main\\java"); // Replace "your_directory_path" with the actual directory path
        ArrayList<String> listFiles = new ArrayList<String>();
        try {
            Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    if (file.getFileName().toString().endsWith(".java")) {
//                        System.out.println(file);
                        listFiles.add(file.toString());
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        //***********************************
        int counter = 0;
        for (int j = 0; j < listFiles.size(); j++) {
//            String path = "C:\\Users\\worker\\IdeaProjects\\Anno2\\src\\main\\java\\workforce\\FarmProcess.java";
            String path = listFiles.get(j);
            DataOperation dataOperation = new DataOperation();
            String operationalText = dataOperation.readDataFromFile(path);
            //System.out.println("___MARKER___");
            String marker = "System.out.println(\"___MARKER___";
            String[] array = operationalText.split("\\n");
            for (int i = 0; i < array.length; i++) {
                array[i] = array[i] + "\n";
            }
//            int counter = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i].contains("{") && !array[i].contains("class") && !array[i].contains("}") && !array[i].contains("//") && !array[i].contains("interface")) {
                    //                System.out.println(array[i]);
                    System.out.println();
                    array[i] = array[i] + marker + counter + "\");" + "\n";
                    //                System.out.println(s);
                    counter++;
                    //System.out.println(array[i + 1]);
                }
            }

            String resultText = "";
            for (int i = 0; i < array.length; i++) {
                resultText = resultText + array[i];
            }
//         
            dataOperation.writeDataToFile(path, resultText);
        }
    }
}
