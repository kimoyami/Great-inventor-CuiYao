/*
 * Copyright: kimoyami
 */

package srv.server;

import dao.DataBank;
import srv.bank.BankInfo;

public class Bank_Info {
    public static void run(int op) {
        try {
            if (op == 1) {
                ServerThread.cout.writeInt(insert());
                ServerThread.cout.flush();
            }
            if (op == 2) {
                ServerThread.cout.writeInt(delete());
                ServerThread.cout.flush();
            }
            if (op == 3) {
               ServerThread.cout.writeObject(query());
               ServerThread.cout.flush();
            }
            if (op == 4) {
                ServerThread.cout.writeInt(transferToEcard());
                ServerThread.cout.flush();
            }
            if(op==5){
                ServerThread.cout.writeInt(transfer());
                ServerThread.cout.flush();
            }
            if(op==6){
                ServerThread.cout.writeInt(transferTocard());
                ServerThread.cout.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    public static synchronized int insert() {
        BankInfo account;
        try {
            account = (BankInfo) ServerThread.cin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataBank.insert(account);
    }

    public static synchronized int delete() {
        BankInfo account;
        try {
            account = (BankInfo) ServerThread.cin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataBank.delete(account);
    }

    public static BankInfo query() {
        String ID = "";
        try {
            ID = ServerThread.cin.readUTF();
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
            fromID=ServerThread.cin.readUTF();
            toID=ServerThread.cin.readUTF();
            change=ServerThread.cin.readDouble();
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
            ID=ServerThread.cin.readUTF();
            change=ServerThread.cin.readDouble();
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
            ID=ServerThread.cin.readUTF();
            change=ServerThread.cin.readDouble();
        }catch(Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBank.transferTocard(ID,change);
    }

}
