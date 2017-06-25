package pro.belbix.finam;

import org.slf4j.LoggerFactory;

import java.net.URL;

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
