/*
 * Copyright: Mashiro
 */

package srv.client;


import srv.goods.Goods;

import java.util.Vector;

public class GoodInfo {
    public static int insert(Goods goods) {
        Client.run();
        try {
            Client.cout.writeInt(30);
            Client.cout.writeObject(goods);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            Client.stop();
            return -4;
        }
    }

    public static int delete(Goods goods) {
        Client.run();
        try {
            Client.cout.writeInt(31);
            Client.cout.writeObject(goods);
            Client.cout.flush();
            int res = Client.cin.readInt();
            Client.stop();
            return res;
        } catch (Exception e) {
            Client.stop();
            return -4;
        }
    }

    public static Vector<Goods> query(String name,int tag) {
        Client.run();
        Vector<Goods> res = new Vector<>();
        try {
            Client.cout.writeInt(32);
            Client.cout.writeUTF(name);
            Client.cout.writeInt(tag);
            Client.cout.flush();
            int n = Client.cin.readInt();
            for (int i = 0; i < n; i++) {
                res.add((Goods) Client.cin.readObject());
            }
            Client.stop();
        } catch (Exception e) {
            Client.stop();
            e.printStackTrace();
        }
        return res;
    }

    public static int update(Goods goods) {
        Client.run();
        try {
            Client.cout.writeInt(33);
            Client.cout.writeObject(goods);
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
