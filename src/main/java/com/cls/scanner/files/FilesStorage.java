package com.cls.scanner.files;

import com.cls.scanner.util.WordsUtil;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * The class is responsible for reading text files and adding successfully parsed ones
 * to collection. It is assumed that files will be "ISO-8859-1" encoded.
 */
@Getter
@Setter
public class FilesStorage {

    private List<ScannedFile> inMemoryFiles;

    public FilesStorage() {
        this.inMemoryFiles = new ArrayList<>();
    }

    public void scanFile(Path path) {
        if (isFile(path)) {
            try (Stream<String> stream = Files.lines(path, Charset.forName("ISO-8859-1"))) {
                inMemoryFiles.add(new ScannedFile(path.getFileName().toString(), WordsUtil.getWords(stream)));
            } catch (IOException e) {
                System.out.println("Exception while parsing file " + e);
            }
        }
    }

    private boolean isFile(Path path) {
        return !Files.isDirectory(path) && path.toString().endsWith(".txt");
    }
}

