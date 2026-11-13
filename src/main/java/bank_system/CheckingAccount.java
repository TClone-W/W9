package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản vảng lai.
 */
public class CheckingAccount extends Account {
    private static final Logger log = LoggerFactory.getLogger(CheckingAccount.class);

    /**
     * Constructor tạo tài khoản vảng lai.
     *
     * @param accountNumber số tài khoản
     * @param balance số dư ban đầu
     */
    public CheckingAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     * Trả loại tài khoản: CHECKING.
     *
     * @return loại tài khoản vảng lai
     */
    @Override
    public String getType() {
        return Account.CHECKING_TYPE;
    }

    /**
     * Nạp tiền vào tài khoản vảng lai.
     *
     * @param amount số tiền cần nạp
     */
    @Override
    public void deposit(double amount) {
        double initialBalance = getBalance();
        try {
            doDepositing(amount);
            double finalBalance = getBalance();
            Transaction transaction = new Transaction(
                    Transaction.TYPE_DEPOSIT_CHECKING,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(transaction);
            log.info("Nạp tiền vào tài khoản {} thành công: {}", getAccountNumber(), amount);
        } catch (BankException e) {
            log.error("Lỗi nạp tiền: {}", e.getMessage(), e);
        }
    }

    /**
     * Rút tiền từ tài khoản vảng lai.
     *
     * @param amount số tiền cần rút
     */
    @Override
    public void withdraw(double amount) {
        double initialBalance = getBalance();
        try {
            doWithdrawing(amount);
            double finalBalance = getBalance();
            Transaction transaction = new Transaction(
                    Transaction.TYPE_WITHDRAW_CHECKING,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(transaction);
            log.info("Rút tiền từ tài khoản {} thành công: {}", getAccountNumber(), amount);
        } catch (BankException e) {
            log.error("Lỗi rút tiền: {}", e.getMessage(), e);
        }
    }
}
