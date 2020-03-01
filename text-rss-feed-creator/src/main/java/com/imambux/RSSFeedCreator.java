package com.imambux;

import java.io.File;
import java.util.Scanner;

import static java.lang.System.*;

/**
 * RSS Feed Creator
 * @author imam.bux
 *
 */
public class RSSFeedCreator {

    public static void main( String[] args ) {
        while(true) {
            out.print("Enter YAML file's absolute path (type X to quit): ");
            String fileAbsolutePath = new Scanner(System.in).nextLine();
            if (fileAbsolutePath.equals("X")) {
                break;
            }
            File file = new File(fileAbsolutePath);

            out.println("Validating...");
            if (FileValidator.isValid(file)) {
                if (RSSValidator.isFormatValid(file)) {
                } else {
                    out.println("===================================================");
                    out.println();
                    out.println("Please provide the text file with following YAML/YML format:");
                    out.println();
                    out.println("channel:");
                    out.println("  title:text-goes-here");
                    out.println("  link:http-link-here");
                    out.println("  description:text-goes-here");
                    out.println("  [items:");
                    out.println("    [-item:");
                    out.println("      title:text-goes-here");
                    out.println("      link:http-link-here");
                    out.println("      description:text-goes-here");
                    out.println("    ]+");
                    out.println("  ]*");
                    out.println();
                    out.println("for example:");
                    out.println();
                    out.println("channel:");
                    out.println("  title:geo news");
                    out.println("  link:www.geo.tv/news/local/01032020");
                    out.println("  description:According to the recent survey, Pakistan ranks top for the traveling destination.");
                    out.println("  items:");
                    out.println("    -item:");
                    out.println("      title:geo news");
                    out.println("      link:www.geo.tv/news/local/01032020/islamabad");
                    out.println("      description:Islamabad among the most visited cities by the foreigners.");
                    out.println();
                    out.println("===================================================\n");
                }
            } else {
                out.println("Sorry invalid YAML/YML file path.");
            }
            out.print("\n\n\n");
        }

    }

}
