package com.cls.scanner;

import com.cls.scanner.files.FilesStorage;
import com.cls.scanner.search.SearchProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Main application class which runs interactive console.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index");
        }
        FilesStorage filesStorage = scanGivenPath(args[0]);
        runInputScanner(filesStorage);
    }

    private static FilesStorage scanGivenPath(String arg) throws IOException {
        FilesStorage filesStorage = new FilesStorage();
        Path path = Paths.get(arg);
        Files.walk(path).forEach(filesStorage::scanFile);
        System.out.println(filesStorage.getInMemoryFiles().size() + " files read in directory " + arg);
        return filesStorage;
    }

    private static void runInputScanner(FilesStorage filesStorage) {
        SearchProcessor searchProcessor = new SearchProcessor(filesStorage);
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.print("search> ");
            final String line = keyboard.nextLine();
            if (line.equals(":quit")) {
                break;
            } else {
                searchProcessor.process(line);
                searchProcessor.clear();
            }
        }
    }
}
