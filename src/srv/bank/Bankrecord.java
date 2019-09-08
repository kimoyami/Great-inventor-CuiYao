package srv.bank;

import java.io.Serializable;

public class Bankrecord implements Serializable {
    private static final long serialVersionUID = 4887339892028369833L;
    String eCardName;
    double change;
    String record;
    String otime;

    public Bankrecord(String e,double c,String r,String o) {
        this.eCardName=e;
        this.change=c;
        this.record=r;
        this.otime=o;
    }

    public String getECardName() {
        return this.eCardName;
    }

    public double getChange() {
        return this.change;
    }

    public String getRecord() {
        return this.record;
    }

    public String getOtime() {
        return this.otime;
    }

    public void setECardName(String eCardName) {
        this.eCardName = eCardName;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public void setOtime(String otime) {
        this.otime = otime;
    }

}
