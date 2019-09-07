/*
 * Copyright: kimoyami
 */

package srv.server;

import dao.DataHead;

public class Head {
    public static void run(int op){
        try {
            if(op == 1){
                ServerThread.cout.writeUTF(query());
                ServerThread.cout.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static synchronized String query(){
        String eCardNumber = "";
        try {
            eCardNumber = ServerThread.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        return DataHead.query(eCardNumber);
    }

}
