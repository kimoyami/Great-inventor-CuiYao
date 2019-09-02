/*
 * Copyright: kimoyami
 */

package srv.client;

import srv.bank.BankInfo;

public class Bank_Info {
    public static int insert(BankInfo account) {
        Client.run();
        try {
            Client.cout.writeInt(40);
            Client.cout.writeObject(account);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            Client.stop();
            return -4;
        }
    }

    public static int delete(BankInfo account) {
        Client.run();
        try {
            Client.cout.writeInt(41);
            Client.cout.writeObject(account);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            Client.stop();
            return -4;
        }
    }

    public static BankInfo query(String ID) {
        Client.run();
        BankInfo account = null;
        try {
            Client.cout.writeInt(42);
            Client.cout.writeUTF(ID);
            Client.cout.flush();
            account = (BankInfo) Client.cin.readObject();
            Client.stop();
        } catch (Exception e) {
            Client.stop();
        }
        return account;
    }

    public static int update(BankInfo account, double change, int tag) {
        Client.run();
        try {
            Client.cout.writeInt(43);
            Client.cout.writeObject(account);
            Client.cout.writeDouble(change);
            Client.cout.writeInt(tag);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            Client.stop();
            return -4;
        }
    }

}
