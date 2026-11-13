package bank_system;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp quản lý ngân hàng, chứa danh sách khách hàng.
 */
public class Bank {
    private static final Logger log = LoggerFactory.getLogger(Bank.class);
    private static final String ID_REGEX = "\\d{9}";

    private final List<Customer> customers = new ArrayList<>();

    /**
     * Lấy danh sách khách hàng.
     *
     * @return danh sách khách hàng
     */
    public List<Customer> getCustomerList() {
        return customers;
    }

    /**
     * Đặt danh sách khách hàng.
     *
     * @param customerList danh sách khách hàng mới
     */
    public void setCustomerList(List<Customer> customerList) {
        if (customerList == null) {
            customers.clear();
        } else {
            customers.clear();
            customers.addAll(customerList);
        }
    }

    /**
     * Đọc danh sách khách hàng từ input stream.
     * Format mỗi dòng:
     * - Dòng khách hàng: "Ho ten [so_CMND_9_ky_so]"
     * - Dòng tài khoản: "[so_tai_khoan] [CHECKING|SAVINGS] [so_du]"
     *
     * @param inputStream luồng dữ liệu đầu vào
     */
    public void readCustomerList(InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        log.debug("Bắt đầu đọc dữ liệu khách hàng từ input stream");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            Customer current = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                int lastSpace = line.lastIndexOf(' ');
                if (lastSpace <= 0) {
                    continue;
                }
                String token = line.substring(lastSpace + 1).trim();
                if (token.matches(ID_REGEX)) {
                    String name = line.substring(0, lastSpace).trim();
                    current = new Customer(Long.parseLong(token), name);
                    customers.add(current);
                    log.info("Thêm khách hàng mới: {}", name);
                } else if (current != null) {
                    String[] parts = line.split("\\s+");
                    if (parts.length >= 3) {
                        long accountNumber = Long.parseLong(parts[0]);
                        double balance = Double.parseDouble(parts[2]);
                        if (Account.CHECKING_TYPE.equals(parts[1])) {
                            current.addAccount(new CheckingAccount(accountNumber, balance));
                        } else if (Account.SAVINGS_TYPE.equals(parts[1])) {
                            current.addAccount(new SavingsAccount(accountNumber, balance));
                        }
                    }
                }
            }
            log.debug("Đọc xong dữ liệu khách hàng, tổng số: {}", customers.size());
        } catch (IOException e) {
            log.error("Lỗi khi đọc dữ liệu khách hàng: {}", e.getMessage(), e);
        }
    }

    /**
     * Lấy thông tin khách hàng sắp xếp theo số CMND.
     *
     * @return thông tin khách hàng dạng text
     */
    public String getCustomersInfoByIdOrder() {
        log.debug("Bắt đầu sắp xếp khách hàng theo số CMND");
        customers.sort(Comparator.comparingLong(Customer::getIdNumber));
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < customers.size(); i++) {
            result.append(customers.get(i).getCustomerInfo());
            if (i < customers.size() - 1) {
                result.append("\n");
            }
        }
        log.debug("Sắp xếp xong theo số CMND, số lượng: {}", customers.size());
        return result.toString();
    }

    /**
     * Lấy thông tin khách hàng sắp xếp theo họ tên.
     *
     * @return thông tin khách hàng dạng text
     */
    public String getCustomersInfoByNameOrder() {
        log.debug("Bắt đầu sắp xếp khách hàng theo họ tên");
        List<Customer> sorted = new ArrayList<>(customers);
        sorted.sort(Comparator
                .comparing(Customer::getFullName)
                .thenComparingLong(Customer::getIdNumber));
        StringBuilder result = new StringBuilder();
        for (Customer customer : sorted) {
            result.append(customer.getCustomerInfo()).append("\n");
        }
        log.debug("Sắp xếp xong theo họ tên, số lượng: {}", sorted.size());
        return result.toString().trim();
    }
}
