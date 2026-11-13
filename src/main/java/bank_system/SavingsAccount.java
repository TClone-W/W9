package bank_system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản tiết kiệm - thực thi quy định về nạp tiền và rút tiền.
 */
public class SavingsAccount extends Account {
    private static final Logger log = LoggerFactory.getLogger(SavingsAccount.class);
    private static final double MAX_WITHDRAW_AMOUNT = 1000.0;
    private static final double MIN_BALANCE = 5000.0;

    /**
     * Constructor tạo tài khoản tiết kiệm.
     *
     * @param accountNumber số tài khoản
     * @param balance số dư ban đầu
     */
    public SavingsAccount(long accountNumber, double balance) {
        super(accountNumber, balance);
    }

    /**
     * Trả loại tài khoản: SAVINGS.
     *
     * @return loại tài khoản tiết kiệm
     */
    @Override
    public String getType() {
        return Account.SAVINGS_TYPE;
    }

    /**
     * Nạp tiền vào tài khoản tiết kiệm.
     *
     * @param amount số tiền cần nạp
     */
    @Override
    public void deposit(double amount) {
        log.debug("Bắt đầu xử lý nạp tiền: {}", amount);
        double initialBalance = getBalance();
        try {
            doDepositing(amount);
            double finalBalance = getBalance();
            Transaction transaction = new Transaction(
                    Transaction.TYPE_DEPOSIT_SAVINGS,
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
     * Rút tiền từ tài khoản tiết kiệm.
     *
     * @param amount số tiền cần rút
     */
    @Override
    public void withdraw(double amount) {
        log.debug("Bắt đầu xử lý rút tiền: {}", amount);
        double initialBalance = getBalance();
        try {
            if (amount > MAX_WITHDRAW_AMOUNT) {
                throw new InvalidFundingAmountException(amount);
            }
            if (initialBalance - amount < MIN_BALANCE) {
                throw new InsufficientFundsException(amount);
            }
            doWithdrawing(amount);
            double finalBalance = getBalance();
            Transaction transaction = new Transaction(
                    Transaction.TYPE_WITHDRAW_SAVINGS,
                    amount,
                    initialBalance,
                    finalBalance);
            addTransaction(transaction);
            log.info("Rút tiền từ tài khoản {} thành công: {}, số dư còn: {}", getAccountNumber(), amount, finalBalance);
        } catch (BankException e) {
            log.error("Rút tiền thất bại: {}", e.getMessage(), e);
        }
    }
}
