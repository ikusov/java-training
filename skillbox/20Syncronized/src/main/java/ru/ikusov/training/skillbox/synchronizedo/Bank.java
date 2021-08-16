package ru.ikusov.training.skillbox.synchronizedo;

import java.util.HashMap;

import static ru.ikusov.training.utils.Console.p;
import static ru.ikusov.training.utils.MyMath.r;

public class Bank {

    //bank constructor accepts accounts count and money amount on every account
    public Bank(int accountsCount, long accountSum) {
        this.accounts = new HashMap<>();
        for(int i=0; i<accountsCount; i++) {
            accounts.put(i, new Account(i, accountSum));
        }
    }

    //collection of accounts in the bank
    private final HashMap<Integer, Account> accounts;

    //locker for synchronizer of transaction fraud checking
    private final Object securityLock = new Object();

    //synchronized transaction method
    public TransferStatus transfer(Integer fromAccNum, Integer toAccNum, long sum) throws InterruptedException {
        Account fromAcc = accounts.get(fromAccNum);
        Account toAcc = accounts.get(toAccNum);

        if(fromAcc == null || toAcc == null) throw new NullPointerException("Attempt of null account transaction is detected!!!");

        if (fromAccNum.equals(toAccNum)) return TransferStatus.STATUS_SAMEPERSUN;

        if (fromAccNum < toAccNum) {
            synchronized (fromAcc) { synchronized (toAcc) {
                return asyncTransfer(fromAcc, toAcc, sum);
            }}
        } else {
            synchronized (toAcc) {synchronized (fromAcc) {
                return asyncTransfer(fromAcc, toAcc, sum);
            }}
        }
    }

    //transaction without sync
    private TransferStatus asyncTransfer (Account fromAcc, Account toAcc, long sum) throws InterruptedException {

        //ifs beard
        if(fromAcc.getMoney() < sum) return TransferStatus.STATUS_INSUFFICIENT_WEALTH;
        if(fromAcc.isLocked() || toAcc.isLocked()) return TransferStatus.STATUS_ACCOUNT_LOCKED;
        if(sum>50_000 && isFraud()) {
            fromAcc.setLocked();
            toAcc.setLocked();
            return TransferStatus.STATUS_FRAUD;
        }

        //gimme yo money
        fromAcc.changeMoney(-sum);
        toAcc.changeMoney(sum);

        return TransferStatus.STATUS_SUCCESSFUL;
    }

    public Integer getAccountsCount() {
        return accounts.size();
    }

    public long getBalance(Integer accNum) {
        Account account = accounts.get(accNum);
        long money;

        if(account == null) {
            throw new NullPointerException("Attempt to get balance from unexisting account number " + accNum);
        }
        synchronized (account) {
            money = account.getMoney();
            TransferStatus.moneyChecked += money;
        }
        return money;
    }

    //return all the money amount in the bank
    public long vaBank() {
        return accounts.keySet().stream()
                .map(this::getBalance)
                .reduce(Long::sum)
                .get();
    }

    private boolean isFraud() throws InterruptedException {
        synchronized (securityLock) {
            Thread.sleep(1000);
        }
        TransferStatus.fraudChecksCount++;
        return r(100)<5;
    }

    @Override
    public String toString() {
        return accounts.keySet().stream()
                .map(key -> accounts.get(key).toString())
                .reduce((s1, s2) -> String.join("\n", s1, s2))
                .get();
    }

    //transfer status enum with statistic counters
    public enum TransferStatus {
        STATUS_SUCCESSFUL,
        STATUS_SAMEPERSUN,
        STATUS_INSUFFICIENT_WEALTH,
        STATUS_ACCOUNT_LOCKED,
        STATUS_FRAUD;

        public static long successfulTransfersCount = 0,
                            samepersunTransfersCount = 0,
                            insufficientWealthTransfersCount = 0,
                            lockedAccountTransfersCount = 0,
                            fraudTransfersCount = 0,
                            fraudChecksCount = 0,
                            moneyChecked = 0,
                            moneyTransferred = 0;
    }

    //random transaction thread class
    public class RandomTransaction implements Runnable {
        private final long maxTransactionSum;

        RandomTransaction(long maxTransactionSum) {
            this.maxTransactionSum = maxTransactionSum;
        }

        @Override
        public void run() {
                int accountsCount = getAccountsCount();
                Integer accFrom = r(accountsCount);
                Integer accTo = r(accountsCount);
                long sum = r(maxTransactionSum);

                if (r(10)<5) {
                    randomTransfer(accFrom, accTo, sum);
                } else {
                    getBalance(accFrom);
                }
        }

        private void randomTransfer(Integer accFrom, Integer accTo, long sum) {
            try {
                TransferStatus status = transfer(accFrom, accTo, sum);

                switch(status) {
                    case STATUS_SUCCESSFUL -> {
                        TransferStatus.successfulTransfersCount++;
                        TransferStatus.moneyTransferred += sum;
                    }
                    case STATUS_FRAUD -> TransferStatus.fraudTransfersCount++;
                    case STATUS_ACCOUNT_LOCKED -> TransferStatus.lockedAccountTransfersCount++;
                    case STATUS_INSUFFICIENT_WEALTH -> TransferStatus.insufficientWealthTransfersCount++;
                    case STATUS_SAMEPERSUN -> TransferStatus.samepersunTransfersCount++;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
