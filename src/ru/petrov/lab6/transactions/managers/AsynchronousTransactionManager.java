package ru.petrov.lab6.transactions.managers;

import ru.petrov.lab6.exceptions.TransactionFailedException;
import ru.petrov.lab6.transactions.Account;
import ru.petrov.lab6.transactions.Transaction;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousTransactionManager implements TransactionManager {

    private static final int MAX_ATTEMPTS = 30;
    private ConcurrentMap<Account, Queue<Transaction>> transactions;

    public AsynchronousTransactionManager(List<Transaction> transactions) {
        this.transactions = new ConcurrentHashMap<>();
        transactions.forEach(transaction -> this.transactions.compute(
            transaction.getSender(),
            (account, queue) -> {
                if (queue == null) {
                    queue = new ArrayDeque<>();
                }
                queue.add(transaction);
                return queue;
            })
        );
    }

    @Override
    public synchronized void completeTransactions() {
        ExecutorService executor = Executors.newCachedThreadPool();

        System.Logger logger = System.getLogger("Transactions");
        transactions.forEach((account, queue) -> {
            executor.execute(() -> {
                while (!queue.isEmpty()) {
                    Transaction transaction = queue.poll();
                    for (int i = 0; i < MAX_ATTEMPTS; i++) {
                        try {
                            transaction.completeTransaction();
                            logger.log(System.Logger.Level.INFO, transaction.getId() + " executed");
                            break;
                        } catch (TransactionFailedException e) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    if (!transaction.isCompleted()) {
                        logger.log(System.Logger.Level.WARNING, transaction.getId() + " not executed");
                    }
                }
            });
        });

        executor.shutdown();
    }
}
