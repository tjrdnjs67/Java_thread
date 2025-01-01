package thread.collection.java;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListMain {

    public static void main(String[] args) {
        List<Integer> list = new CopyOnWriteArrayList<>();  // ArrayList 동시성 대안 컬렉션
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println("list = " + list);
    }
}
