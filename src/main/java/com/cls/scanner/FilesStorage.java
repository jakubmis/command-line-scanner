package com.cls.scanner;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Mis on 2017-07-24.
 */
@Getter
public class FilesStorage {

    private List<ScanedFile> inMemoryFiles;

    public FilesStorage() {
        this.inMemoryFiles = new ArrayList<>();
    }

    public void scanFile(Path path) {
        if (isFile(path)) {
            try (Stream<String> stream = Files.lines(path)) {
                inMemoryFiles.add(new ScanedFile(path.getFileName().toString(), WordsUtil.getWords(stream)));
            } catch (IOException e) {
                System.out.println("Exception while parsing file " + e);
            }
        }
    }

    private boolean isFile(Path path) {
        return !Files.isDirectory(path) && path.toString().endsWith(".txt");
    }
}

