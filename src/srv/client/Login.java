/*
Arthor: kimoyami
 */

package srv.client;

import srv.person.Unsolve;

import java.util.Vector;
import com.fasterxml.jackson.databind.ObjectMapper;
public class Login {
    private static final int STARTPOS = 0;
    public static int query(String eCardNumber, String password){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.writeUTF(password);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static int insert(String userName, String password, String eCardNumber, String sex, String status){
        Client.run();
        try{
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeUTF(userName);
            Client.cout.writeUTF(password);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.writeUTF(sex);
            Client.cout.writeUTF(status);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static int delete(String eCardNumber){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static int solve(String eCardNumber){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static int addAdmin(String eCardNumber){
        Client.run();
        try{
            Client.cout.writeInt(STARTPOS + 5);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static int cancelAdmin(String eCardNumber){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 6);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static int update(String userName, String password, String eCardNumber, String sex, String status){
        Client.run();
        try{
            Client.cout.writeInt(STARTPOS + 7);
            Client.cout.writeUTF(userName);
            Client.cout.writeUTF(password);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.writeUTF(sex);
            Client.cout.writeUTF(status);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }


    public static int isNew(String eCardNumber){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 8);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

    public static Vector<Unsolve> getAll(){
        Client.run();
        Vector<Unsolve> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 9);

            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Unsolve) Client.cin.readObject());
            }
            Client.stop();
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            e.printStackTrace();
        }
        return res;
    }
    
    public static int exist(String eCardNumber){
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 10);
            Client.cout.writeUTF(eCardNumber);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        }catch (Exception e){
            Client.stop();
            return -4;
        }
    }

   public static void main(String args[]) {
        Vector<Unsolve>res=getAll();
       for (int i = 0; i <res.size() ; i++) {
           System.out.println(res.elementAt(i).getSex());
       }


   }
}
