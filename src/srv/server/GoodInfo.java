/*
 * Copyright: kimoyami
 */

package srv.server;

import dao.DataGoods;
import srv.goods.Goods;

import java.util.Vector;

public class GoodInfo {

    public static void run(int op) {
        try {
            if (op == 1) {
                ServerThread.cout.writeInt(insert());
                ServerThread.cout.flush();
            }
            if (op == 2) {
                ServerThread.cout.writeInt(delete());
                ServerThread.cout.flush();
            }
            if (op == 3) {
                Vector<Goods> res = query();
                ServerThread.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    ServerThread.cout.writeObject(res.elementAt(i));
                }
                ServerThread.cout.flush();
            }

        } catch (Exception e) {
            return;
        }
    }

    public static synchronized int insert() {
        Goods goods;
        try {
            goods = (Goods) ServerThread.cin.readObject();
        } catch (Exception e) {
            return -3;
        }
        return DataGoods.insert(goods);
    }

    public static synchronized int delete() {
        Goods goods;
        try {
            goods = (Goods) ServerThread.cin.readObject();
        } catch (Exception e) {
            return -1;
        }
        return DataGoods.delete(goods);
    }

    public static Vector<Goods> query() {
        String name = "";
        int tag = 0;
        try {
            name = ServerThread.cin.readUTF();
            tag = ServerThread.cin.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataGoods.query(name, tag);
    }

}
