package bank_system;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cho lớp Transaction.
 */
class TransactionTest {

    @Test
    void testGetTransactionSummary() {
        Transaction transaction = new Transaction(
                Transaction.TYPE_DEPOSIT_CHECKING,
                200.0,
                500.0,
                700.0);

        String summary = transaction.getTransactionSummary();
        assertTrue(summary.contains("200.00"));
        assertTrue(summary.contains("500.00"));
        assertTrue(summary.contains("700.00"));
    }

    @Test
    void testGetTypeStringDepositChecking() {
        assertEquals("Nạp tiền vảng lai", Transaction.getTypeString(Transaction.TYPE_DEPOSIT_CHECKING));
    }

    @Test
    void testGetTypeStringWithdrawChecking() {
        assertEquals("Rút tiền vảng lai", Transaction.getTypeString(Transaction.TYPE_WITHDRAW_CHECKING));
    }

    @Test
    void testGetTypeStringDepositSavings() {
        assertEquals("Nạp tiền tiết kiệm", Transaction.getTypeString(Transaction.TYPE_DEPOSIT_SAVINGS));
    }

    @Test
    void testGetTypeStringWithdrawSavings() {
        assertEquals("Rút tiền tiết kiệm", Transaction.getTypeString(Transaction.TYPE_WITHDRAW_SAVINGS));
    }
}
