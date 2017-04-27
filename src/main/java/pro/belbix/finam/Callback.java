package pro.belbix.finam;

import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * Created by Belykh Vsevolod on 23.04.2017.
 */
public class Callback {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Callback.class);

    public synchronized static void callback(byte[] pData) {
        String pDataStr = new String(pData);
        log.info(pDataStr);
    }
}
