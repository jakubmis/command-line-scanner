package com.cls.scanner.search;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The class is POJO for holding result from searching process.
 */
@AllArgsConstructor
@Getter
public class Result {

    private String filename;
    private Float result;

}
