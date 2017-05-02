package pro.belbix.finam;

import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import pro.belbix.finam.messages.Message;
import pro.belbix.finam.messages.ServerStatus;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by Belykh Vsevolod on 29.04.2017.
 */
public class CallbackWriter {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CallbackWriter.class);
    private DocumentBuilderFactory factory = null;
    private DocumentBuilder builder = null;

    public CallbackWriter() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            log.error("", e);
        }
    }

    public void write(String msg) {
        InputSource is = new InputSource(new StringReader(msg));
        Document doc = null;
        try {
            doc = builder.parse(is);
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
        if (doc == null) return;
        Message message = switchMessage(doc);
        log.info(message.getStringXml());
    }

    private Message switchMessage(Document doc) {
        Node root = doc.getFirstChild();
        String name = root.getNodeName();
        Message msg = null;
        switch (name){
            case "server_status":
                msg = new ServerStatus(doc);
        }
        return msg;
    }

    public static void main(String[] args) {
        CallbackWriter writer = new CallbackWriter();
        writer.write("<root></root>");


    }

}
