package Multithreading;

class Runner implements Runnable{
    @Override
    public void run() {
        for (int i=0;i<50;i++){
            System.out.println("Itertation: "+ i);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class Q2A {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());

        t1.start();
        t2.start();
    }
}