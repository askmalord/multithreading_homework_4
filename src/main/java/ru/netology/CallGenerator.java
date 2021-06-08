package ru.netology;

import java.util.Queue;

public class CallGenerator extends Thread {
    private static final int COUNT_OF_CALLS_PER_SECOND = 60;
    private static final int WAITING_TIME = 1000;
    private Queue<String> queueOfCalls;
    private static int countOfIter = 0;

    public CallGenerator(Queue<String> queueOfCalls) {
        this.queueOfCalls = queueOfCalls;
    }

    @Override
    public void run() {
        System.out.println("Call generator is starting...");
        try {
            while (!isInterrupted()) {
                if (countOfIter == 5) {
                    interrupt();
                }
                for (int i = 1; i <= COUNT_OF_CALLS_PER_SECOND; i++) {
                    queueOfCalls.add("incoming call # " + i);
                }
                countOfIter++;
                Thread.sleep(WAITING_TIME);
            }
        } catch (InterruptedException e) {
            e.getStackTrace();
        }
    }
}
