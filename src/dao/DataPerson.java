/*
Arthor: kimoyami
 */
package dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import srv.person.Person;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DataPerson {
    private static SimpleDateFormat trans = new SimpleDateFormat("yyyy-MM-dd");

    public static int exist(String eCardNumber){
        DataBase.start();
        try {
            String sql = "select * from personinfo where ecardnumber = '"+eCardNumber+"'";
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

    public static int insert(Person person){
        DataBase.start();
        try {
            if(exist(person.geteCardNumber()) == 1){
                DataBase.stop();
                return 0;
            }
            String date = trans.format(person.getBirthday());
            String sql = "insert into personinfo(username, ecardnumber, age, sex, birthday, birthplace, academy, dormitory, state, sign) values('"+person.getName()+"', '"+person.geteCardNumber()+"', "+person.getAge()+", '"+person.getGender()+"', #"+date+"#, '"+person.getBirthplace()+"', '"+person.getAcademy()+"', '"+person.getDormitory()+"', '"+person.getState()+"', '"+person.getSign()+"')";
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

    public static int delete(String eCardNumber){
        DataBase.start();
        try {
            if(exist(eCardNumber) == 0) {
                DataBase.stop();
                return 0;
            }
            String sql = "delete from personinfo where ecardnumber = '"+eCardNumber+"'";
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

    public static Person query(String eCardNumber){
        DataBase.start();
        Person person = new Person();
        try{
            String sql = "select * from personinfo where ecardnumber = '"+eCardNumber+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(rs.next()){
                person = new Person(rs.getString(2), rs.getString(3), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        DataBase.stop();
        return person;
    }

    public static int update(Person person){
        DataBase.start();
        if(exist(person.geteCardNumber()) == 0){
            DataBase.stop();
            return 0;
        }
        try{
            String sql = "update personinfo set username = '"+person.getName()+"', age = "+person.getAge()+", sex = '"+person.getGender()+"', birthday = #"+trans.format(person.getBirthday())+"#, birthplace = '"+person.getBirthplace()+"', academy = '"+person.getAcademy()+"', dormitory = '"+person.getDormitory()+"', state = '"+person.getState()+"', sign = '"+person.getSign()+"' where ecardnumber = '"+person.geteCardNumber()+"'";
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

    public static int isNew(String eCardNumber){
        DataBase.start();
        try {
            String sql = "select academy from personinfo where ecardnumber = '"+eCardNumber+"'";
            ResultSet rs = DataBase.s.executeQuery(sql);
            if(!rs.next()){
                DataBase.stop();
                return -2;
            }
            if(rs.getString(1) == null) {
                DataBase.stop();
                return 1;
            }
            DataBase.stop();
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            DataBase.stop();
            return -1;
        }
    }

    public static void main(String args[]){
        DataBase.start();
        DataBase.stop();
    }
}
