package srv.person;

import java.io.Serializable;

public class Unsolve implements Serializable {
    private static final long serialVersionUeCardNumber =963524741253039910L;
    private String username;
    private String password;
    private String ecardname;
    private String sex;
    private String status;

    public Unsolve(String u,String p,String e,String s,String st) {
        this.username=u;
        this.password=p;
        this.ecardname=e;
        this.sex=s;
        this.status=st;
    }


    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getEcardname() {
        return this.ecardname;
    }

    public String getSex() {
        return this.sex;
    }

    public String getStatus() {
        return this.status;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEcardname(String ecardname) {
        this.ecardname = ecardname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
