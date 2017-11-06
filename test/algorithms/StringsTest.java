package algorithms;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by afathy on 11/6/17.
 */
public class StringsTest {

    @Test
    public void testFailFunction() {
        assertArrayEquals(new int[]{0, 1, 0, 0, 1, 2, 3, 4}, Strings.getFailFunction("AABBAABB".toCharArray()));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4}, Strings.getFailFunction("AAAAA".toCharArray()));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 0, 0, 1, 0, 0, 0, 1, 2, 0}, Strings.getFailFunction("AAAAABBABBBAAB".toCharArray()));
        assertArrayEquals(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 1, 2, 3, 4, 5, 0, 0, 0, 0, 0}, Strings.getFailFunction("AAAAAAAAAABBBBAAAAABBBBB".toCharArray()));
        assertArrayEquals(new int[]{0, 0, 1}, Strings.getFailFunction("ABA".toCharArray()));
    }


    @Test
    public void testKMP() {
        assertEquals(Arrays.asList(0, 4), Strings.kmp("AABBAABB".toCharArray(), "AABB".toCharArray()));
        assertEquals(Arrays.asList(0, 8, 14, 17), Strings.kmp("AABABBAAAABBBBAABAAB".toCharArray(), "AAB".toCharArray()));
        assertEquals(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7), Strings.kmp("AAAAAAAAA".toCharArray(), "AA".toCharArray()));
        assertEquals(Arrays.asList(28, 30, 51, 56, 58, 66, 68, 78), Strings.kmp("AAABBBABBBABBBABBBBAAAABBABBABABAABBABBABBBABBBBAAAABABBABABABBBBAABABAAAABBBBABAB".toCharArray(), "ABA".toCharArray()));
        assertEquals(Arrays.asList(0, 5, 7, 9, 11, 13, 15, 21, 26, 32, 34, 39, 49, 51, 57, 59, 68, 73, 75, 81, 89, 91),
                Strings.kmp("BABABBABABABABABABAAABABABBABABBBABABABBABABBABBBBABABAAABABABABBABBBABABBABABABBBABABBABBABABABBA".toCharArray(), "BABA".toCharArray()));
    }

    List<Integer> getBruteMatching(String str, String pattern) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.substring(i).startsWith(pattern)) {
                result.add(i);
            }
        }
        return result;
    }

}