package thread.collection.java;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class MapMain {

    public static void main(String[] args) {
        Map<Integer,String> map1 = new ConcurrentHashMap<>(); // HashMap 동시성 대안 컬렉션, 순서 보장 x
        map1.put(3, "data3");
        map1.put(2, "data2");
        map1.put(1, "data1");
        System.out.println("map1 = " + map1);

        Map<Integer,String> map2 = new ConcurrentSkipListMap<>(); // TreeMap 동시성 대안 컬렉션, 데이터 정렬 순서 유지, Comparator 사용 가능
        map1.put(2, "data2");
        map1.put(3, "data3");
        map1.put(1, "data1");
        System.out.println("map1 = " + map1);

        /* LinkedHashMap 처럼 입력 순서를 유지하는 동시에 멀티스레드 환경에서 사용할 수 있는
           Map은 구현체 제공 X  필요하다면 Collections.synchronizedXxx() 를 사용   */
    }
}
