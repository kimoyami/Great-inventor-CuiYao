package srv.ecard;

import java.io.Serializable;

public class EcardInfo implements Serializable {
    private static final long serialVersionUID= 7768054716259077555L;
    private int state;//0欠费，1正常，2停用
    private double balance;

    public EcardInfo(int state,int balance) {
        this.balance=balance;
        this.state=state;
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
