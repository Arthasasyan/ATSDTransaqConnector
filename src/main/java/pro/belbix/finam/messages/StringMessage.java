package pro.belbix.finam.messages;

public class StringMessage implements Message {
    private String message;

    public StringMessage(String message) {
        this.message = message;
    }

    @Override
    public String getStringXml() {
        return this.message;
    }
}
