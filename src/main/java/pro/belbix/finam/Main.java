package pro.belbix.finam;

import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Belykh Vsevolod on 22.04.2017.
 */
public class Main {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Main.class);
    private TXmlConnector64 con = new TXmlConnector64();
    BlockingQueue<String> queue = new ArrayBlockingQueue<>(100_000);
    private Callback cb = Callback.getInstance(queue);

    private static Command connect = new Command();
    private static Command disconnect = new Command();

    static {
        connect.initRoot("command");
        connect.setRootAttribute("id","connect");
        connect.addElemet("login","TCNN9964");
        connect.addElemet("password","v8JUF8");
        connect.addElemet("host","tr1-demo5.finam.ru");
        connect.addElemet("port","3939");
        connect.addElemet("autopos","false");
        connect.addElemet("micex_registers","true");
        connect.addElemet("milliseconds","true");
        connect.addElemet("utc_time","false");
        connect.addElemet("rqdelay","100");
        connect.addElemet("session_timeout","120");
        connect.addElemet("request_timeout","20");

        disconnect.initRoot("command");
        disconnect.setRootAttribute("id","disconnect");

//        connect.getProxy().attributs.put("type","HTTP-CONNECT");
//        connect.getProxy().attributs.put("addr","bproxy.msk.mts.ru");
//        connect.getProxy().attributs.put("port","3131");
//        connect.getProxy().attributs.put("login","vabelyk2");
//        connect.getProxy().attributs.put("password","br--tha7");
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("MainTestThread");
        Main main = new Main();

        CallbackReader cbr = new CallbackReader(main.queue);
        Thread t = new Thread(cbr);
        t.setName("CallbackReader");
        t.start();



//            int i = 0;
//            while (i < 50) {
//
//                log.info("start iteration:" + i);
//                if (!main.test())
//                    return;
//                i++;
//                try {
//                    Thread.sleep(1_000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }




        boolean start = main.start();
        if (!start) {
            log.error("Fail start");
            return;
        }

        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = 0;
        while (i < 1) {
            log.info("connect:" + main.con.sendCommand(connect.getStringXml()));
            try {
                Thread.sleep(5_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("disconnect:" + main.con.sendCommand(disconnect.getStringXml()));
//            try {
//                Thread.sleep(1_000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            i++;
        }

        cbr.run = false;
        main.end();
    }

    private boolean test() {
        Main main = this;
        boolean start = main.start();
        if (!start) {
            log.error("Fail start");
            return false;
        }
        log.info("CallbackReader setting");

        CallbackReader cbr = new CallbackReader(queue);
//        cbr.print = false;
        Thread t = new Thread(cbr);
        t.setName("CallbackReader");
        t.start();

        log.info("CallbackReader have set");
        try {
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            log.error("main error:", e);
        }

        cbr.run = false;
        main.end();
        return true;
    }

    private boolean start() {
        log.info("Start");

        String path = TXmlConnector64.class.getClassLoader().getResource("txcn64.dll").getPath();
        path = path.replaceFirst("/", "").replace("/", "\\");
        boolean success;
        int fail_count = 0;
        int try_count = 10;
        do {
            success = con.initDll(path);
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

        String initResStr = new String(con.Initialize("C:\\log\\", 3));
        log.info("Initialize:" + initResStr);
        if (initResStr.contains("<error>")) {
            return false;
        }

        log.info("SetCallback:" + con.SetCallback(cb));

        //  connect();
        log.info("Start end");
        return true;
    }

    private void end() {
        log.info("end start");
        String uninitResStr = new String(con.UnInitialize());
        log.info("UnInitialize:" + uninitResStr);
        log.info("end end");
    }


}
