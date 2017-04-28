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

    private static BlockingQueue<String> queue;

    Callback(BlockingQueue<String> queue) {
        Callback.queue = queue;
    }

    public synchronized static void callback(byte[] pData) {
        String pDataStr = new String(pData);
        log.info(pDataStr);
        queue.offer(pDataStr);
    }

    public static BlockingQueue<String> getQueue() {
        return queue;
    }
}
