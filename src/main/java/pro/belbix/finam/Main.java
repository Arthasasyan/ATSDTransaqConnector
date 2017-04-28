package pro.belbix.finam;

import org.slf4j.LoggerFactory;
import pro.belbix.finam.commands.Connect;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by Belykh Vsevolod on 22.04.2017.
 */
public class Main {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Main.class);
    private TXmlConnector64 con = new TXmlConnector64();

    public static void main(String[] args) {


        Main main = new Main();

        main.start();



    }

    private void start() {
//        log.info("Start");
        Integer i = 1;
        Reference<Integer> ref = new SoftReference<>(i);

//        boolean fromHell = con.SetCallback(Callback.callback);
        String path = TXmlConnector64.class.getClassLoader().getResource("txmlconnector64.dll").getPath();
        path = path.replaceFirst("/", "").replace("/", "\\");
        //path = "C:\\libs\\txmlconnector64.dll";
//        log.info(path);
        boolean success;
        int fail_count = 0;
        do {
            success= con.initDll(path);
            log.info("initDll:" + success);
            if (!success){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            fail_count++;
        } while (!success && fail_count < 10);


        byte[] initRes = con.Initialize("C:/log", 3);

        String initResStr = new String(initRes);
        log.info("initRes:" + initResStr);

        Callback cb = new Callback(new LinkedBlockingQueue<>(10000));

        boolean callbackRes = con.SetCallback(cb);
        log.info("callbackRes:" + callbackRes);

        Connect connect = new Connect();
        connect.getLogin().setBody("TCNN9964");
        connect.getPassword().setBody("v8JUF8");
        connect.getHost().setBody("tr1-demo5.finam.ru");
        connect.getPort().setBody("3939");
        connect.getAutopos().setBody("false");
        connect.getMicex_registers().setBody("true");
        connect.getMilliseconds().setBody("true");
        connect.getUtc_time().setBody("false");
        connect.getRqdelay().setBody("1000");
        connect.getSession_timeout().setBody("10");
        connect.getRequest_timeout().setBody("5");
        connect.getProxy().attributs.put("type","HTTP-CONNECT");
        connect.getProxy().attributs.put("addr","bproxy.msk.mts.ru");
        connect.getProxy().attributs.put("port","3131");
        connect.getProxy().attributs.put("login","vabelyk2");
        connect.getProxy().attributs.put("password","br--tha7");

        log.info("elem size:" + connect.getElements().size());
        log.info(connect.getXml());



        String cmdResStr = con.sendCommand(connect.getXml());

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("cmdRes:" + cmdResStr);


//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        log.info("end");
    }


    public static String bytesToStringUTFCustom(byte[] bytes) {
        char[] buffer = new char[bytes.length >> 1];
        for (int i = 0; i < buffer.length; i++) {
            int bpos = i << 1;
            char c = (char) (((bytes[bpos] & 0x00FF) << 8) + (bytes[bpos + 1] & 0x00FF));
            buffer[i] = c;
        }
        return new String(buffer);
    }


}
