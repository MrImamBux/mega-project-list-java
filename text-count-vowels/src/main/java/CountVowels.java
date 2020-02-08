public class CountVowels {

    public static void main(String[] args) {
        countVowels("Pakistan");
    }

    public static void countVowels(String word) {
        char[] vowels = { 'a', 'e', 'i', 'o', 'u' };
        System.out.println(word);
        int totalCounter = 0;
        for (char vowel : vowels) {
            int counter = 0;
            for (char letter : word.toLowerCase().toCharArray()) {
                if (vowel == letter) {
                    counter++;
                }
            }
            if (counter > 0) {
                System.out.printf("%s: %d%n", vowel, counter);
                totalCounter += counter;
            }
        }
        System.out.printf("Total counter: %d", totalCounter);
    }

}
