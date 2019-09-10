/*
Author: kimoyami
 */
package srv.server;

import dao.DataBase;
import dao.DataPerson;
import srv.person.Unsolve;

import java.net.Socket;
import java.util.Vector;

public class Login {
    public static ServerThread now;

    public static void run(int op) {
        try {
            if (op == 1) {
                now.cout.writeInt(query());
                now.cout.flush();
            } else if (op == 2) {
                now.cout.writeInt(insert());
                now.cout.flush();
            } else if (op == 3) {
                now.cout.writeInt(delete());
                now.cout.flush();
            } else if (op == 4) {
                now.cout.writeInt(solve());
                now.cout.flush();
            } else if (op == 5) {
                now.cout.writeInt(addAdmin());
                now.cout.flush();
            } else if (op == 6) {
                now.cout.writeInt(cancelAdmin());
                now.cout.flush();
            } else if (op == 7) {
                now.cout.writeInt(update());
                now.cout.flush();
            } else if (op == 8) {
                now.cout.writeInt(isNew());
                now.cout.flush();
            } else if (op == 9) {
                Vector<Unsolve> res = getAll();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            } else if (op == 10) {
                now.cout.writeInt(exist());
                now.cout.flush();
            }


        } catch (Exception e) {
            return;
        }
    }

    public static int query() {
        String eCardNumber, password;
        try {
            eCardNumber = now.cin.readUTF();
            password = now.cin.readUTF();
        } catch (Exception e) {
            return -3;
        }
        DataBase.start();
        int a = DataBase.query(eCardNumber, password);
        DataBase.stop();

        return a;
    }

    public static synchronized int insert() {
        String userName;
        String password;
        String eCardNumber;
        String sex;
        String status;
        try {
            userName = now.cin.readUTF();
            password = now.cin.readUTF();
            eCardNumber = now.cin.readUTF();
            sex = now.cin.readUTF();
            status = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a = DataBase.insert(userName, password, eCardNumber, sex, status);
        DataBase.stop();
        return a;
    }

    public static synchronized int delete() {
        String eCardNumber;
        try {
            eCardNumber = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }

        DataBase.start();
        int a = DataBase.delete(eCardNumber);
        DataBase.stop();
        return a;
    }

    public static synchronized int solve() {
        String eCardNumber;
        try {
            eCardNumber = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a = DataBase.solve(eCardNumber);
        DataBase.stop();
        return a;
    }

    public static synchronized int addAdmin() {
        String eCardNumber;
        try {
            eCardNumber = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a = DataBase.addAdmin(eCardNumber);
        DataBase.stop();
        return a;
    }

    public static synchronized int cancelAdmin() {
        String eCardNumber;
        try {
            eCardNumber = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a = DataBase.cancelAdmin(eCardNumber);
        DataBase.stop();
        return a;
    }

    public static synchronized int update() {
        String userName;
        String password;
        String eCardNumber;
        String sex;
        String status;
        try {
            userName = now.cin.readUTF();
            password = now.cin.readUTF();
            eCardNumber = now.cin.readUTF();
            sex = now.cin.readUTF();
            status = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a = DataBase.update(userName, password, eCardNumber, sex, status);
        DataBase.stop();

        return a;
    }

    public static int isNew() {
        String eCardNumber;
        try {
            eCardNumber = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a = DataPerson.isNew(eCardNumber);
        DataBase.stop();

        return a;
    }

    public static Vector<Unsolve> getAll() {
        return DataBase.getAll();
    }

    public static int exist() {
        String eCardNumber;
        try {
            eCardNumber = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        DataBase.start();
        int a=DataBase.exist(eCardNumber);
        DataBase.stop();

        return a;
    }
}
