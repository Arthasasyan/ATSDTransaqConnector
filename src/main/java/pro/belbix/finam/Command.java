package pro.belbix.finam;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Map;

/**
 * Created by vabelyk2 on 28.04.2017.
 */
public class Command {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Command.class);

    private Document doc = null;
    private Element rootElement = null;

    public Command() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            doc = docBuilder.newDocument();
        } catch (ParserConfigurationException e) {
            log.error("", e);
        }
    }

    public Element initRoot(String name) {
        if (rootElement == null) {
            rootElement = doc.createElement(name);
            doc.appendChild(rootElement);

        }
        return rootElement;
    }

    public void setRootAttribute(String name, String value) {
        rootElement.setAttribute(name, value);
    }

    public Element addElemet(String name) {
        return addElemet(name, null, null);
    }

    public Element addElemet(String name, String value) {
        return addElemet(name, value, null);
    }

    public Element addElemet(String name, Map<String, String> attrs) {
        return addElemet(name, null, attrs);
    }

    public Element addElemet(String name, String value, Map<String,String> attrs){
        Element element = doc.createElement(name);
        if (value != null) {
            element.appendChild(doc.createTextNode(value));
        }
        if (attrs != null){
            for (String key : attrs.keySet()){
                element.setAttribute(key, attrs.get(key));
            }
        }
        rootElement.appendChild(element);
        return element;
    }



    public String getStringXml() {
        StringWriter writer = new StringWriter();
        StringBuffer strB = writer.getBuffer();
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer =transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        return strB.toString();
    }

}
