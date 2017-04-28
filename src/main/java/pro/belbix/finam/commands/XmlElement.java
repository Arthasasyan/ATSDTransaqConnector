package pro.belbix.finam.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vabelyk2 on 28.04.2017.
 */
public class XmlElement {

    public XmlElement(String name) {
        this.name = name;
    }

    String name = "";
    String body = "";
    public Map<String, String> attributs = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
