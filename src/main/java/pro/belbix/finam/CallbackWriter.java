package pro.belbix.finam;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pro.belbix.finam.messages.Message;
import pro.belbix.finam.messages.ServerStatus;
import pro.belbix.finam.messages.StringMessage;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by Belykh Vsevolod on 29.04.2017.
 */
public class CallbackWriter {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CallbackWriter.class);
    private DocumentBuilderFactory factory = null;
    private DocumentBuilder builder = null;
    private boolean logErrors;

    public CallbackWriter() {
        this(true);
    }

    public CallbackWriter(boolean logErrors) {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error("", e);
        }
        this.logErrors = logErrors;
    }

    public void write(String msg) {
        InputSource is = new InputSource(new ByteArrayInputStream(msg.getBytes(StandardCharsets.UTF_8)));
        Document doc = null;
        try {
            doc = builder.parse(is);
        } catch (SAXException | IOException e) {
            if (logErrors) {
                log.info("Input source: " + msg);
                e.printStackTrace();
                //throw new RuntimeException("Kill me");
            }
        }
        if (doc == null) return;
        Message message = switchMessage(doc);
        if (message == null) {
            message = switchMessage(msg);
        }
        ;
        log.info(message.getStringXml());
    }

    private Message switchMessage(Document doc) {
        Node root = doc.getFirstChild();
        String name = root.getNodeName();
        Message msg = null;
        switch (name) {
            case "server_status":
                msg = new ServerStatus(doc);
                break;
        }
        return msg;
    }

    private Message switchMessage(String message) {
        return new StringMessage(message);
    }

    /*public static void main(String[] args) throws Exception {
        CallbackWriter writer = new CallbackWriter();
        writer.write("<root></root>");


    }*/

}
