/*
Arthor: kimoyami
 */
package dao;

import srv.goods.Goods;

import java.sql.ResultSet;
import java.util.Vector;

public class DataGoods {
    public static int exist(String goodsName, String tag){
        try {
            String sql = "select * from goods where goodsname = '"+goodsName+"' and tag ='"+tag+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) return 0;
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(Goods goods) {
        try {
            if(exist(goods.getName(), goods.getTag()) == 1) return 0;
            String sql = "insert into goods(idx, cnt, goodsname, price, url, tag) " +
                    "values ('" + goods.getID() + "', "+ goods.getNumber() +", " +
                    "'"+goods.getName()+"', "+goods.getPrice()+", '"+goods.getPicturePath()+"', '"+goods.getTag()+"')";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(String goodsname,String tag){
        try {

            String sql = "delete from goods where goodsname = '" +goodsname+ "' and tag = '"+tag+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Goods> query(String name){
        Vector<Goods> res = new Vector<>();
        try{
            String sql = "select * from goods where goodsname = '"+name+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            while(rs.next()){
                Goods tmp = new Goods(rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getDouble(5), rs.getString(6), rs.getString(7));
                res.addElement(tmp);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    public static Vector<Goods>getAll(){
        Vector<Goods>res=new Vector<>();
        try{
            String sql="select * from goods";
            ResultSet rs=DataBase.s.executeQuery(sql);
            while(rs.next()){
                Goods tmp = new Goods(rs.getString(2), rs.getInt(3), rs.getString(4),
                        rs.getDouble(5), rs.getString(6), rs.getString(7));
                res.addElement(tmp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    return res;
    }

    public static int update(String name,String tag,int change){
        try{
            String sql="select * from goods where goodsname='"+name+"' and tag='"+tag+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            rs.next();
            int cnt=rs.getInt("cnt");
            cnt-=change;
            if(cnt<0){return -2;}//剩余不足
            sql="update goods set cnt="+cnt+" where goodsname='"+name+"' and tag='"+tag+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            return 1;
        }catch(Exception e){
            e.printStackTrace();
            return -1;
        }

    }

    public static void main(String args[]) {
        DataBase.start();
        int c=update("薯片","中超",10);
        System.out.println(c);

        DataBase.stop();
    }
}
/*
        int a=insert(new Goods("0005",100,"薯片",5,"","中超"));
        int b=delete("可乐","天平");
        Vector<Goods>res =query("雪碧");
        for (int i = 0; i <res.size() ; i++) {
            System.out.println(res.elementAt(i).getTag());
        }

        res=getAll();
        for (int i = 0; i <res.size() ; i++) {
            System.out.println(res.elementAt(i).getName());
        }
*/