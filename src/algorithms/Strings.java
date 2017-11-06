package algorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by afathy on 11/6/17.
 */
public class Strings {

    public int strStr(final String haystack, final String needle) {
        char[] str = haystack.toCharArray();
        char[] pattern = needle.toCharArray();
        int[] f = getFailFunction(pattern);

        for (int i = 0, k = 0; i < str.length; i++) {
            while (k > 0 && str[i] != pattern[k]) {
                k = f[k - 1];
            }

            if (str[i] == pattern[k]) {
                k++;
            }

            if (k == pattern.length) {
                return i - pattern.length + 1;
            }
        }
        return -1;
    }

    /**
     * visible for testing
     */
    static int[] getFailFunction(char[] str) {
        int[] f = new int[str.length];

        for (int i = 1, k = 0; i < str.length; i++) {
            while (k > 0 && str[i] != str[k]) {
                k = f[k - 1];
            }

            if (str[i] == str[k]) {
                f[i] = ++k;
            }
        }
        return f;
    }
}
