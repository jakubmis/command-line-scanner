package com.cls.scanner.search;

import com.cls.scanner.util.WordsUtil;
import com.cls.scanner.files.FilesStorage;
import com.cls.scanner.files.ScannedFile;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * The class is responsible for comparing inputted words with words from loaded files.
 * It counts percentage of founded words and prints result to user.
 */
@Getter
public class SearchProcessor {

    private FilesStorage filesStorage;
    private List<Result> results;

    public SearchProcessor(FilesStorage filesStorage) {
        this.filesStorage = filesStorage;
        this.results = new ArrayList<>();
    }

    public void process(String line) {
        List<ScannedFile> inMemoryFiles = filesStorage.getInMemoryFiles();
        Set<String> inputWords = WordsUtil.getWords(Stream.of(line));
        inMemoryFiles.forEach(inMemoryFile -> countWordsInLoadedFile(inMemoryFile, inputWords));
        printResult();
    }

    private void printResult() {
        if (results.isEmpty()) {
            System.out.println("no matches found");
        }
        results.sort((o1, o2) -> o2.getResult().compareTo(o1.getResult()));
        results.stream().limit(10)
                .map(this::print)
                .forEach(System.out::println);
    }

    private String print(Result result) {
        return result.getFilename() + " - " + result.getResult() * 100 + "%";
    }

    private void countWordsInLoadedFile(ScannedFile inMemoryFile, Set<String> words) {
        Set<String> content = inMemoryFile.getContent();
        int inputWordsSize = words.size();
        int wordsFound = words.stream()
                .map(word -> wordExistInFile(content, word))
                .mapToInt(Integer::intValue)
                .sum();
        float result = (float) wordsFound / inputWordsSize;
        if (wordsFound != 0) {
            results.add(new Result(inMemoryFile.getFilename(), result));
        }
    }

    private Integer wordExistInFile(Set<String> content, String word) {
        return content.contains(word) ? 1 : 0;
    }

    public void clear() {
        results.clear();
    }
}
