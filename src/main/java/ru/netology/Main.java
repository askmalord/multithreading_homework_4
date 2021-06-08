package ru.netology;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {
    public static final int COUNT_OF_SPECIALISTS = 15;

    public static void main(String[] args) throws InterruptedException {
        Queue<String> queueOfCalls = new ArrayBlockingQueue<String>(500, true);
        CallGenerator callGenerator = new CallGenerator(queueOfCalls);
        SpecialistThread specialistThread;
        callGenerator.start();
        List<Thread> listOfSpecialist = new ArrayList<>();
        System.out.println("-----start-----");
        for (int i = 0; i < COUNT_OF_SPECIALISTS; i++) {
            specialistThread = new SpecialistThread(queueOfCalls);
            listOfSpecialist.add(specialistThread);
            specialistThread.start();
            System.out.println("специалист " + specialistThread.getName() + " работает");
        }
        callGenerator.join();
        for (int i = 0; i < COUNT_OF_SPECIALISTS; i++) {
            listOfSpecialist.get(i).join();
        }
        System.out.println("-----end-----");
    }
}
