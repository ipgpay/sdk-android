package ipg.sdk.models;

public class EncryptedData {
    private String pad;
    private String value;

    public EncryptedData(String pad, String value) {
        this.pad = pad;
        this.value = value;
    }

    public String getPad() {
        return pad;
    }

    public String getValue() {
        return value;
    }
}
