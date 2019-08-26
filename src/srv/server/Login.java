package srv.server;

import dao.DataBase;

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
            return -3;
        }
        return DataBase.insert(userName, password, eCardNumber, sex, status);
    }

    public static synchronized int delete(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.delete(eCardNumber);
    }

    public static synchronized int solve(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.solve(eCardNumber);
    }

    public static synchronized int addAdmin(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            return -3;
        }
        return DataBase.addAdmin(eCardNumber);
    }

    public static synchronized int cancelAdmin(){
        String eCardNumber;
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
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
            return -3;
        }
        return DataBase.update(userName, password, eCardNumber, sex, status);
    }
}
