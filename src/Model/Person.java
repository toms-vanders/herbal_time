package Model;

import java.sql.Date;

/**
 * Interface used for Employee and SeasonalWorker classes. Holds mainly personal information
 * about the person in question.
 */
public abstract class Person {

    private String cpr;
    private String fname;
    private String lname;
    private Date dob;
    private Character sex;
    private String email;
    private String phoneNum;
    private String streetName;
    private String streetNum;
    private String zip;
    private String countryCode;
    private String country;

    public Person(String cpr, String fname, String lname, Date dob, Character sex, String email, String phoneNum,
                  String streetName, String streetNum, String zip, String countryCode, String country) {
        this.cpr = cpr;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.sex = sex;
        this.email = email;
        this.phoneNum = phoneNum;
        this.streetName = streetName;
        this.streetNum = streetNum;
        this.zip = zip;
        this.countryCode = countryCode;
        this.country = country;
    }

    public String getCpr() { return cpr; }
    public void setCpr(String cpr) { this.cpr = cpr; }

    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }
    public void setLname(String lname) { this.lname = lname; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public Character getSex() { return sex; }
    public void setSex(Character sex) { this.sex = sex; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getStreetNum() { return streetNum; }
    public void setStreetNum(String streetNum) { this.streetNum = streetNum; }

    public String getStreetName() { return streetName; }
    public void setStreetName(String streetName) { this.streetName = streetName; }

    public String getZip() { return zip; }
    public void setZip(String zip) { this.zip = zip; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    @Override
    public String toString() {
        return "Person{" +
                "cpr='" + cpr + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", dob=" + dob +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNum='" + streetNum + '\'' +
                ", zip=" + zip +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
