/*
 * Copyright: kimoyami
 */

package srv.server;

import dao.DataHead;

public class Head {
    public static ServerThread now;
    public static void run(int op){
        try {
            if(op == 1){
                now.cout.writeUTF(query());
                now.cout.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String query(){
        String eCardNumber = "";
        try {
            eCardNumber = now.cin.readUTF();
        }catch (Exception e){
            e.printStackTrace();
        }
        return DataHead.query(eCardNumber);
    }

}
