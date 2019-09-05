package srv.bank;

import java.io.Serializable;
/*
账户余额balance
密码password
转账对象transferTo
转账金额transferAmount
转账时间transferTime
银行个人信息类BankInfo(String, double, int, String, double, long)
 */
public class BankInfo {
    private static final long serialVersionUID = -1925809561781496530L;

    private String ID;
    private String name;
    private double balance;
    private double eCardBalance;
    private String password;
    private String transferTo;
    private double transferAmount;
    private long transferTime;
    private String state;

    public BankInfo(String id,String n, double b, String p, String to, double amount, long t){
        this.setID(id);
        this.setName(n);
        this.setBalance(b);
        this.setPassword(p);
        this.setTransferTo(to);
        this.setTransferAmount(amount);
        this.setTransferTime(t);
    }

    public BankInfo(String id,String n, double b, double e,String p){
        this.setID(id);
        this.setName(n);
        this.setBalance(b);
        this.seteCardBalance(e);
        this.setPassword(p);
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

    public void seteCardBalance(double param){
        this.eCardBalance = param;
    }
    public double geteCardBalance(){
        return this.eCardBalance;
    }

    public void setPassword(String param){
        this.password = param;
    }
    public String getPassword(){
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

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setState(String state){this.state=state;}
    public String getState(){return this.state;}

}
