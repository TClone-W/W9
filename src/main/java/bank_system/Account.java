package bank_system;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp này đại diện cho tài khoản ngân hàng.
 */
public abstract class Account {
    private static final Logger log = LoggerFactory.getLogger(Account.class);

    /** Loại tài khoản vảng lai */
    public static final String CHECKING_TYPE = "CHECKING";
    /** Loại tài khoản tiết kiệm */
    public static final String SAVINGS_TYPE = "SAVINGS";

    private final long accountNumber;
    private double balance;
    private final List<Transaction> transactions = new ArrayList<>();

    /**
     * Constructor tạo tài khoản ngân hàng.
     *
     * @param accountNumber số tài khoản
     * @param balance số dư ban đầu
     */
    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    /**
     * Lấy số tài khoản.
     *
     * @return số tài khoản
     */
    public long getAccountNumber() {
        return accountNumber;
    }

    /**
     * Lấy số dư hiện tại.
     *
     * @return số dư
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Đặt số dư.
     *
     * @param balance số dư mới
     */
    protected void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Lấy danh sách giao dịch.
     *
     * @return danh sách giao dịch
     */
    public List<Transaction> getTransactionList() {
        return transactions;
    }

    /**
     * Đặt danh sách giao dịch.
     *
     * @param transactions danh sách giao dịch mới
     */
    public void setTransactionList(List<Transaction> transactions) {
        if (transactions == null) {
            this.transactions.clear();
        } else {
            this.transactions.clear();
            this.transactions.addAll(transactions);
        }
    }

    /**
     * Lấy loại tài khoản.
     *
     * @return loại tài khoản (CHECKING hoặc SAVINGS)
     */
    public abstract String getType();

    /**
     * Nạp tiền vào tài khoản.
     *
     * @param amount số tiền cần nạp
     */
    public abstract void deposit(double amount);

    /**
     * Rút tiền từ tài khoản.
     *
     * @param amount số tiền cần rút
     */
    public abstract void withdraw(double amount);

    /**
     * Thực hiện nạp tiền vào số dư.
     *
     * @param amount số tiền cần nạp
     * @throws BankException nếu số tiền không hợp lệ
     */
    protected void doDepositing(double amount) throws BankException {
        log.debug("Bắt đầu nạp tiền: {}", amount);
        if (amount <= 0) {
            throw new InvalidFundingAmountException(amount);
        }
        balance += amount;
        log.debug("Nạp tiền thành công: {}, số dư mới: {}", amount, balance);
    }

    /**
     * Thực hiện rút tiền từ số dư.
     *
     * @param amount số tiền cần rút
     * @throws BankException nếu số tiền không hợp lệ hoặc không đủ số dư
     */
    protected void doWithdrawing(double amount) throws BankException {
        log.debug("Bắt đầu rút tiền: {}", amount);
        if (amount <= 0) {
            throw new InvalidFundingAmountException(amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
        log.debug("Rút tiền thành công: {}, số dư mới: {}", amount, balance);
    }

    /**
     * Thêm giao dịch vào danh sách.
     *
     * @param transaction giao dịch cần thêm
     */
    public void addTransaction(Transaction transaction) {
        if (transaction != null) {
            transactions.add(transaction);
        }
    }

    /**
     * Lấy lịch sử giao dịch của tài khoản.
     *
     * @return lịch sử giao dịch dạng text
     */
    public String getTransactionHistory() {
        log.debug("Bắt đầu lấy lịch sử giao dịch cho tài khoản: {}", accountNumber);
        StringBuilder history = new StringBuilder();
        history.append("Lịch sử giao dịch của tài khoản ").append(accountNumber).append(":\n");
        for (int i = 0; i < transactions.size(); i++) {
            history.append(transactions.get(i).getTransactionSummary());
            if (i < transactions.size() - 1) {
                history.append("\n");
            }
        }
        log.debug("Đã lấy xong lịch sử giao dịch cho tài khoản: {}", accountNumber);
        return history.toString();
    }

    /**
     * So sánh hai tài khoản có cùng số tài khoản hay không.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Account)) return false;
        Account other = (Account) obj;
        return this.accountNumber == other.accountNumber;
    }

    /**
     * Tính hash code dựa trên số tài khoản.
     */
    @Override
    public int hashCode() {
        return (int) (accountNumber ^ (accountNumber >>> 32));
    }
}
