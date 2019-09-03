/*
Arthor: kimoyami
 */
package srv.server;

import dao.DataBase;
import dao.DataPerson;

import java.util.Vector;

public class Login {
    
    public static void run(int op){
        try {
            if (op == 1) {
                ServerThread.cout.writeInt(query());
                ServerThread.cout.flush();
            } else if (op == 2) {
                ServerThread.cout.writeInt(insert());
                ServerThread.cout.flush();
            } else if (op == 3) {
                ServerThread.cout.writeInt(delete());
                ServerThread.cout.flush();
            } else if (op == 4) {
                ServerThread.cout.writeInt(solve());
                ServerThread.cout.flush();
            } else if (op == 5) {
                ServerThread.cout.writeInt(addAdmin());
                ServerThread.cout.flush();
            } else if (op == 6) {
                ServerThread.cout.writeInt(cancelAdmin());
                ServerThread.cout.flush();
            } else if (op == 7) {
                ServerThread.cout.writeInt(update());
                ServerThread.cout.flush();
            }
            else if(op == 8){
                ServerThread.cout.writeInt(isNew());
                ServerThread.cout.flush();
            }
            else if(op == 9){
                Vector<Vector<Object>> res = getAll();
                ServerThread.cout.writeInt(res.size());
                for(int i = 0; i < res.size(); i++){
                    ServerThread.cout.writeInt(res.elementAt(i).size());
                    for(int j = 0; j < res.elementAt(i).size(); j++){
                        ServerThread.cout.writeObject(res.elementAt(i).elementAt(j));
                    }
                }
                ServerThread.cout.flush();
            }
            else if(op == 10){
                ServerThread.cout.writeInt(exist());
                ServerThread.cout.flush();
            }


        }catch (Exception e){
            return;
        }
    }
    
    public static int query(){
        String eCardNumber, password;
        try {
            eCardNumber = ServerThread.cin.readUTF();
            password = ServerThread.cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.query(eCardNumber, password);
    }

    public static synchronized int insert(){
        String userName;
        String password;
        String eCardNumber;
        String sex;
        String status;
        try {
            userName = ServerThread.cin.readUTF();
            password = ServerThread.cin.readUTF();
            eCardNumber = ServerThread.cin.readUTF();
            sex = ServerThread.cin.readUTF();
            status = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBase.insert(userName, password, eCardNumber, sex, status);
    }

    public static synchronized int delete(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBase.delete(eCardNumber);
    }

    public static synchronized int solve(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBase.solve(eCardNumber);
    }

    public static synchronized int addAdmin(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBase.addAdmin(eCardNumber);
    }

    public static synchronized int cancelAdmin(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBase.cancelAdmin(eCardNumber);
    }

    public static synchronized int update() {
        String userName;
        String password;
        String eCardNumber;
        String sex;
        String status;
        try {
            userName = ServerThread.cin.readUTF();
            password = ServerThread.cin.readUTF();
            eCardNumber = ServerThread.cin.readUTF();
            sex = ServerThread.cin.readUTF();
            status = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBase.update(userName, password, eCardNumber, sex, status);
    }

    public static int isNew(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataPerson.isNew(eCardNumber);
    }

    public static Vector<Vector<Object>> getAll(){
        return DataBase.getAll();
    }

    public static int exist(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
            return -3;
        }
        return DataBase.exist(eCardNumber);
    }
}
