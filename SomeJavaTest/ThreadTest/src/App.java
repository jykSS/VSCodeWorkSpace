import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {
    private static Lock lock = new ReentrantLock();// 通过JDK5中的Lock锁来保证线程的访问的互斥
    private static int state = 0;//通过state的值来确定是否打印
    public static void main(String[] args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10;) {
                    lock.lock();
                    try {
                        while (state % 3 == 0) {
                        System.out.println("A");
                        state++;
                        i++;
                    }
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
 
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ) {
                    lock.lock();
                    try {
                        while (state % 3 == 1) {
                            System.out.println("B");
                            state++;
                            i++;
                        }
                    } finally {
                        lock.unlock();
                    }
 
                }
 
            }
        }).start();
 
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; ) {
                    lock.lock();
                    try {
                        while (state % 3 == 2) {
                            System.out.println("C");
                            state++;
                            i++;
                        }
                    } finally {
                        lock.unlock();
                    }
 
                }
 
            }
        }).start();
    }
}
