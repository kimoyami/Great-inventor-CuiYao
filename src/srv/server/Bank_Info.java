/*
 * Copyright: kimoyami
 */

package srv.server;

import dao.DataBank;
import srv.bank.Bank;
import srv.bank.BankInfo;
import srv.bank.Bankrecord;

import java.util.Vector;

public class Bank_Info {
    public static ServerThread now;
    public static void run(int op) {
        try {
            if (op == 1) {
                now.cout.writeInt(insert());
                now.cout.flush();
            }
            if (op == 2) {
                now.cout.writeInt(delete());
                now.cout.flush();
            }
            if (op == 3) {
               now.cout.writeObject(query());
               now.cout.flush();
            }
            if (op == 4) {
                now.cout.writeInt(transferToEcard());
                now.cout.flush();
            }
            if(op==5){
                now.cout.writeInt(transfer());
                now.cout.flush();
            }
            if(op==6){
                now.cout.writeInt(transferTocard());
                now.cout.flush();
            }
            if(op==7){
                Vector<Bankrecord> res = queryrecord();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static synchronized int insert() {
        BankInfo account;
        try {
            account = (BankInfo) now.cin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataBank.insert(account);
    }

    public static synchronized int delete() {
        BankInfo account;
        try {
            account = (BankInfo) now.cin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataBank.delete(account);
    }

    public static BankInfo query() {
        String ID = "";
        try {
            ID = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataBank.query(ID);
    }

    public static int transfer(){
        String fromID;
        String toID;
        double change;
        try{
            fromID=now.cin.readUTF();
            toID=now.cin.readUTF();
            change=now.cin.readDouble();
        }catch(Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBank.transfer(fromID,toID,change);
    }

    public static int transferToEcard(){
        String ID;
        double change;
        try{
            ID=now.cin.readUTF();
            change=now.cin.readDouble();
        }catch(Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBank.transferToEcard(ID,change);
    }
    public static int transferTocard(){
        String ID;
        double change;
        try{
            ID=now.cin.readUTF();
            change=now.cin.readDouble();
        }catch(Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBank.transferTocard(ID,change);
    }

    public static Vector<Bankrecord> queryrecord(){
        String ID="";
        try{
            ID=now.cin.readUTF();
        }catch(Exception e){
            e.printStackTrace();
        }
        return DataBank.queryrecord(ID);
    }
}
