package thread.cas.increment;

/*
  volatile은 여러 CPU 사이에 발생하는 캐시 메모리와 메인 메모리가 동기화 되지 않은 문제를 해결할 뿐
  volatile을 사용하면 CPU의 캐시메모리를 무시하고 메인 메모리를 직접 사용하도록 함

  이 문제는 연산 자체가 원자적 연산이 아닌 연산 자체가 나누어져 있기 때문에 발생하는 문제로 메인 메모리를 직접 사용해도 여전히 문제가 발생함
  volatile은 연산 자체를 원자적으로 묶어주는 기능이 아님
*/

public class VolatileInteger implements IncrementInteger{

    volatile private int value;

    @Override
    public void increment() {
        value++;
    }

    @Override
    public int get() {
        return value;
    }
}
