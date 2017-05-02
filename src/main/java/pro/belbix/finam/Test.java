package pro.belbix.finam;

import org.slf4j.LoggerFactory;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.StringWriter;

/**
 * Created by Belykh Vsevolod on 28.04.2017.
 */
public class Test {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args){
        CallbackWriter writer = new CallbackWriter();
        writer.write("<success>INITIALIZATION SUCCESSFUL!</success>");

    }

}
