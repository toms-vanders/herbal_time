package model;

import java.time.LocalDate;

public abstract class Person {

    private String cpr;
    private String fname;
    private String lname;
    private LocalDate dob;
    private char sex;
    private String email;
    private String phoneNum;
    private String streetNum;
    private String streetName;
    private Integer zip;
    private String country;

    public Person(String cpr, String fname, String lname, LocalDate dob, char sex, String email, String phoneNum,
                  String streetNum, String streetName, Integer zip, String country) {
        this.cpr = cpr;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.sex = sex;
        this.email = email;
        this.phoneNum = phoneNum;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.zip = zip;
        this.country = country;
    }

    public String getCpr() { return cpr; }
    public void setCpr(String cpr) { this.cpr = cpr; }

    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }
    public void setLname(String lname) { this.lname = lname; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public char getSex() { return sex; }
    public void setSex(char sex) { this.sex = sex; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNum() { return phoneNum; }
    public void setPhoneNum(String phoneNum) { this.phoneNum = phoneNum; }

    public String getStreetNum() { return streetNum; }
    public void setStreetNum(String streetNum) { this.streetNum = streetNum; }

    public String getStreetName() { return streetName; }
    public void setStreetName(String streetName) { this.streetName = streetName; }

    public Integer getZip() { return zip; }
    public void setZip(Integer zip) { this.zip = zip; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

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
                ", streetNum='" + streetNum + '\'' +
                ", streetName='" + streetName + '\'' +
                ", zip=" + zip +
                ", country='" + country + '\'' +
                '}';
    }
}
