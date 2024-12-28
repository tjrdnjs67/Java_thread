package thread.bounded;

public interface BoundedQueue {
    // 생산자
    void put(String data);
    // 소비자
    String take();
}
