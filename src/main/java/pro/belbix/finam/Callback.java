package pro.belbix.finam;

import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Belykh Vsevolod on 23.04.2017.
 */
class Callback {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Callback.class);

    private static BlockingQueue<String> queue = null;

    private static volatile Callback instance;

    static Callback getInstance(BlockingQueue<String> queue) {
        Callback localInstance = instance;
        if (localInstance == null) {
            synchronized (Callback.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Callback(queue);
                }
            }
        }
        return localInstance;
    }


    private Callback(BlockingQueue<String> queue) {
        Callback.queue = queue;
    }

    public synchronized static void callback(byte[] pData) {
        if (pData != null){
            instance.putInQueue(pData);
        }

    }

    private void putInQueue(byte[] msg){
        if (queue != null) {
            queue.offer(new String(msg));
        }
    }

    public static BlockingQueue<String> getQueue() {
        return queue;
    }
}
