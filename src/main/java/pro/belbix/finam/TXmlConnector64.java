package pro.belbix.finam;

import org.slf4j.LoggerFactory;

/**
 * Created by Belykh Vsevolod on 22.04.2017.
 */
public class TXmlConnector64 {
    private String dllTXmlConPath;
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TXmlConnector64.class);


    public TXmlConnector64(String dllFinCon, String dllTXmlCon){
        System.load(dllFinCon);
        this.dllTXmlConPath = dllTXmlCon.replaceFirst("/", "").replace("/", "\\");

    }

    public String sendCommand(String cmd) {
        return new String(SendCommand(cmd));
    }

    public boolean initDllSimple() {
        return initDll(dllTXmlConPath);
    }

    public byte[] initializeTimeout(String logPath, int logLevel) {
        byte[] result = null;
        int fail_count = 0;
        int try_count = 3;
        do {
            result = Initialize(logPath, logLevel);
            if (result == null) {
                fail_count++;
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.error("", e);
                }
            }
        } while (result == null && fail_count <= try_count);
        if (fail_count > try_count) {
            return null; //TODO Throw exception here
        }
        return result;
    }

    public native boolean initDll(String path);

    public native boolean SetCallback(Object callback);

    public native boolean SetCallbackEx(byte[] pCallback, int userData);

    public native byte[] SendCommand(String pData);

    public native boolean FreeMemory(byte[] pData);

    public native byte[] Initialize(String logPath, int logLevel);

    public native byte[] UnInitialize();

    public native byte[] SetLogLevel(int logLevel);

    public native int GetServiceInfo(String request, Object response);
}
