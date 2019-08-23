package srv;

import java.io.Serializable;

/*
账户余额balance
密码password
转账对象transferTo
转账金额transferAmount
转账时间transferTime
银行个人信息类BankInfo(String, double, int, double, long)
 */
public class BankInfo {
    private String ID;
    private double balance;
    private int password;
    private String transferTo;
    private double transferAmount;
    private long transferTime;

    public BankInfo(String id, double b, int p, String to, double amount, long t){
        this.setID(id);
        this.setBalance(b);
        this.setPassword(p);
        this.setTransferTo(to);
        this.setTransferAmount(amount);
        this.setTransferTime(t);
    }

    public void setID(String param){
        this.ID = param;
    }
    public String getID(){
        return this.ID;
    }

    public void setBalance(double param){
        this.balance = param;
    }
    public double getBalance(){
        return this.balance;
    }

    public void setPassword(int param){
        this.password = param;
    }
    public int getPassword(){
        return this.password;
    }

    public void setTransferTo(String param){
        this.transferTo = param;
    }
    public String getTransferTo(){
        return this.transferTo;
    }

    public void setTransferAmount(double param){
        this.transferAmount = param;
    }
    public double getTransferAmount(){
        return this.transferAmount;
    }

    public void setTransferTime(long param){
        this.transferTime = param;
    }
    public long getTransferTime(){
        return this.transferTime;
    }
}
