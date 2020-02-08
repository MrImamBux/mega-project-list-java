public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome("bob"));
        System.out.println(isPalindrome("stephen"));
        System.out.println(isPalindrome("oppo"));
        System.out.println(isPalindrome("a"));
    }

    private static boolean isPalindrome(String oppo) {
        if (oppo.isBlank() || oppo.length() == 1) return true;
        for (int i = 0, j = oppo.length() - 1; i < j; i++, j--) {
            if (oppo.charAt(i) != oppo.charAt(j)) {
                return false;
            }
        }

        return true;
    }

}
