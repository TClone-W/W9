package bank_system;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cho lớp Customer.
 */
class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(9001L, "Nguyen Van A");
    }

    @Test
    void testGetCustomerInfo() {
        String info = customer.getCustomerInfo();
        assertTrue(info.contains("9001"));
        assertTrue(info.contains("Nguyen Van A"));
    }

    @Test
    void testAddAccount() {
        Account account = new CheckingAccount(1L, 100.0);
        customer.addAccount(account);
        assertEquals(1, customer.getAccountList().size());
        assertTrue(customer.getAccountList().contains(account));
    }

    @Test
    void testAddDuplicateAccount() {
        Account account = new CheckingAccount(1L, 100.0);
        customer.addAccount(account);
        customer.addAccount(account);
        assertEquals(1, customer.getAccountList().size());
    }

    @Test
    void testRemoveAccount() {
        Account account = new CheckingAccount(1L, 100.0);
        customer.addAccount(account);
        customer.removeAccount(account);
        assertEquals(0, customer.getAccountList().size());
    }

    @Test
    void testAddNullAccount() {
        customer.addAccount(null);
        assertEquals(0, customer.getAccountList().size());
    }
}