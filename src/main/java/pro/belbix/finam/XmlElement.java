package pro.belbix.finam;

import org.w3c.dom.Document;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;

/**
 * Created by Belykh Vsevolod on 30.04.2017.
 */
public abstract class XmlElement {

    protected Document doc = null;

    public String getStringXml() {
        StringWriter writer = new StringWriter();
        StringBuffer strB = writer.getBuffer();
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer =transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return strB.toString();
    }

}
