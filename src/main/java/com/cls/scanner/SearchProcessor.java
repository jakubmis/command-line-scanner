package com.cls.scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by Mis on 2017-07-24.
 */
public class SearchProcessor {

    private FilesStorage filesStorage;
    private List<Result> results;

    public SearchProcessor(FilesStorage filesStorage) {
        this.filesStorage = filesStorage;
        this.results = new ArrayList<>();
    }

    public void process(String line) {
        List<ScanedFile> inMemoryFiles = filesStorage.getInMemoryFiles();
        Set<String> inputWords = WordsUtil.getWords(Stream.of(line));
        inMemoryFiles.forEach(inMemoryFile -> count(inMemoryFile, inputWords));
        printResult();
    }

    private void printResult() {
        if (results.isEmpty()) {
            System.out.println("no matches found");
        }
        Collections.sort(results, (o1, o2) -> o2.getResult().compareTo(o1.getResult()));
        results.stream().limit(10)
                .map(this::print)
                .forEach(System.out::println);
    }

    private String print(Result result) {
        return result.getFilename() + " - " + result.getResult() * 100 + "%";
    }

    private void count(ScanedFile inMemoryFile, Set<String> words) {
        Set<String> content = inMemoryFile.getContent();
        int inputWordsSize = words.size();
        int wordsFound = words.stream()
                .map(word -> exist(content, word))
                .mapToInt(Integer::intValue)
                .sum();
        float result = (float) wordsFound / inputWordsSize;
        if (wordsFound != 0) {
            results.add(new Result(inMemoryFile.getFilename(), result));
        }
    }

    private Integer exist(Set<String> content, String word) {
        if (content.contains(word)) {
            return 1;
        } else {
            return 0;
        }
    }

    public void clear() {
        results.clear();
    }
}
