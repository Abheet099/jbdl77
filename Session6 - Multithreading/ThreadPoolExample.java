import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (i == 0) {
                Date d = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                System.out.println("Initialization with " + Thread.currentThread().getName() + " for " + name + " at " + ft.format(d));
            } else {
                Date d = new Date();
                SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                System.out.println("Execution with " + Thread.currentThread().getName() + " for " + name + " at " + ft.format(d));
            }
            try {
                // intentional delay of 300 milli secs to see task behaviour
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class ThreadPoolExample {

    public static void main(String[] args) {
        Runnable r1 = new Task("Task 1");
        Runnable r2 = new Task("Task 2");
        Runnable r3 = new Task("Task 3");
        Runnable r4 = new Task("Task 4");
        Runnable r5 = new Task("Task 5");

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        fixedThreadPool.execute(r1);
        fixedThreadPool.execute(r2);
        fixedThreadPool.execute(r3);
        fixedThreadPool.execute(r4);
        fixedThreadPool.execute(r5);

        Future<Object> futureObject = fixedThreadPool.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        });

        // fetch result of computation
//        futureObject.get();

        // invoke all submitted tasks
//        fixedThreadPool.invokeAll();

        fixedThreadPool.shutdown();
    }
}
