
package srv.clinic;

import java.io.Serializable;
import java.util.Date;

/**
 * 医院信息
 * */
public class Clinic implements Serializable {

    private static final long serialVersionUID = 2949713471686533905L;
    private String doctorName;
    private String clinicName;
    private String clinicPlace;
    private String clinicTime;

    public Clinic( String clinicTime,String clinicName,String doctorName, String clinicPlace) {
        this.clinicName = clinicName;
        this.clinicPlace = clinicPlace;
        this.clinicTime = clinicTime;
        this.doctorName = doctorName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getClinicPlace() {
        return clinicPlace;
    }

    public String getClinicTime() {
        return clinicTime;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public void setClinicPlace(String clinicPlace) {
        this.clinicPlace = clinicPlace;
    }

    public void setClinicTime(String clinicTime) {
        this.clinicTime = clinicTime;
    }
}


