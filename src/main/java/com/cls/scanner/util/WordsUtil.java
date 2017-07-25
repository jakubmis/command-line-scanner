package com.cls.scanner.util;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *  The class provides static method where line of loaded text file is
 *  formatted to Set of words. It cleans line from all special characters
 *  that are not words.
 */
public class WordsUtil {

    public static Set<String> getWords(Stream<String> stream) {
        return stream
                .map(WordsUtil::splitWords)
                .flatMap(Function.identity())
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }

    private static Stream<String> splitWords(String s) {
        return Arrays.stream(s.split("\\W+"));
    }
}
