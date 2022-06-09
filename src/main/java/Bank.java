public class Bank {
    public static void main(String args[]){
        Account user=new Account();
        user.checkBalance();
        user.creditBalance("5D 3C");
        user.creditBalance("3D 40C");
        System.out.println(user.checkBalance());
        user.creditBalance("100D 20C");
        user.creditBalance("1000C");
        user.creditBalance("1000D");
        System.out.println(user.checkBalance());
        user.debitBalance("100C");
        user.debitBalance("200D");
        user.debitBalance("1000D");
        user.debitBalance("-100D -100C");
        System.out.println(user.checkBalance());
        user.creditBalance("-100D -20C");
        user.debitBalance("-100D -10C");
        System.out.println(user.checkBalance());
        user.debitBalance("300D -60C");
        System.out.println(user.checkBalance());
    }
}
