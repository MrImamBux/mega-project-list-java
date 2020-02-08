import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReverseAStringTest {

    ReverseAString subject = new ReverseAString();

    @DisplayName("null string, expected null")
    @Test
    void null_string() {
        assertSame(null, subject.reverse(null));
    }

    @DisplayName("empty string, expected empty")
    @Test
    void empty_string() {
        String text = "";
        assertSame(text, subject.reverse(text));
    }

    @DisplayName("one character in a string, expected same")
    @Test
    void one_char() {
        String text = "a";
        assertSame(text, subject.reverse(text));
    }

    @DisplayName("atleast 1 different character in a string, expected reverse")
    @Test
    void diff_chars() {
        String expected = "aaab";
        String actual = subject.reverse("baaa");
        assertEquals(expected, actual);
    }

    @DisplayName("same characters in a string, expected equal")
    @Test
    void same_chars() {
        String text = "aaa";
        assertEquals(text, subject.reverse(text));
    }

}
