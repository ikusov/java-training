package ru.ikusov.training.skillbox.synchronizedo;

public class Account {
    public Account(Integer accNumber, long money) {
        this.accNumber = accNumber;
        this.money = money;
        this.locked = false;
    }

    private final Integer accNumber;
    public Integer getAccNumber() {
        return accNumber;
    }

    private long money;
    public long getMoney() {
        return money;
    }
    public void setMoney(long money) {
        this.money = money;
    }

    private boolean locked;
    public void setLocked() {
        locked = true;
    }
    public boolean isLocked() {
        return locked;
    }

    public void changeMoney(long sum) {
        setMoney(money + sum);
    }

    @Override
    public String toString() {
        return "Account number: " + accNumber
                + ", account fund: " + money
                + ", account locked: " + (locked?"yes":"no");
    }
}
