package bank_system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cho FilePathProcessor — demo vấn đề path separator.
 */
class FilePathProcessorTest {

    private final FilePathProcessor processor = new FilePathProcessor();

    @Test
    void testJoinPath() {
        String result = processor.joinPath("data", "customers", "list.txt");
        assertEquals("data/customers/list.txt", result);
    }

    @Test
    void testIsValidPath() {
        assertTrue(processor.isValidPath("data/customers/list.txt"));
        assertFalse(processor.isValidPath(""));
        assertFalse(processor.isValidPath(null));
    }

    @Test
    void testJoinPathSingleSegment() {
        String result = processor.joinPath("data");
        assertEquals("data", result);
    }

    @Test
    void testIsValidPathNoSeparator() {
        assertFalse(processor.isValidPath("data"));
    }
}