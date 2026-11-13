package bank_system;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp Customer đại diện cho một khách hàng.
 */
public class Customer {
    private static final Logger log = LoggerFactory.getLogger(Customer.class);

    private long idNumber;
    private String fullName;
    private final List<Account> accountList = new ArrayList<>();

    /**
     * Constructor không tham số.
     */
    public Customer() {
        this(0L, "");
    }

    /**
     * Constructor có tham số.
     *
     * @param idNumber số CMND
     * @param fullName họ tên
     */
    public Customer(long idNumber, String fullName) {
        this.idNumber = idNumber;
        this.fullName = fullName;
    }

    /**
     * Lấy số CMND.
     *
     * @return số CMND
     */
    public long getIdNumber() {
        return idNumber;
    }

    /**
     * Đặt số CMND.
     *
     * @param idNumber số CMND mới
     */
    public void setIdNumber(long idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * Lấy họ tên.
     *
     * @return họ tên
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Đặt họ tên.
     *
     * @param fullName họ tên mới
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Lấy danh sách tài khoản.
     *
     * @return danh sách tài khoản
     */
    public List<Account> getAccountList() {
        return accountList;
    }

    /**
     * Đặt danh sách tài khoản.
     *
     * @param accountList danh sách tài khoản mới
     */
    public void setAccountList(List<Account> accountList) {
        this.accountList.clear();
        if (accountList != null) {
            this.accountList.addAll(accountList);
        }
    }

    /**
     * Thêm tài khoản cho khách hàng.
     *
     * @param account tài khoản cần thêm
     */
    public void addAccount(Account account) {
        if (account == null) {
            return;
        }
        if (!accountList.contains(account)) {
            accountList.add(account);
            log.info("Thêm tài khoản {} cho khách hàng {}", account.getAccountNumber(), fullName);
        }
    }

    /**
     * Xóa tài khoản của khách hàng.
     *
     * @param account tài khoản cần xóa
     */
    public void removeAccount(Account account) {
        if (account == null) {
            return;
        }
        accountList.remove(account);
        log.info("Đã xóa tài khoản {} của khách hàng {}", account.getAccountNumber(), fullName);
    }

    /**
     * Lấy thông tin khách hàng dạng text.
     *
     * @return thông tin khách hàng
     */
    public String getCustomerInfo() {
        return "Số CMND: " + idNumber + ". Họ tên: " + fullName + ".";
    }
}
