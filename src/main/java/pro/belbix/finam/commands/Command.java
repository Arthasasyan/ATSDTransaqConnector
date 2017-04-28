package pro.belbix.finam.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vabelyk2 on 28.04.2017.
 */
public abstract class Command {

    private String id;
    private final static String root_tag = "command";
    Map<String, XmlElement> elements = new HashMap<>();

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    String getRoot_tag() {
        return root_tag;
    }

    public Map<String, XmlElement> getElements() {
        return elements;
    }
}
