package com.cls.scanner;

import java.util.Arrays;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Mis on 2017-07-24.
 */
public class WordsUtil {

    public static Set<String> getWords(Stream<String> stream) {
        return stream
                .map(WordsUtil::splitWords)
                .flatMap(Function.identity())
                .map(String::toUpperCase)
                .collect(Collectors.toSet());
    }

    public static Stream<String> splitWords(String s) {
        return Arrays.stream(s.split("\\W+"));
    }
}
