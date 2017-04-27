package pro.belbix.finam;

import org.slf4j.LoggerFactory;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.reflect.Method;

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
        byte[] b = {1};
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
        //byte[] initRes = con.Initialize("C:log", 3);

        String initResStr = new String(initRes);
        log.info("initRes:" + initResStr);

        Callback cb = new Callback();

        boolean callbackRes = con.SetCallback(cb);
        log.info("callbackRes:" + callbackRes);


        String cmdConnect = "<command id=\"connect\">" +
                "<login>TCNN9964</login>" +
                "<password>v8JUF8</password>" +
                "<host>tr1-demo5.finam.ru</host>" +
                "<port>3939</port>" +
                "<autopos>false</autopos>" +
                "<micex_registers>true</micex_registers>" +
                "<milliseconds>true</milliseconds>" +
                "<utc_time>false</utc_time>" +
                "<rqdelay>1000</rqdelay>" +
                "<session_timeout>10</session_timeout>" +
                "<request_timeout>5</request_timeout>" +
                "</command>";



        String cmdResStr = con.sendCommand(cmdConnect);

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
