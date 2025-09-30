import java.time.LocalDateTime;

class BankAccount {
    String ownerName;
    int balance;
    LocalDateTime openDate;
    boolean isBlocked;

    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
        this.openDate = LocalDateTime.now();
        this.isBlocked = false;
    }

    public boolean deposit(int amount) {
        if (amount > 0 && !isBlocked) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(int amount) {
        if (amount > 0 && balance >= amount && !isBlocked) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount otherAccount, int amount) {
        if (this.withdraw(amount)) {
            if (otherAccount.deposit(amount)) {
                return true;
            } else {
                this.deposit(amount);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("Глеб");
        BankAccount acc2 = new BankAccount("Толик");

        acc1.deposit(1000);
        System.out.println("У Глеба: " + acc1.balance);

        acc1.withdraw(300);
        System.out.println("После снятия 300: " + acc1.balance);

        acc1.transfer(acc2, 200);
        System.out.println("Глеб перевел 200 Толику");
        System.out.println("У Глеба: " + acc1.balance);
        System.out.println("У Толика: " + acc2.balance);

        boolean result = acc1.withdraw(1000);
        System.out.println("Снятие 1000: " + result);
        System.out.println("Баланс Глеба: " + acc1.balance);
    }
}