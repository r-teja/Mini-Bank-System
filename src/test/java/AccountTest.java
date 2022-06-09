import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    @Test
    void checkInitialBalance(){
        Account user=new Account();
        assertEquals("0D 0C", user.checkBalance());
    }

    @Test
    void checkCreditBalance(){
        Account user=new Account();
        user.creditBalance("5D 3C");
        user.creditBalance("3D 40C");
        assertEquals("8D 43C", user.checkBalance());
    }

    @Test
    void checkCreditBalanceWhenDepositedMoreThan100Dollars(){
        Account user=new Account();
        user.creditBalance("100D 20C");
        user.creditBalance("1000C");
        user.creditBalance("1000D");
        assertEquals("100D 20C", user.checkBalance());
    }

    @Test
    void checkDebitBalance(){
        Account user=new Account();
        user.debitBalance("100C");
        user.debitBalance("200D");
        assertEquals("-200D -100C", user.checkBalance());

    }

    @Test
    void checkDebitBalanceWhenWithdrawnMoreThan100Dollars(){
        Account user=new Account();
        user.debitBalance("1000D");
        user.debitBalance("200C");
        user.debitBalance("30D");
        assertEquals("-1030D -200C", user.checkBalance());
    }

    @Test
    void checkBalanceWithNegatives(){
        Account user=new Account();
        user.creditBalance("-100D -20C");
        user.debitBalance("-100D -10C");
        user.debitBalance("300D -60C");
        assertEquals("-300D 50C", user.checkBalance());
    }

    @Test
    void checkConversionFromAccountToDenominations(){
        Account user=new Account();
        String msg= "";

        try {
            assertArrayEquals(new int[]{10, 3}, user.convertDollarsAndCents("10D 3C"));
            assertArrayEquals(new int[]{0, 0}, user.convertDollarsAndCents("30C 20D"));
            assertArrayEquals(new int[]{0, 30}, user.convertDollarsAndCents("30C"));
            assertArrayEquals(new int[]{30, 0}, user.convertDollarsAndCents("30D 0C"));
            assertArrayEquals(new int[]{-20, -30}, user.convertDollarsAndCents("-20D -30C"));
            assertArrayEquals(new int[]{-10, 100}, user.convertDollarsAndCents("-10D 100C"));
            assertArrayEquals(new int[]{1000, -2000}, user.convertDollarsAndCents("1000D -2000C"));
            assertArrayEquals(new int[]{0, 0}, user.convertDollarsAndCents(""));
        }
        catch(Exception e){
            msg=e.getMessage();
        }
        assertTrue(msg.equals("Improper denominations for the given amount") ||  msg.equals("Improper input format"));
    }
}