package pro.belbix.finam.messages;

import org.w3c.dom.Document;
import pro.belbix.finam.XmlElement;

/**
 * Created by Belykh Vsevolod on 30.04.2017.
 */
public class ServerStatus extends XmlElement implements Message {

    public ServerStatus(Document doc) {
        super.doc = doc;
    }
}
