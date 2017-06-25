# README #

Sample use this connector:


```
#!java

import org.slf4j.LoggerFactory;
import pro.belbix.finam.*;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Test {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Test.class);

    private static BlockingQueue<String> queue = new ArrayBlockingQueue<>(100_000);
    private static Command connect = new Command();
    private static Command disconnect = new Command();

    static {
        connect.initRoot("command");
        connect.setRootAttribute("id", "connect");
        connect.addElemet("login", "***");
        connect.addElemet("password", "***");
        connect.addElemet("host", "tr1-demo5.finam.ru");
        connect.addElemet("port", "3939");
        connect.addElemet("autopos", "false");
        connect.addElemet("micex_registers", "true");
        connect.addElemet("milliseconds", "true");
        connect.addElemet("utc_time", "false");
        connect.addElemet("rqdelay", "100");
        connect.addElemet("session_timeout", "120");
        connect.addElemet("request_timeout", "20");

        disconnect.initRoot("command");
        disconnect.setRootAttribute("id", "disconnect");

//        connect.getProxy().attributs.put("type","HTTP-CONNECT");
//        connect.getProxy().attributs.put("addr","***");
//        connect.getProxy().attributs.put("port","***");
//        connect.getProxy().attributs.put("login","***");
//        connect.getProxy().attributs.put("password","***");
    }

    public static void main(String[] args) throws ClassNotFoundException {

        Thread.currentThread().setName("MainTestThread");
        String s1 = Test.class.getClassLoader().getResource("finam_connector.dll").getPath();
        String s2 = Test.class.getClassLoader().getResource("txmlconnector64.dll").getPath();



        TXmlConnector64 con = new TXmlConnector64(s1,s2);
        DefaultInit defaultInit = new DefaultInit(con, queue);
        CallbackReader cbr = new CallbackReader(queue);
        Thread t = new Thread(cbr);
        t.setName("CallbackReader");
        t.setDaemon(true);
        t.start();
        try {
            boolean start = defaultInit.start();
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
                log.info("connect:" + con.sendCommand(connect.getStringXml()));
                try {
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("disconnect:" + con.sendCommand(disconnect.getStringXml()));
                i++;
            }
            defaultInit.stop();
        } finally {
            log.info("Successfully exit");
        }

    }

}


```