package srv;

import java.io.Serializable;
import java.util.Date;

/*
* 一卡通信息
* */

public class EcardInfo implements Serializable {
    private static final long serialVersionUID= 7768054716259077555L;
    private int state;
    private double balance;

    public EcardInfo() {
        setBalance(0);
        setState(1);
    }

    public int getState() {
        return this.state;
    }
    public double getBalance() {
        return this.balance;
    }


    public void setState(int state) {
        this.state = state;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void changeBalance(double money){
        if(state==1){
            this.balance+=money;
        }
    }
}
