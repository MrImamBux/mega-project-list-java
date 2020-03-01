package com.imambux;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class RSSValidator {

    private final static String[] FIELDS = new String[]{ "channel:", "title:", "link:", "description:", "items:", "-item:" };

    public static boolean isFormatValid(File file) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            return validateFields(bufferedReader);
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    private static boolean validateFields(BufferedReader bufferedReader) {
        try {
            String line = bufferedReader.readLine();
            while (line != null) {
                String finalLine = line;
                if (Arrays.stream(FIELDS).noneMatch(field -> finalLine.contains(field))) {
                    System.out.printf("Invalid field detected in:%n%s%n", finalLine);
                    return false;
                }
                line = bufferedReader.readLine();
            }
            System.out.println("Fields validated.");
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean validateSequence(Stream<String> linesStream) {
        return false;
    }

    private static boolean validateIndentation(Stream<String> linesStream) {
        return false;
    }

}
