/*
 * Copyright: Mashiro
 * exist()是否存在医生
 *delete()删除医生
 *
 */

package dao;
import srv.clinic.*;
import dao.DataBase;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class DataClinic {
    public static int exist(Clinic doctor){
        DataBase.start();
        try{
            String sql = "select * from clinic where ClinicTime = '"+doctor.getClinicTime()+"'and ClinicName='"+ doctor.getClinicName()+"' ";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()) {
                DataBase.stop();
                return 0;
            }
            else {
                DataBase.stop();
                return 1;
            }
        }catch (Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static int insert(Clinic doctor){
        DataBase.start();
        try {
            int res = exist(doctor);
            if (res != 0) {
                DataBase.stop();
                return 0;
            }
            String sql="insert into clinic(ClinicTime,ClinicName,DoctorName,ClinicPlace) values('"+doctor.getClinicTime()+"'," +
                    "'"+doctor.getClinicName()+"','"+doctor.getDoctorName()+"','"+doctor.getClinicPlace()+"')";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }
        catch(Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static int delete(Clinic doctor){
        DataBase.start();
        try{
            int res=exist(doctor);
            if(res!=1){
                DataBase.stop();
                return 0;
            }
            String sql="delete * from clinic " +
                    "where ClinicTime = '"+doctor.getClinicTime()+"'" +
                    " and ClinicName='"+ doctor.getClinicName()+"' and DoctorName='"+doctor.getDoctorName()+"'" +
                    " and ClinicPlace='"+doctor.getClinicPlace()+"'";
            DataBase.s.executeUpdate(sql);
            DataBase.c.commit();
            DataBase.stop();
            return 1;
        }
        catch(Exception e){
            DataBase.stop();
            e.printStackTrace();
            return -1;
        }
    }

    public static Vector<Clinic> query(String doctorName){
        DataBase.start();
        Vector<Clinic> res=new Vector<>();
        try{
            String sql="select *from clinic where DoctorName='"+doctorName+"'";
            ResultSet rs=DataBase.s.executeQuery(sql);
            while(rs.next()){
                Clinic tmp=new Clinic(rs.getString("ClinicTime"),rs.getString("ClinicName"),
                rs.getString("DoctorName"),rs.getString("ClinicPlace"));
                res.add(tmp);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        DataBase.stop();
        return res;
    }

    public static void main(String []args) {


    }

}
