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
            if (op == 4) {
                Vector<Goods> res = getAll();
                ServerThread.cout.writeInt(res.size());
                System.out.println(res.size());
                for (int i = 0; i < res.size(); i++) {
                    ServerThread.cout.writeObject(res.elementAt(i));
                }
                ServerThread.cout.flush();
            }
            if (op == 5) {
                ServerThread.cout.writeInt(update());
                ServerThread.cout.flush();
            }
            if (op == 6) {
                ServerThread.cout.writeInt(consumption());
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
            e.printStackTrace();
            return -3;
        }
        return DataGoods.insert(goods);
    }

    public static synchronized int delete() {
        String goodsname;
        String tag;
        try {
            goodsname = ServerThread.cin.readUTF();
            tag = ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return DataGoods.delete(goodsname, tag);
    }


    public static Vector<Goods> query() {
        String name = "";
        try {
            name = ServerThread.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataGoods.query(name);
    }

    public static int update() {
        String name = "";
        String tag = "";
        int change = 0;
        try {
            name = ServerThread.cin.readUTF();
            tag = ServerThread.cin.readUTF();
            change = ServerThread.cin.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataGoods.update(name, tag, change);
    }

    public static int consumption() {
        String ID = "";
        double change = 0;
        try {
            ID = ServerThread.cin.readUTF();
            change = ServerThread.cin.readDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataGoods.consumption(ID,change);
    }

    public static Vector<Goods> getAll() {
        return DataGoods.getAll();
    }


}
