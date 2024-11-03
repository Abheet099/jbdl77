import jdk.nashorn.internal.ir.WhileNode;

public class WaitNotifyExample {
    // starting counter
    int counter = 1;

    static int N;

    public void printOddNumber() {
        synchronized (this) {
            while (counter < N) {
                while (counter % 2 == 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // print odd number
                System.out.println(counter + " ");
                counter++;
                notify();
            }
        }
    }

    public void printEvenNumber() {
        synchronized (this) {
            while (counter < N) {
                while (counter % 2 == 1) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                // print even number
                System.out.println(counter + " ");
                counter++;

                // notify to other thread
                notify();
            }
        }
    }


    public static void main(String[] args) {
        N = 10;
        WaitNotifyExample waitNotifyExampleObj = new WaitNotifyExample();

        // thread to print even number
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                waitNotifyExampleObj.printEvenNumber();
            }
        });

        // thread to print odd number
        Thread t2 = new Thread(waitNotifyExampleObj::printOddNumber);

        t1.start();
        t2.start();

    }
}
