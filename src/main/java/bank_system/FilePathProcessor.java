package bank_system;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Xử lý đường dẫn file — dùng java.nio.file.Path API.
 */
public class FilePathProcessor {

    /**
     * Ghép các đoạn đường dẫn bằng Path API (tương thích mọi OS).
     *
     * @param segments các đoạn đường dẫn
     * @return đường dẫn đã ghép
     */
    public String joinPath(String... segments) {
        Path path = Paths.get(segments[0]);
        for (int i = 1; i < segments.length; i++) {
            path = path.resolve(segments[i]);
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
        try {
            Path parsed = Paths.get(path);
            return parsed.getFileName() != null;
        } catch (Exception e) {
            return false;
        }
    }
}