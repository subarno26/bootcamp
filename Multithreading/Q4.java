package Multithreading;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Process1 implements Runnable{

    public void run() {
        for (int i=0; i<100;i=i+2){
            System.out.println(i);
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
        }
        System.out.println("\n");

        for (int i=1;i<100;i=i+2){
            System.out.println(i);
        }
    }
}




public class Q4 {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);


        executor.submit(new Process1());

        executor.shutdown();

        System.out.println("All tasks submitted.");

        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
        }

        System.out.println("All tasks completed.");
    }
}