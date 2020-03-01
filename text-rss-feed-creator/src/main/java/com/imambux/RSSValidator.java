package com.imambux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class RSSValidator {

    private final static String[] FIELDS = new String[]{ "channel", "title", "link", "description", "item" };

    public static boolean isFormatValid(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            Stream<String> linesStream = bufferedReader.lines();
            return validateFields(linesStream) && validateSequence(linesStream) && validateIndentation(linesStream);
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean validateIndentation(Stream<String> linesStream) {
        return false;
    }

    private static boolean validateSequence(Stream<String> linesStream) {
        return false;
    }

    private static boolean validateFields(Stream<String> linesStream) {

        return false;
    }

}
