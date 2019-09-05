/*
 * Copyright: kimoyami
 */

package srv.client;

import srv.bank.BankInfo;

public class Bank_Info {
    private static final int STARTPOS = 23;
    public static int insert(BankInfo account) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeObject(account);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }

    public static int delete(BankInfo account) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeObject(account);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }

    public static BankInfo query(String ID) {
        Client.run();
        BankInfo account = null;
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.writeUTF(ID);
            Client.cout.flush();
            account = (BankInfo) Client.cin.readObject();
            Client.stop();
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
        }
        return account;
    }

    public static int update(BankInfo account, double change, int tag) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.writeObject(account);
            Client.cout.writeDouble(change);
            Client.cout.writeInt(tag);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }

}
