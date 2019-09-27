package ru.petrov.lab6.transactions.managers;

import ru.petrov.lab6.exceptions.TransactionFailedException;
import ru.petrov.lab6.transactions.Account;
import ru.petrov.lab6.transactions.Transaction;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class AsynchronousTransactionManager implements TransactionManager {

    private System.Logger logger;

    private class TransactionTask extends RecursiveAction {

        private int ind;

        private TransactionTask(int ind) {
            this.ind = ind;
        }

        @Override
        protected void compute() {
            if (ind > queues.size()) {
                return;
            }
            TransactionTask task = new TransactionTask(ind + 1);
            task.fork();

            Queue<Transaction> queue = queues.get(ind);
            while (!queue.isEmpty()) {
                Transaction transaction = queue.poll();
                for (int i = 0; i < MAX_ATTEMPTS; i++) {
                    try {
                        transaction.completeTransaction();
                        logger.log(System.Logger.Level.INFO, transaction.getId() + " executed");
                        break;
                    } catch (TransactionFailedException e) {
                        helpQuiesce();
                    }
                }
                if (!transaction.isCompleted()) {
                    logger.log(System.Logger.Level.WARNING, transaction.getId() + " not executed");
                }
            }
        }
    }

    private static final int MAX_ATTEMPTS = 30;

    private ConcurrentMap<Account, Queue<Transaction>> transactions;
    private List<Queue<Transaction>> queues;

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
        queues = new ArrayList<>(this.transactions.values());
    }

    @Override
    public synchronized void completeTransactions() {
        ForkJoinPool executor = (ForkJoinPool) Executors.newWorkStealingPool(queues.size());
        logger = System.getLogger("Transactions");
        executor.invoke(new TransactionTask(0));
        executor.awaitQuiescence(Long.MAX_VALUE, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
