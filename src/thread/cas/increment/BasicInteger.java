package thread.cas.increment;

/*
  value 값은 인스턴스의 필드이기 때문에, 여러 스레드가 공유할 수 있는데 이렇게 공유 가능한 자원에 ++ 와 같은 원자적이지 않은 연산을 사용하면 멀티스레드 상황에서 문제가 될 수 있음
*/

public class BasicInteger implements IncrementInteger{

    private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
