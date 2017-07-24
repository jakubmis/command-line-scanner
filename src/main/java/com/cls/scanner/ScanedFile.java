package com.cls.scanner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * Created by Mis on 2017-07-24.
 */
@Getter
@Setter
@AllArgsConstructor
public class ScanedFile {

    private String filename;
    private Set<String> content;

}
