/*
 * Copyright: Mashiro
 */

package srv.client;


import com.sun.org.apache.bcel.internal.generic.InstructionConstants;
import srv.clinic.Clinic;
import srv.goods.Goods;

import java.util.Vector;

public class GoodInfo {
    private static final int STARTPOS = 50;

    public static int insert(String idx,int cnt,String goodname,double price,String url,String tag,String info) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 1);
            Client.cout.writeUTF(idx);
            Client.cout.writeInt(cnt);
            Client.cout.writeUTF(goodname);
            Client.cout.writeDouble(price);
            Client.cout.writeUTF(url);
            Client.cout.writeUTF(tag);
            Client.cout.writeUTF(info);
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

    public static int delete(String goodsname, String tag) {
        Client.run();
        try {
            Client.cout.writeInt(STARTPOS + 2);
            Client.cout.writeUTF(goodsname);
            Client.cout.writeUTF(tag);
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

    public static Vector<Goods> query(String name) {
        Client.run();
        Vector<Goods> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 3);
            Client.cout.writeUTF(name);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Goods) Client.cin.readObject());
            }
            Client.stop();
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            e.printStackTrace();
        }
        return res;
    }


    public static Vector<Goods> getAll() {
        Client.run();
        Vector<Goods> res = new Vector<>();
        try {
            Client.cout.writeInt(STARTPOS + 4);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Goods) Client.cin.readObject());
            }
            Client.stop();
        } catch (Exception e) {
            e.printStackTrace();
            Client.stop();
            e.printStackTrace();
        }
        return res;
    }

    public static int update(String name,String tag,int change){
        Client.run();
        try{
            Client.cout.writeInt(STARTPOS+5);
            Client.cout.writeUTF(name);
            Client.cout.writeUTF(tag);
            Client.cout.writeInt(change);
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

    public static int consumption(String ID,double change){
        Client.run();
        try{
        Client.cout.writeInt(STARTPOS+6);
        Client.cout.writeUTF(ID);
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

    public static void main(String args[]){


            int a=insert("0010",100,"ryh",10,"we","中超","123");

        System.out.println(a);

    }

}
