/*
 * Copyright: kimoyami
 */

package srv.server;

import dao.DataGoods;
import srv.goods.Goods;

import java.util.Vector;

public class GoodInfo {
    public static ServerThread now;
    public static void run(int op) {
        try {
            if (op == 1) {
                now.cout.writeInt(insert());
                now.cout.flush();
            }
            if (op == 2) {
                now.cout.writeInt(delete());
                now.cout.flush();
            }
            if (op == 3) {
                Vector<Goods> res = query();
                now.cout.writeInt(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
            if (op == 4) {
                Vector<Goods> res = getAll();
                now.cout.writeInt(res.size());
                System.out.println(res.size());
                for (int i = 0; i < res.size(); i++) {
                    now.cout.writeObject(res.elementAt(i));
                }
                now.cout.flush();
            }
            if (op == 5) {
                now.cout.writeInt(update());
                now.cout.flush();
            }
            if (op == 6) {
                now.cout.writeInt(consumption());
                now.cout.flush();
            }

        } catch (Exception e) {
            return;
        }
    }

    public static synchronized int insert() {
        String idx;
        int cnt;
        String goodname;
        double price;
        String url;
        String tag;
        String info;
        try {
            idx=now.cin.readUTF();
            cnt=now.cin.readInt();
            goodname=now.cin.readUTF();
            price=now.cin.readDouble();
            url=now.cin.readUTF();
            tag=now.cin.readUTF();
            info=now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -3;
        }
        return DataGoods.insert(idx,cnt,goodname,price,url,tag,info);
    }

    public static synchronized int delete() {
        String goodsname;
        String tag;
        try {
            goodsname = now.cin.readUTF();
            tag = now.cin.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return DataGoods.delete(goodsname, tag);
    }


    public static Vector<Goods> query() {
        String name = "";
        try {
            name = now.cin.readUTF();
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
            name = now.cin.readUTF();
            tag = now.cin.readUTF();
            change = now.cin.readInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataGoods.update(name, tag, change);
    }

    public static int consumption() {
        String ID = "";
        double change = 0;
        try {
            ID = now.cin.readUTF();
            change = now.cin.readDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DataGoods.consumption(ID,change);
    }

    public static Vector<Goods> getAll() {
        return DataGoods.getAll();
    }



}
