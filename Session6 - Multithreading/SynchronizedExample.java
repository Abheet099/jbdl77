class Sender {
    synchronized void send(String message){
        try {
            System.out.println("Before Sleep "+Thread.currentThread()+" - "+System.currentTimeMillis());
            Thread.sleep(300);
            System.out.println("After Sleep "+Thread.currentThread()+" - "+System.currentTimeMillis());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n" + message + " sent");

        // synchronized block
        synchronized (this){

        }
    }

}

class ThreadSend extends Thread {
    private String message;
    Sender sender;

    ThreadSend(String msg, Sender sender) {
        this.message = msg;
        this.sender = sender;
    }

    public void run() {
        sender.send(message);
    }

}

public class SynchronizedExample {
    public static void main(String[] args) {

        Sender sender = new Sender();
        ThreadSend t1 = new ThreadSend("HI ", sender);
        ThreadSend t2 = new ThreadSend("Bye ", sender);
        t1.start();
        t2.start();
    }

}
