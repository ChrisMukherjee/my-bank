package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void testCustomerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(Account.AccountType.CHECKING));
        john.openAccount(new Account(Account.AccountType.SAVINGS));
        Customer fred = new Customer("Fred");
//        fred.openAccount(new Account(Account.AccountType.MAXI_SAVINGS));
        fred.openAccount(new Account(Account.AccountType.SAVINGS));
        bank.addCustomer(john);
        bank.addCustomer(fred);

        assertEquals("Customer Summary\n" +
        		" - John (2 accounts)\n" +
        		" - Fred (1 account)", bank.customerSummary());
    }

    @Test
    public void testCheckingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.AccountType.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(1000.0);

        assertEquals(1.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSavingsAccount_lt1000() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.AccountType.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(500.0);

        assertEquals(0.5, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSavingsAccount_gt1000() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.AccountType.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSuperSavingsAccount_lt1000() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.AccountType.SUPER_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(500.0);

        assertEquals(10.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSuperSavingsAccount_lt2000() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.AccountType.SUPER_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(45.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void testSuperSavingsAccount_gt2000() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(Account.AccountType.SUPER_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

}
