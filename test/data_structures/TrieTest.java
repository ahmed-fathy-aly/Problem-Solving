package data_structures;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class TrieTest {

    @Test
    public void testSmallCase() {
        Trie trie = new Trie();
        trie.add("abcd");
        trie.add("abe");

        Node a = trie.get("a");
        assertEquals('a', a.c);
        assertEquals(1, a.next.size());

        Node b = a.next.get(0);
        assertEquals('b', b.c);
        assertEquals(2, b.next.size());

        Node c = b.getNext('c');
        assertEquals('c', c.c);
        assertEquals(1, c.next.size());

        Node d = c.getNext('d');
        assertEquals('d', d.c);
        assertEquals(0, d.next.size());

        Node e = b.getNext('e');
        assertEquals('e', e.c);
        assertEquals(0, e.next.size());
    }

    @Test
    public void stressTestRuntime() {
        int nWords = 500;
        int wordLength = 10000;
        Random random = new Random();
        Trie trie = new Trie();
        for (int i = 0; i < nWords; i++) {
            StringBuilder strb = new StringBuilder();
            for (int j = 0; j < wordLength; j++) {
                strb.append((char) ('a' + random.nextInt(26)));
            }
            trie.add(strb.toString());

        }
    }
}