/*
Arthor: kimoyami
 */
package dao;

import srv.goods.Goods;

import java.sql.ResultSet;
import java.util.Vector;

public class DataGoods {
    public static int exist(String goodsName, String tag){
        DataBase.start();
        try {
            String sql = "select * from goods where goodsname = '"+goodsName+"' and tag ='"+tag+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) {
                DataBase.stop();
                return 0;
            }
            DataBase.stop();
            return 1;
        }catch (Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(Goods goods) {
        DataBase.start();
        try {
            if(exist(goods.getName(), goods.getTag()) == 1){
                DataBase.stop();
                return 0;
            }
            String sql = "insert into goods(idx, cnt, goodsname, price, url, tag) " +
                    "values ('" + goods.getID() + "', "+ goods.getNumber() +", " +
                    "'"+goods.getName()+"', "+goods.getPrice()+", '"+goods.getPicturePath()+"', '"+goods.getTag()+"')";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }catch (Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(String goodsname,String tag){
        DataBase.start();
        try {
            String sql = "delete from goods where goodsname = '" +goodsname+ "' and tag = '"+tag+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }catch (Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Goods> query(String name){
        DataBase.start();
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
        DataBase.stop();
        return res;
    }

    public static Vector<Goods>getAll(){
        DataBase.start();
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
        DataBase.stop();
    return res;
    }

    public static int update(String name,String tag,int change){
        DataBase.start();
        try{
            String sql="select * from goods where goodsname='"+name+"' and tag='"+tag+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            rs.next();
            int cnt=rs.getInt("cnt");
            cnt-=change;
            if(cnt<0){
                DataBase.stop();
                return -2;
            }//剩余不足
            sql="update goods set cnt="+cnt+" where goodsname='"+name+"' and tag='"+tag+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }catch(Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }

    }

    public static int consumption(String ID,double change){
        DataBase.start();
        try{
            String sql="select * from bank where idx='"+ID+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            rs.next();
            double tmp=rs.getDouble("eCardBalance");
            tmp-=change;
            if(tmp<0){
                DataBase.stop();
                return -2;
            }
            sql="update bank set eCardBalance="+tmp+" where idx='"+ID+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }catch(Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static void main(String args[]) {
        DataBase.start();

        DataBase.stop();
    }
}
