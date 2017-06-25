package pro.belbix.finam;

import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * Created by Belykh Vsevolod on 25.06.2017.
 */
public class DefaultInit {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(DefaultInit.class);
    private TXmlConnector64 con;
    private Callback cb;
    private String logPath;
    private int logLevel;

    public DefaultInit(TXmlConnector64 con, BlockingQueue<String> queue) {
        this(con, queue, "C:\\log\\", 3);
    }

    public DefaultInit(TXmlConnector64 con, BlockingQueue<String> queue, String logPath, int logLevel) {
        this.con = con;
        this.cb = Callback.getInstance(queue);
        this.logPath = logPath;
        this.logLevel = logLevel;
    }

    public boolean start() {
        boolean success;
        int fail_count = 0;
        int try_count = 3;
        do {
//            success = con.initDll(TXmlConnector64.class.getClassLoader().getResource("txcn64.dll").getPath());
            success = con.initDllSimple();
            log.info("initDll:" + success);
            if (!success) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            }
            fail_count++;
        } while (!success && fail_count < try_count);
        if (fail_count >= try_count) {
            return false;
        }

        String initResStr = new String(con.Initialize(logPath, logLevel));
        log.info("Initialize:" + initResStr);
        if (initResStr.contains("<error>")) {
            return false;
        }
        log.info("SetCallback:" + con.SetCallback(cb));
        return true;
    }

    public void stop() {
        String unInitResStr = new String(con.UnInitialize());
        log.info("UnInitialize:" + unInitResStr);
    }

}
