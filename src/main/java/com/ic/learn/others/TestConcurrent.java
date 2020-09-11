package com.ic.learn.others;

import java.util.concurrent.*;

public class TestConcurrent {
    public static void main(String[] args) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(2);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                Future<Object> task = executorService.submit(new Callable() {
                    @Override
                    public String call() {
                        return Thread.currentThread().getName() + "  " + System.currentTimeMillis();
                    }
                });
                try {
                  String result = (String) task.get();
                    System.out.println(result);
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private final ConcurrentHashMap<Object, Future<String>> taskCache = new ConcurrentHashMap<>();

    private String executionTask(final String taskName) {
        while (true) {
            Future<String> future = taskCache.get(taskName);
            if (future == null) {
                Callable<String> task = new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return taskName;
                    }
                };
                FutureTask<String> futureTask = new FutureTask<>(task);
                future = taskCache.putIfAbsent(taskName, futureTask);
                if (future == null) {
                    future = futureTask;
                    futureTask.run();
                }
            }

            try {
                return future.get();
            } catch (Exception e) {
                taskCache.remove(taskName, future);
            }

        }
    }
}
