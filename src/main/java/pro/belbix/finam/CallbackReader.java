package pro.belbix.finam;

import org.slf4j.LoggerFactory;
import pro.belbix.finam.Callback;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Belykh Vsevolod on 28.04.2017.
 */
public class CallbackReader implements Runnable {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CallbackReader.class);
    boolean run = true;
    boolean print = true;
    private BlockingQueue<String> queue = null;
    private CallbackWriter writer =  new CallbackWriter();

    public CallbackReader(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String str = null;
        while (run) {
            try {
                if (queue != null) {
                    str = queue.take();
                }
//                Thread.sleep(1000);
            } catch (Exception e) {
                log.error("CallbackReader error:", e);
            }
            if (str != null && print) {
//                log.info(str);
                writer.write(str);
            }
        }
    }
}
