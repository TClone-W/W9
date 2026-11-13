package bank_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cho lớp CheckingAccount.
 */
class CheckingAccountTest {

    private CheckingAccount checkingAccount;

    @BeforeEach
    void setUp() {
        checkingAccount = new CheckingAccount(2001L, 1000.0);
    }

    @Test
    void testDeposit() throws BankException {
        checkingAccount.deposit(500.0);
        assertEquals(1500.0, checkingAccount.getBalance(), 0.001);
    }

    @Test
    void testWithdraw() throws BankException {
        checkingAccount.withdraw(300.0);
        assertEquals(700.0, checkingAccount.getBalance(), 0.001);
    }

    @Test
    void testWithdrawInsufficientFunds() {
        checkingAccount.withdraw(999999.0);
        assertEquals(1000.0, checkingAccount.getBalance(), 0.001);
    }

    @Test
    void testTypeIsChecking() {
        assertEquals(Account.CHECKING_TYPE, checkingAccount.getType());
    }
}