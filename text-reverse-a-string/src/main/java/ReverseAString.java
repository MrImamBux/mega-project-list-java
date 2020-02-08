import java.util.Scanner;

public class ReverseAString {

    public static void main(String[] args) {
        System.out.print("Please enter a text: ");
        String text = new Scanner(System.in).nextLine();
        System.out.printf("Reverse Text: %s", new ReverseAString().reverse(text));
    }

    public String reverse(String text) {
        if (text == null || text.isBlank() || text.length() == 1) {
            return text;
        }
        char[] textChars = text.toCharArray();
        StringBuilder result = new StringBuilder();
        for (int i = textChars.length - 1; i >= 0; i--) {
            result.append(textChars[i]);
        }

        return result.toString();
    }

}
