package thread.collection.simple.list;

import static util.MyLogger.log;

public class SimpleListMainV2 {

    public static void main(String[] args) throws InterruptedException {
        //test(new BasicList());
        //test(new SyncList());

        BasicList basicList = new BasicList();
        SyncProxyList syncProxyList = new SyncProxyList(basicList);
        test(syncProxyList);
    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1: list.add(A)");
            }
        };

        Runnable addB = new Runnable() {
            @Override
            public void run() {
                list.add("B");
                log("Thread-2: list.add(B)");
            }
        };

        Thread th1 = new Thread(addA, "Thread-1");
        Thread th2 = new Thread(addB, "Thread-2");

        th1.start();
        th2.start();
        th1.join();
        th2.join();

        log(list);
    }
}
