import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WordsCounter {

    public static void main(String[] args) throws IOException {
        String absolutePath = "/Users/imam.bux/berlin.txt";

        countWords(new File(absolutePath));
    }

    private static void countWords(File file) throws IOException {
        int totalCharacters = 0;
        int totalWords = 0;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                totalCharacters += line.length();
                totalWords += line.split(" ").length;

                line = bufferedReader.readLine();
            }
        }

        System.out.printf("total characters: %d%n", totalCharacters);
        System.out.printf("total words: %d", totalWords);
    }

}
