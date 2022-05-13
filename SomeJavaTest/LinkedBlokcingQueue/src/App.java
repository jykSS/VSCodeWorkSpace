import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class App {

    public static void main(String args[]) {
        BlockingQueue lBlockingQueue = new LinkedBlockingQueue<>(5);
        new Thread(new ProducerQueue(lBlockingQueue)).start();
        new Thread(new ConsumerQueue(lBlockingQueue)).start();
    }

    /**
     * ProducerQueue
     */
    static class ProducerQueue implements Runnable {
        private BlockingQueue producerQueue;

        public ProducerQueue(BlockingQueue producerQueue) {
            this.producerQueue = producerQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("当前生产第" + i + "个数据");
                    producerQueue.put(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     * ConsumerQueue
     */
    static class ConsumerQueue implements Runnable {
        private BlockingQueue consumerQueue;

        public ConsumerQueue(BlockingQueue consumerQueue) {
            this.consumerQueue = consumerQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("当前消费的第" + i + "个数据");
                    consumerQueue.take();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }
}