package com.imambux;

import java.io.File;
import java.util.Scanner;

/**
 * RSS Feed Creator
 * @author imam.bux
 *
 */
public class RSSFeedCreator {

    public static void main( String[] args ) {
        System.out.print("Enter file's absolute path: ");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        File file = new File(filePath);

        if (file.exists() && file.isFile() && file.getName().endsWith(".txt")) {
            System.out.println("file exists");
        } else {
            System.out.println("Sorry invalid file location.");
        }

    }

}
