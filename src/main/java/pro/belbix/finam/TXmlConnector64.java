package pro.belbix.finam;

import org.slf4j.LoggerFactory;

import java.lang.ref.Reference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Belykh Vsevolod on 22.04.2017.
 */
public class TXmlConnector64 {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(TXmlConnector64.class);

    static {
        System.load(TXmlConnector64.class.getClassLoader().getResource("finam_connector.dll").getPath());
    }

    public String sendCommand(String cmd){
        return new String(SendCommand(cmd));
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
