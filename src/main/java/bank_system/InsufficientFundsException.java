package bank_system;

import java.util.Locale;

/**
 * Ngoại lệ khi số dư tài khoản không đủ.
 */
public class InsufficientFundsException extends BankException {
    /**
     * Constructor tạo ngoại lệ số dư không đủ.
     *
     * @param amount số tiền cần rút
     */
    public InsufficientFundsException(double amount) {
        super("Số dư tài khoản không đủ $" + String.format(Locale.US, "%.2f", amount)
                + " để thực hiện giao dịch");
    }
}
