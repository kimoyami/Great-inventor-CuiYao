package srv;

import java.io.Serializable;
import java.util.Date;

public class StudentInfo implements Serializable {
    /**
    * academy学院
    * dormitory宿舍
    * */
    private static final long serialVersionUID= 2963524741253039910L;
    private String name;
    private String ID;
    private int age;
    private String gender;
    private Date birthday;
    private String birthplace;
    private String academy;
    private String dormitory;
    private String EcardNumber;
    private String password;

    StudentInfo(String name,String ID,int age,String gender,Date birthday,
                String birthplace,String academy,String dormitory,String EcardNumber,String password){
        this.password=password;
        this.EcardNumber=EcardNumber;
        this.name=name;
        this.ID=ID;
        this.age=age;
        this.gender=gender;
        this.birthday=birthday;
        this.birthplace=birthplace;
        this.EcardNumber=EcardNumber;

    }


    public String getName() {
        return this.name;
    }
    public String getID() {
        return this.ID;
    }
    public int getAge() {
        return this.age;
    }
    public String getGender() {
        return this.gender;
    }
    public Date getBirthday() {
        return birthday;
    }
    public String getAcademy() {
        return this.academy;
    }
    public String getDormitory() {
        return this.dormitory;
    }
    public String getEcardNumber(){return this.EcardNumber;}
    public String getPassword(){return this.password;}

    public void setName(String name) {
        this.name = name;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }
    public void setAcademy(String academy) {
        this.academy = academy;
    }
    public void setDormitory(String dormitory) {
        this.dormitory = dormitory;
    }
    public void setEcardNumber(String EcardNumber){this.EcardNumber=EcardNumber;}
    public void setPassword(String password){this.password=password;}
}

