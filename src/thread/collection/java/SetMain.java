package thread.collection.java;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetMain {

    public static void main(String[] args) {
        Set<Integer> copySet = new CopyOnWriteArraySet<>();  // HashSet 동시성 대안 컬렉션
        copySet.add(1);
        copySet.add(2);
        copySet.add(3);
        System.out.println("copySet = " + copySet);

        ConcurrentSkipListSet<Integer> skipSet = new ConcurrentSkipListSet<>(); // TreeSet 동시성 대안 컬렉션, 데이터 정렬 순서 유지, Comparator 사용 가능
        skipSet.add(1);
        skipSet.add(2);
        skipSet.add(3);
        System.out.println("skipSet = " + skipSet);

        /* LinkedHashSet 처럼 입력 순서를 유지하는 동시에 멀티스레드 환경에서 사용할 수 있는
           Set은 구현체 제공 X  필요하다면 Collections.synchronizedXxx() 를 사용   */
    }
}
