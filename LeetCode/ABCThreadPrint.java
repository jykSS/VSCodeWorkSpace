import java.util.concurrent.atomic.AtomicInteger;

public class ABCThreadPrint {
    public static void main(String[] args) {
        AtomicInteger state = new AtomicInteger(0);
        Object Object = new Object();
        new Thread(
                () -> {
                    for (int i =0 ;i<=34;) {
                        synchronized (Object) {
                            if (state.get() % 3 == 0) {
                                System.out.println("A");
                                state.incrementAndGet();
                                i++;
                            }
                        }
                    }
                }).start();
        new Thread(
                () -> {
                    for (int i =0 ;i<34;) {
                        synchronized (Object) {
                            if (state.get() % 3 == 1) {
                                System.out.println("B");
                                state.incrementAndGet();
                                i++;
                            }
                        }
                    }
                }).start();
        new Thread(
                () -> {
                    for (int i =0 ;i<34;) {
                        synchronized (Object) {
                            if (state.get() % 3 == 2) {
                                System.out.println("C");
                                state.incrementAndGet();
                                i++;
                            }
                        }
                    }
                }).start();
    }
}
