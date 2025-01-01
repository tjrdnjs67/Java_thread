package thread.collection.simple;

import java.util.ArrayList;
import java.util.List;

/*
  add() 메서드를 생각해보면, 단순히 컬렉션에 데이터를 하나 추가하는 것 뿐 원자적 연산으로 멀티스레드 상황에서 문제 X
  물론 [A, B], [B, A] 와 같이 저장 순서는 변경 될 수 있지만 데이터는 모두 안전하게 저장됨

  BUT 컬렉션 프레임워크가 제공하는 대부분의 연산은 원자적인 연산이 아님
 */

public class SimpleListMainV0 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        // 스레드1, 스레드2가 동시에 실행 가정
        list.add("A"); // 스레드1 실행 가정
        list.add("B"); // 스레드2 실행 가정
        System.out.println(list);
    }
}
