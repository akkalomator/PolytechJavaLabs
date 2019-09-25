package ru.petrov.lab6;

import ru.petrov.lab6.exceptions.TransactionFailedException;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AsynchronousTransactionManager implements TransactionManager {

    public static final int MAX_ATTEMPTS = 30;
    private Map<Account, Queue<Transaction>> transactions;

    public AsynchronousTransactionManager(List<Transaction> transactions) {
        this.transactions = new HashMap<>();
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
                queue.forEach(transaction -> {
                    for (int i = 0; i < MAX_ATTEMPTS; i++) {
                        try {
                            transaction.completeTransaction();
                            logger.log(System.Logger.Level.INFO, transaction.getId() + " executed");
                            return;
                        } catch (TransactionFailedException e) {
                            logger.log(System.Logger.Level.INFO, e.getMessage());
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                    logger.log(System.Logger.Level.WARNING, transaction.getId() + " not executed");
                });
            });
        });

        executor.shutdown();
    }
}
