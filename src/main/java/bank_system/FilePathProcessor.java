package bank_system;

import java.io.File;

/**
 * Xử lý đường dẫn file — đã refactor dùng File.separator.
 */
public class FilePathProcessor {

    /**
     * Ghép các đoạn đường dẫn bằng File.separator (tương thích mọi OS).
     *
     * @param segments các đoạn đường dẫn
     * @return đường dẫn đã ghép
     */
    public String joinPath(String... segments) {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < segments.length; i++) {
            path.append(segments[i]);
            if (i < segments.length - 1) {
                path.append(File.separator); 
            }
        }
        return path.toString();
    }

    /**
     * Kiểm tra đường dẫn có hợp lệ không.
     *
     * @param path đường dẫn cần kiểm tra
     * @return true nếu hợp lệ
     */
    public boolean isValidPath(String path) {
        if (path == null || path.isEmpty()) {
            return false;
        }
        // Dùng File.separator hoặc ký tự separator chung
        return path.contains(File.separator) || path.contains("/") || path.contains("\\");
    }
}