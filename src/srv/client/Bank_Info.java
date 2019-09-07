/*
 * Copyright: kimoyami
 */

package srv.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import srv.bank.BankInfo;
import srv.person.Person;
import srv.server.Server;

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

<<<<<<< HEAD
    public static int transfToEcard(String s){
        ObjectMapper mapper = new ObjectMapper();
        BankInfo bankinfo = new BankInfo();
        try {
            bankinfo = mapper.readValue(s, BankInfo.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return transfToEcard(bankinfo.getID(), bankinfo.getTransferAmount());
    }

=======
    public static int transfTocard(String ID,double change) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 6);
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


>>>>>>> master
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

    public static int transfer(String s){
        ObjectMapper mapper = new ObjectMapper();
        BankInfo bankinfo = new BankInfo();
        int a = 0;
        try {
            bankinfo = mapper.readValue(s, BankInfo.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return transfer(bankinfo.getID(), bankinfo.getTransferTo(), bankinfo.getTransferAmount());
    }


    /*
    public static int update(String s){
        ObjectMapper mapper = new ObjectMapper();
        BankInfo bankInfo = new BankInfo();
        int a = 0;
        try {
            bankInfo = mapper.readValue(s, BankInfo.class);
            a = Integer.parseInt(bankInfo.getTransferTo());
        }catch (Exception e){
            e.printStackTrace();
        }
        return update(bankInfo, bankInfo.getTransferAmount(), a);
    }
    /
     */

    public static void main(String args[]){

        int b=transfTocard("213170001",20);
        System.out.println(b);

    }
}
