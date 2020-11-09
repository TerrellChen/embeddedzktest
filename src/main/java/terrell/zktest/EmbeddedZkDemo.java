package terrell.zktest;
/**
 * @author: TerrellChen
 * @version: Created in 21:48 2020-11-09
 */


import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description:
 */
public class EmbeddedZkDemo {
    public static void main(String[] args) throws Exception {
        System.out.println("Service started");

        ZKUtils.startZkCluster(args[0], Integer.valueOf(args[1]));


        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            private volatile boolean hasShutdown = false;
            private AtomicInteger shutdownTimes = new AtomicInteger(0);

            @Override
            public void run() {
                synchronized (this) {
                    System.out.println("Shutdown hook was invoked, " + this.shutdownTimes.incrementAndGet());
                    if (!this.hasShutdown) {
                        this.hasShutdown = true;
                        long beginTime = System.currentTimeMillis();
                        long consumingTimeTotal = System.currentTimeMillis() - beginTime;
                        System.out.println("Shutdown hook over, consuming total time(ms): " + consumingTimeTotal);
                    }
                }
            }
        }, "ShutdownHook"));
    }
}
