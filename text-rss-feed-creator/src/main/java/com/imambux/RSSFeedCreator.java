package com.imambux;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
            out.print("Enter YAML yamlFile's absolute path (type X to quit): ");
            String userInput = new Scanner(System.in).nextLine();
            if (userInput.equals("X")) {
                break;
            }
            File yamlFile = new File(userInput);

            if (isValid(yamlFile)) {
                createRSSFile(yamlFile);
            } else {
                out.println("Sorry invalid YAML/YML yamlFile path.");
                out.println();
                out.println("===================================================");
                out.println();
                out.println("Please provide the text yamlFile with following YAML/YML format:");
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
            out.print("\n\n\n");
        }

    }

    private static void createRSSFile(File file) {
        try {
            Scanner scanner = new Scanner(file);
            String filePath = file.getParent() + "/" + file.getName().split("\\.")[0] + "-rss.xml";
            PrintWriter printWriter = new PrintWriter(new File(filePath));

            printWriter.println("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
            printWriter.println("<rss version=\"2.0\">");
            printWriter.println("<channel>");

            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] split = line.split(":", 2);
                printWriter.printf("<%s>", split[0].trim());
                printWriter.printf("%s", split[1].trim());
                printWriter.printf("</%s>%n", split[0].trim());
            }

            printWriter.println("</channel>");
            printWriter.println("</rss>");

            printWriter.flush();
            printWriter.close();
            out.printf("File created: %s", filePath);
        } catch (IOException e) {
            out.println("Could not create file.");
        }

    }

    public static boolean isValid(File file) {
        return file.exists() && file.isFile() && (file.getName().endsWith(".yaml") || file.getName().endsWith(".yml"));
    }

}
