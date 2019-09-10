/*
 * Copyright: kimoyami
 */

package srv.server;

import dao.DataBank;
import dao.DataBase;
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
            if (op == 5) {
                now.cout.writeInt(transfer());
                now.cout.flush();
            }
            if (op == 6) {
                now.cout.writeInt(transferTocard());
                now.cout.flush();
            }
            if (op == 7) {
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
        DataBase.start();
        int a = DataBank.insert(account);
        DataBase.stop();
        return a;
    }

    public static synchronized int delete() {
        BankInfo account;
        try {
            account = (BankInfo) now.cin.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a = DataBank.delete(account);
        DataBase.stop();
        return a;

    }

    public static BankInfo query() {
        String ID = "";
        try {
            ID = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataBase.start();
        BankInfo bankInfo = DataBank.query(ID);
        DataBase.stop();

        return bankInfo;
    }

    public static int transfer() {
        String fromID;
        String toID;
        double change;
        try {
            fromID = now.cin.readUTF();
            toID = now.cin.readUTF();
            change = now.cin.readDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a = DataBank.transfer(fromID, toID, change);
        DataBase.stop();
        return a;
    }

    public static int transferToEcard() {
        String ID;
        double change;
        try {
            ID = now.cin.readUTF();
            change = now.cin.readDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a = DataBank.transferToEcard(ID, change);
        DataBase.stop();
        return a;
    }

    public static int transferTocard() {
        String ID;
        double change;
        try {
            ID = now.cin.readUTF();
            change = now.cin.readDouble();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a=DataBank.transferTocard(ID, change);
        DataBase.stop();
        return a;
    }

    public static Vector<Bankrecord> queryrecord() {
        String ID = "";
        try {
            ID = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        DataBase.start();
        Vector<Bankrecord>res=DataBank.queryrecord(ID);
        DataBase.stop();
        return res;
    }
}
