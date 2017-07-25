package com.cls.scanner.files;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * The class is POJO for holding data of loaded text files from init directory.
 */
@Getter
@Setter
@AllArgsConstructor
public class ScannedFile {

    private String filename;
    private Set<String> content;

}
