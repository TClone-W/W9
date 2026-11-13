package bank_system;

import java.io.File;

/**
 * Xử lý đường dẫn file - demo vấn đề path separator.
 */
public class FilePathProcessor {

    /**
     * Ghép các đoạn đường dẫn bằng dấu gạch chéo "/" (cứng).
     *
     * @param segments các đoạn đường dẫn
     * @return đường dẫn đã ghép
     */
    public String joinPath(String... segments) {
        StringBuilder path = new StringBuilder();
        for (int i = 0; i < segments.length; i++) {
            path.append(segments[i]);
            if (i < segments.length - 1) {
                path.append("/"); // ❌ Dùng cứng "/" — sẽ fail trên Windows
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
        return path != null && !path.isEmpty() && path.contains("/");
    }
}