/*
 * Copyright: kimoyami
 */

package srv.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import srv.bank.BankInfo;
import srv.bank.Bankrecord;
import srv.person.Person;
import srv.server.Server;

import java.util.Vector;

public class Bank_Info {
    private static final int STARTPOS = 40;
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

    public static String query(String ID) {
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
        String res = "";
        ObjectMapper mapper = new ObjectMapper();
        try{
            res = mapper.writeValueAsString(account);
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static int transfToEcard(String ID,double change) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.writeUTF(ID);
            Client.cout.writeDouble(change);
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



    public static int transfer(String fromID,String toID,double change){
        Client.run();
        try{
            Client.cout.writeInt(STARTPOS+5);
            Client.cout.writeUTF(fromID);
            Client.cout.writeUTF(toID);
            Client.cout.writeDouble(change);
            Client.cout.flush();
            int res=Client.cin.readInt();
            Client.stop();
            return res;
        }catch(Exception e){
            e.printStackTrace();
            Client.stop();
            return -4;
        }
    }

    public static int transfTocard(String ID) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 6);
            Client.cout.writeUTF(ID);
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


    public static Vector<Bankrecord> queryrecord(String ID) {
        Client.run();
        Vector<Bankrecord> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 7);
            Client.cout.writeUTF(ID);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Bankrecord) Client.cin.readObject());
            }
            Client.stop();
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            e.printStackTrace();
        }
        return res;

    }

    public static void main(String args[]){
        Vector<Bankrecord> res=queryrecord("213170002");
        for (int i = 0; i <res.size() ; i++) {
            System.out.println(res.elementAt(i).getRecord()+res.elementAt(i).getOtime());
        }
    }
}
