package io.github.ms5984.commonlib.taglib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AmpersandTextProcessorTest {
    @Test
    void testProcess() {
        assertEquals("test", TagLib.ampersand("test").process());
        assertEquals("<red>test", TagLib.ampersand("&ctest").process());
        assertEquals("<white>The quick<bold> brown fox</bold><yellow> jumped over the<italic> lazy<reset> dog.",
                TagLib.ampersand("&fThe quick&l brown fox&e jumped over the&o lazy&r dog.").process());
    }

    @Test
    void testPrependProcess() {
        assertEquals("<red>test", TagLib.ampersand("test").prependProcess("<red>"));
    }

    @Test
    void testDisplayNameOverride() {
        assertEquals("<!i>test", TagLib.ampersand("test").displayNameOverride());
    }

    @Test
    void testLoreLineOverride() {
        assertEquals("<!i><white>test", TagLib.ampersand("test").loreLineOverride());
    }
}
