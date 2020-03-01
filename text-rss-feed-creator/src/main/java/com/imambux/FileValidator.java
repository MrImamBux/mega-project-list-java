package com.imambux;

import java.io.File;

public class FileValidator {

    public static boolean isValid(File file) {
        return file.exists() && file.isFile() && file.getName().endsWith(".txt");
    }

}
