package bank_system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilePathProcessorTest {

    private final FilePathProcessor processor = new FilePathProcessor();

    @Test
    void testJoinPathWindowsFormat() {
        // ❌ Test giả định Windows dùng "\"
        String result = processor.joinPath("data", "customers");
        // Trên Windows: code sinh "data/customers" nhưng test expect "data\customers"
        // → FAIL trên Windows
        assertEquals("data\\customers", result);
    }

    @Test
    void testJoinPathLinuxFormat() {
        // ✅ Trên Linux/macOS: code sinh "data/customers", test expect "data/customers"
        // → PASS
        String result = processor.joinPath("data", "customers");
        assertEquals("data/customers", result);
    }
}