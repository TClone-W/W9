package bank_system;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Đại diện cho một giao dịch.
 */
public class Transaction {
    private static final Logger log = LoggerFactory.getLogger(Transaction.class);
    private static final String FORMAT_PATTERN = "%.2f";

    /** Loại: nạp tiền vảng lai */
    public static final int TYPE_DEPOSIT_CHECKING = 1;
    /** Loại: rút tiền vảng lai */
    public static final int TYPE_WITHDRAW_CHECKING = 2;
    /** Loại: nạp tiền tiết kiệm */
    public static final int TYPE_DEPOSIT_SAVINGS = 3;
    /** Loại: rút tiền tiết kiệm */
    public static final int TYPE_WITHDRAW_SAVINGS = 4;

    private final int type;
    private final double amount;
    private final double initialBalance;
    private final double finalBalance;

    /**
    * Constructor tạo giao dịch.
    *
    * @param type loại giao dịch
    * @param amount số tiền giao dịch
    * @param initialBalance số dư ban đầu
    * @param finalBalance số dư sau giao dịch
    */
    public Transaction(int type, double amount, double initialBalance, double finalBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    /**
    * Lấy loại giao dịch.
    *
    * @return loại giao dịch
    */
    public int getType() {
        return type;
    }

    /**
    * Lấy số tiền giao dịch.
    *
    * @return số tiền
    */
    public double getAmount() {
        return amount;
    }

    /**
    * Lấy số dư ban đầu.
    *
    * @return số dư ban đầu
    */
    public double getInitialBalance() {
        return initialBalance;
    }

    /**
    * Lấy số dư cuối.
    *
    * @return số dư cuối
    */
    public double getFinalBalance() {
        return finalBalance;
    }

    /**
    * Lấy tên loại giao dịch.
    *
    * @param type loại giao dịch
    * @return tên loại giao dịch
    */
    public static String getTypeString(int type) {
        switch (type) {
            case TYPE_DEPOSIT_CHECKING:
                return "Nạp tiền vảng lai";
            case TYPE_WITHDRAW_CHECKING:
                return "Rút tiền vảng lai";
            case TYPE_DEPOSIT_SAVINGS:
                return "Nạp tiền tiết kiệm";
            case TYPE_WITHDRAW_SAVINGS:
                return "Rút tiền tiết kiệm";
            default:
                return "Không rõ";
        }
    }

    /**
    * Tạo bản tóm tắt giao dịch.
    *
    * @return bản tóm tắt giao dịch
    */
    public String getTransactionSummary() {
        log.debug("Bắt đầu tạo tóm tắt giao dịch cho loại: {}", type);
        String summary = String.format(
                Locale.US,
                "- Loại giao dịch: %s. Số dư ban đầu: $%.2f. Số tiền: $%.2f. Số dư cuối: $%.2f.",
                getTypeString(type), initialBalance, amount, finalBalance
        );
        log.debug("Đã tạo xong tóm tắt giao dịch");
        return summary;
    }
}
