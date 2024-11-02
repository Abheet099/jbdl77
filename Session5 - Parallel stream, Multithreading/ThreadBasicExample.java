// 1) creating thread by extending Thread class
class ThreadA extends Thread {
    public void run() {
        System.out.println("ThreadA: " + Thread.currentThread());
        for (int i = 20; i > 0; i--) {
            System.out.println("ThreadA class with i = " + i);
        }
    }
}

class ThreadB extends Thread {
    public void run() {
        System.out.println("ThreadB: " + Thread.currentThread());
        for (int i = 20; i > 0; i--) {
            System.out.println("ThreadB class with i = " + i);
        }
    }
}

class ThreadRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("ThreadRunnable: " + Thread.currentThread());
        for (int i = 20; i > 0; i--) {
            System.out.println("ThreadRunnable class with i = " + i);
        }
    }
}

public class ThreadBasicExample {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ThreadBasicExample " + Thread.currentThread());

        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();

        // 2) Create thread using runnable interface
        ThreadRunnable threadC = new ThreadRunnable();
        Thread t1 = new Thread(threadC);

        threadA.start();
        threadA.setPriority(10);
//        threadA.setDaemon(true);

        threadA.join();
        threadB.start();
        t1.start();

        // calling start method twice
//        threadA.start();
//        threadA.start();

        threadB.run();
        threadB.run();
        t1.run();
    }
}
