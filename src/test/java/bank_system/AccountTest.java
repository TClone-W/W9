package bank_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cho lớp Account (thử qua CheckingAccount vì Account là abstract).
 */
class AccountTest {

    private CheckingAccount account;

    @BeforeEach
    void setUp() {
        account = new CheckingAccount(1001L, 5000.0);
    }

    @Test
    void testGetAccountNumber() {
        assertEquals(1001L, account.getAccountNumber());
    }

    @Test
    void testGetInitialBalance() {
        assertEquals(5000.0, account.getBalance(), 0.001);
    }

    @Test
    void testDoDepositing() throws BankException {
        account.doDepositing(2000.0);
        assertEquals(7000.0, account.getBalance(), 0.001);
    }

    @Test
    void testDoWithdrawing() throws BankException {
        account.doWithdrawing(1500.0);
        assertEquals(3500.0, account.getBalance(), 0.001);
    }

    @Test
    void testGetHistoryInitiallyEmpty() {
        String history = account.getTransactionHistory();
        assertTrue(history.contains("Lịch sử giao dịch"));
    }

    @Test
    void testGetHistoryAfterTransaction() throws BankException {
        account.deposit(500.0);
        String history = account.getTransactionHistory();
        assertFalse(history.isEmpty());
        assertTrue(history.contains("500.00"));
    }

    @Test
    void testGetType() {
        Account checking = new CheckingAccount(1L, 0.0);
        Account savings = new SavingsAccount(2L, 0.0);
        assertEquals(Account.CHECKING_TYPE, checking.getType());
        assertEquals(Account.SAVINGS_TYPE, savings.getType());
    }
}
