/*
Arthor: kimoyami
 */
package dao;

import srv.goods.Goods;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public static int consumption(String ID,double change){
        try{
            String sql="select * from bank where idx='"+ID+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            rs.next();
            double tmp=rs.getDouble("eCardBalance");
            tmp-=change;
            if(tmp<0){return -2;}
            sql="update bank set eCardBalance="+tmp+" where idx='"+ID+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();

            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String d = f.format(new Date());

            sql="insert into bankrecord(ecardname,change,record,otime) values('"+ID+"',"+change+",'超市购物',#"+d+"#)";
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
        int a=consumption("213170001",100);
        System.out.println(a);
        DataBase.stop();
    }
}
