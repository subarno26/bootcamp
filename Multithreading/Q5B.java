package Multithreading;

public class Q5B {
    public static void main(String[] args) throws InterruptedException {
        final Q5A process = new Q5A();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                }catch (InterruptedException e){

                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consumer();
                }catch (InterruptedException e){

                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

    }
}