package Multithreading;
import java.util.concurrent.Semaphore;

public class Q8 {
    private static Q8 instance = new Q8();

    private Semaphore sem = new Semaphore(10, true);

    private int connections = 0;

    private Q8() {

    }

    public static Q8 getInstance() {
        return instance;
    }

    public void connect() {
        try {
            sem.acquire();
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
            doConnect();
        } finally {

            sem.release();
        }
    }

    public void doConnect() {

        synchronized (this) {
            connections++;
            System.out.println("Current connections: " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            connections--;
        }

    }
}