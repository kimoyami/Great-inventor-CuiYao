package srv.file;

import java.io.Serializable;

public class Files implements Serializable {
    private static final long serialVersionUID = 7768054716259077555L;
    private String eCardNumber;
    private String url;
    private String fileName;
    private int clicks;

    public Files(String eCardNumber, String url, String fileName, int clicks) {
        this.eCardNumber = eCardNumber;
        this.url = url;
        this.fileName = fileName;
        this.clicks = clicks;
    }

    public String geteCardNumber() {
        return eCardNumber;
    }

    public void seteCardNumber(String eCardNumber) {
        this.eCardNumber = eCardNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }
}
