package srv;

import java.io.Serializable;
import java.util.Date;

public class EcardRecord implements Serializable {
    private static final long serialVersionUID= 4235216323116948706L;
    private Date date;
    private double money;
    private double balance;

    public EcardRecord() {
        setDate(new Date());
    }


    public Date getDate() {
        return date;
    }
    public double getmoney() {
        return money;
    }
    public double getBalance() {
        return balance;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setmoney(double money) {
        this.money = money;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
