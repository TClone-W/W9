package bank_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cho lớp SavingsAccount.
 */
class SavingsAccountTest {

    private SavingsAccount savingsAccount;

    @BeforeEach
    void setUp() {
        savingsAccount = new SavingsAccount(3001L, 10000.0);
    }

    @Test
    void testDeposit() throws BankException {
        savingsAccount.deposit(2000.0);
        assertEquals(12000.0, savingsAccount.getBalance(), 0.001);
    }

    @Test
    void testWithdrawWithinLimit() throws BankException {
        savingsAccount.withdraw(500.0);
        assertEquals(9500.0, savingsAccount.getBalance(), 0.001);
    }

    @Test
    void testWithdrawExceedsMaxAmount() {
        savingsAccount.withdraw(2000.0);
        assertEquals(10000.0, savingsAccount.getBalance(), 0.001);
    }

    @Test
    void testWithdrawBelowMinBalance() {
        savingsAccount.withdraw(6000.0);
        assertEquals(10000.0, savingsAccount.getBalance(), 0.001);
    }

    @Test
    void testTypeIsSavings() {
        assertEquals(Account.SAVINGS_TYPE, savingsAccount.getType());
    }
}