package bank_system;

import java.io.File;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cho FilePathProcessor — tương thích đa nền tảng (không gắn separator cụ thể).
 */
class FilePathProcessorTest {

    private final FilePathProcessor processor = new FilePathProcessor();

    @Test
    void testJoinPath() {
        String result = processor.joinPath("data", "customers", "list.txt");
        // ✅ Không hardcode "/" — dùng File.separator trong expect
        String expected = "data" + File.separator + "customers" + File.separator + "list.txt";
        assertEquals(expected, result);
    }

    @Test
    void testJoinPathUsesSystemSeparator() {
        String result = processor.joinPath("data", "customers");
        // ✅ Xác minh dùng đúng separator của hệ thống
        assertTrue(result.contains(File.separator));
    }

    @Test
    void testJoinPathMatchesPathApi() {
        // ✅ So sánh với kết quả của Path API để đảm bảo tương thích
        String result = processor.joinPath("a", "b", "c");
        String expected = Paths.get("a").resolve("b").resolve("c").toString();
        assertEquals(expected, result);
    }

    @Test
    void testIsValidPath() {
        String path = processor.joinPath("data", "list.txt");
        assertTrue(processor.isValidPath(path));
    }

    @Test
    void testIsValidPathNoSeparator() {
        assertFalse(processor.isValidPath("data"));
    }

    @Test
    void testIsValidPathEmpty() {
        assertFalse(processor.isValidPath(""));
        assertFalse(processor.isValidPath(null));
    }

    @Test
    void testJoinPathSingleSegment() {
        String result = processor.joinPath("data");
        assertEquals("data", result);
    }
}
