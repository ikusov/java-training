package ru.ikusov.training.skillbox.synchronizedo;

import org.junit.Assert;
import org.junit.Test;
import ru.ikusov.training.skillbox.synchronizedo.Bank;

import static org.junit.Assert.*;
import static ru.ikusov.training.utils.Console.p;

public class BankTest {

    public final static int accountsCount = 100;
    public final static long accountSum = 500_000;
    public final static Bank bank = new Bank(accountsCount, accountSum);

    @Test
    public void transferTest() {
        final int THREADS_COUNT = 10000;

        long bankSumExpected = bank.vaBank();

        long startTime = System.currentTimeMillis();

        ThreadGroup threadGroup = new ThreadGroup("transfers");

        for (int i=0; i<THREADS_COUNT; i++) {
            new Thread(threadGroup, bank.new RandomTransaction(accountSum*10/95)).start();
        }

        int activeThreadsCount = threadGroup.activeCount();
        int activeThreadsCountOld = activeThreadsCount;
        while(activeThreadsCount > 0)try {
            activeThreadsCount = threadGroup.activeCount();
            if (activeThreadsCount < activeThreadsCountOld - THREADS_COUNT/100) {
                p(String.format("%d transfers complete! Money transfered: %,d! Money checked: %,d! Fraud checks: %d!",
                        THREADS_COUNT-activeThreadsCount,
                        Bank.TransferStatus.moneyTransferred,
                        Bank.TransferStatus.moneyChecked,
                        Bank.TransferStatus.fraudChecksCount));
                activeThreadsCountOld = activeThreadsCount;
            }
            Thread.sleep(10);
        } catch (InterruptedException e) {}

        long bankSumActual = bank.vaBank();

        p(bank);
        p(String.format("""
                        Money transferred: %,d\s
                        Money checked: %,d\s
                        Successful transfers count: %d\s
                        Transaction fraud checks count: %d\s
                        Fraud transfer attempts count: %d\s
                        The same account transfer attempts count: %d\s
                        Not enough money transfer attempts count: %d\s
                        Locked account transfer attempts count: %d\s
                        Operation time: %,d ms""",
                        Bank.TransferStatus.moneyTransferred,
                        Bank.TransferStatus.moneyChecked,
                        Bank.TransferStatus.successfulTransfersCount,
                        Bank.TransferStatus.fraudChecksCount,
                        Bank.TransferStatus.fraudTransfersCount,
                        Bank.TransferStatus.samepersunTransfersCount,
                        Bank.TransferStatus.insufficientWealthTransfersCount,
                        Bank.TransferStatus.lockedAccountTransfersCount,
                        System.currentTimeMillis() - startTime));

        Assert.assertEquals(bankSumExpected, bankSumActual);

    }
}