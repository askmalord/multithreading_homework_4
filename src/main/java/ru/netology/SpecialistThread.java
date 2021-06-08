package ru.netology;

import java.util.Queue;

public class SpecialistThread extends Thread {
    private Queue<String> queueForProcessing;
    private static final int CALL_PROCESSING_TIME = 3000;
    public static int totalCountOfCalls = 0;

    public SpecialistThread(Queue<String> queueForProcessing) {
        this.queueForProcessing = queueForProcessing;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            if (totalCountOfCalls > 150) {
                interrupt();
            }
            String call = queueForProcessing.poll();
            if (call == null) {
                System.out.println("очередь пуста, ожидание звонков");
            } else {
                try {
                    Thread.sleep(CALL_PROCESSING_TIME);
                    System.out.println("Звонок " + call
                        + " обработан специалистом " + currentThread().getName());
                    totalCountOfCalls++;
                } catch (InterruptedException e) {
                    e.getStackTrace();
                }
            }
        }
    }
}
