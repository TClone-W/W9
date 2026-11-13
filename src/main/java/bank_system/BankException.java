package bank_system;

/**
 * Ngoại lệ chung trong hệ thống ngân hàng.
 */
public class BankException extends Exception {
    /**
     * Constructor tạo ngoại lệ ngân hàng.
     *
     * @param message thông điệp lỗi
     */
    public BankException(String message) {
        super(message);
    }
}
