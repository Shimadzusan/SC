package core;

import util.DataOperation;
import java.nio.file.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileDataOperations {
    List<String> listFilesFromDirectory = new ArrayList();
    String totalText = "";

    public FileDataOperations(String path) throws IOException {
       getListFilesFromDirectory(path);
        DataOperation dataOperation = new DataOperation();
        for (int i = 0; i < listFilesFromDirectory.size(); i++) {
            String fileName = listFilesFromDirectory.get(i);
            totalText = totalText + dataOperation.readDataFromFile(fileName);
        }
        System.out.println("*****");
        System.out.println(totalText);
    }

    public List getListFilesFromDirectory(String path) {
        String directoryPath = path; // Change this to your directory
        try {
            Path dir = Paths.get(directoryPath);
            if (Files.exists(dir) && Files.isDirectory(dir)) {
                // Walk the file tree and print all file paths
                try (Stream<Path> files = Files.walk(dir)) {
                    files.forEach(file -> {
                        if (Files.isRegularFile(file)) { // Check if it's a regular file
                            try {
                            long x = Files.size(file);
                                System.out.println(file + " " + x);
                                listFilesFromDirectory.add(file.toString());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });
                }
            } else {
                System.out.println("The specified path is not a valid directory.");
            }
        } catch (IOException e) {
            System.err.println("An error occurred while accessing the directory: " + e.getMessage());
        }
        return listFilesFromDirectory;
    }

}
