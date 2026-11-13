package bank_system;

import java.io.File;

/**
 * Xử lý đường dẫn file — refactor dùng File.separator (tương thích đa nền tảng).
 */
public class FilePathProcessor {

    /**
     * Ghép các đoạn đường dẫn bằng File.separator (tự động đúng theo OS).
     *
     * @param segments các đoạn đường dẫn
     * @return đường dẫn đã ghép
     */
    public String joinPath(String... segments) {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < segments.length; i++) {
            path.append(segments[i]);
            if (i < segments.length - 1) {
                path.append(File.separator); // ✅ Dùng File.separator thay "/" cứng
            }
        }
        return path.toString();
    }

    /**
     * Kiểm tra đường dẫn có hợp lệ không — kiểm tra chứa separator.
     *
     * @param path đường dẫn cần kiểm tra
     * @return true nếu có separator, false nếu không
     */
    public boolean isValidPath(String path) {
        if (path == null || path.isEmpty()) {
            return false;
        }
        // Kiểm tra chứa separator của hệ thống hoặc separator phổ biến
        return path.contains(File.separator)
                || path.contains("/")
                || path.contains("\\");
    }
}
