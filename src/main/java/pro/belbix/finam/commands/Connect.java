package pro.belbix.finam.commands;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vabelyk2 on 28.04.2017.
 */
public class Connect extends Command{

    private XmlElement login = new XmlElement("login");
    private XmlElement password = new XmlElement("password");
    private XmlElement host = new XmlElement("host");
    private XmlElement port = new XmlElement("port");
    private XmlElement autopos = new XmlElement("autopos");
    private XmlElement micex_registers = new XmlElement("micex_registers");
    private XmlElement milliseconds = new XmlElement("milliseconds");
    private XmlElement utc_time = new XmlElement("utc_time");
    private XmlElement proxy = new XmlElement("proxy");
    private XmlElement rqdelay = new XmlElement("rqdelay");
    private XmlElement session_timeout = new XmlElement("session_timeout");
    private XmlElement request_timeout = new XmlElement("request_timeout");
    private XmlElement push_u_limits = new XmlElement("push_u_limits");
    private XmlElement push_pos_equity = new XmlElement("push_pos_equity");

    public Connect() {
        super();
        Field[] fields = this.getClass().getFields();
        for (Field field : fields){
            if (field.getClass().getName().equals(XmlElement.class.getName())){
                try {
                    XmlElement el = (XmlElement) field.get(this);
                    elements.put(el.getName(), el);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getXml(){
        String xml = "";


        return xml;
    }

    public XmlElement getLogin() {
        return login;
    }

    public void setLogin(XmlElement login) {
        this.login = login;
    }

    public XmlElement getPassword() {
        return password;
    }

    public void setPassword(XmlElement password) {
        this.password = password;
    }

    public XmlElement getHost() {
        return host;
    }

    public void setHost(XmlElement host) {
        this.host = host;
    }

    public XmlElement getPort() {
        return port;
    }

    public void setPort(XmlElement port) {
        this.port = port;
    }

    public XmlElement getAutopos() {
        return autopos;
    }

    public void setAutopos(XmlElement autopos) {
        this.autopos = autopos;
    }

    public XmlElement getMicex_registers() {
        return micex_registers;
    }

    public void setMicex_registers(XmlElement micex_registers) {
        this.micex_registers = micex_registers;
    }

    public XmlElement getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(XmlElement milliseconds) {
        this.milliseconds = milliseconds;
    }

    public XmlElement getUtc_time() {
        return utc_time;
    }

    public void setUtc_time(XmlElement utc_time) {
        this.utc_time = utc_time;
    }

    public XmlElement getProxy() {
        return proxy;
    }

    public void setProxy(XmlElement proxy) {
        this.proxy = proxy;
    }

    public XmlElement getRqdelay() {
        return rqdelay;
    }

    public void setRqdelay(XmlElement rqdelay) {
        this.rqdelay = rqdelay;
    }

    public XmlElement getSession_timeout() {
        return session_timeout;
    }

    public void setSession_timeout(XmlElement session_timeout) {
        this.session_timeout = session_timeout;
    }

    public XmlElement getRequest_timeout() {
        return request_timeout;
    }

    public void setRequest_timeout(XmlElement request_timeout) {
        this.request_timeout = request_timeout;
    }

    public XmlElement getPush_u_limits() {
        return push_u_limits;
    }

    public void setPush_u_limits(XmlElement push_u_limits) {
        this.push_u_limits = push_u_limits;
    }

    public XmlElement getPush_pos_equity() {
        return push_pos_equity;
    }

    public void setPush_pos_equity(XmlElement push_pos_equity) {
        this.push_pos_equity = push_pos_equity;
    }
}
