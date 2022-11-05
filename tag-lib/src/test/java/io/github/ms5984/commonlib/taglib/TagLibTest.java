package io.github.ms5984.commonlib.taglib;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class TagLibTest {
    @Test
    void testAmpersand() {
        final var testInput = "test";
        final var ampersand = TagLib.ampersand(testInput);
        assertInstanceOf(AmpersandTextProcessor.class, ampersand);
        assertEquals(testInput, ((AmpersandTextProcessor) ampersand).input);
    }

    @Test
    void testTagFromAmpersand() {
        // test with leading ampersand
        assertEquals(TagLib.tagFromAmpersand("k", false), TagLib.tagFromAmpersand("&k", false));
        assertEquals(TagLib.tagFromAmpersand("k", true), TagLib.tagFromAmpersand("&k", true));
        assertEquals(TagLib.tagFromAmpersand("#abcabc", false), TagLib.tagFromAmpersand("&#abcabc", false));
        assertEquals(TagLib.tagFromAmpersand("#abcabc", true), TagLib.tagFromAmpersand("&#abcabc", true));
    }

    @Test
    void testTagFromLegacy() {
        final var singleOpen = Pattern.compile("<\\w+>");
        final var singleClose = Pattern.compile("</\\w+>");
        final var singles = List.of(
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
                "a", "b", "c", "d", "e", "f",
                "k", "l", "m", "n", "o", "r"
        );
        // test singles
        for (String s : singles) {
            assertTrue(singleOpen.matcher(TagLib.tagFromLegacy(s, false)).find());
            assertTrue(singleClose.matcher(TagLib.tagFromLegacy(s, true)).find());
        }
        // test invalid single
        assertThrows(IllegalArgumentException.class, () -> TagLib.tagFromLegacy("v", false));
        assertThrows(IllegalArgumentException.class, () -> TagLib.tagFromLegacy("v", true));
        // test hex
        final var hexOpen = Pattern.compile("<#[abcdefABCDEF\\d]{6}>");
        final var hexClose = Pattern.compile("</#[abcdefABCDEF\\d]{6}>");
        // test known hex
        assertTrue(hexOpen.matcher(TagLib.tagFromLegacy("#abcdef", false)).find());
        assertTrue(hexClose.matcher(TagLib.tagFromLegacy("#abcdef", true)).find());
        // test randomly generated hex
        final var sb = new StringBuilder("#");
        for (int i = 0; i < 6; ++i) {
            final var nextInt = ThreadLocalRandom.current().nextInt(0, 16);
            switch (nextInt) {
                case 10 -> sb.append('a');
                case 11 -> sb.append('b');
                case 12 -> sb.append('c');
                case 13 -> sb.append('d');
                case 14 -> sb.append('e');
                case 15 -> sb.append('f');
                default -> sb.append(nextInt);
            }
        }
        final var randomHex = sb.toString();
        System.out.println("Testing random hex: " + randomHex);
        assertTrue(hexOpen.matcher(TagLib.tagFromLegacy(randomHex, false)).find());
        assertTrue(hexClose.matcher(TagLib.tagFromLegacy(randomHex, true)).find());
    }
}
