package model;

import java.time.LocalDate;

public class Client {

    private String cvr;
    private String name;
    private String email;
    private String phoneNum;
    private String streetName;
    private String streetNum;
    private Integer zip;
    private String countryCode;
    private String country;
    private LocalDate dateStart;
    private LocalDate dateEnd;

// Empty constructor, used in ClientDB
    public Client() {}

    public Client(String cvr, String name, String email, String phoneNum, String streetName, String streetNum,
                  Integer zip, String countryCode, String country, LocalDate dateStart, LocalDate dateEnd) {
        this.cvr = cvr;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.streetName = streetName;
        this.streetNum = streetNum;
        this.zip = zip;
        this.countryCode = countryCode;
        this.country = country;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
    }

    public String getCvr() { return cvr; }
    public void setCvr(String cvr) { this.cvr = cvr; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

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

    public LocalDate getDateStart() { return dateStart; }
    public void setDateStart(LocalDate dateStart) { this.dateStart = dateStart; }

    public LocalDate getDateEnd() { return dateEnd; }
    public void setDateEnd(LocalDate dateEnd) { this.dateEnd = dateEnd; }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    @Override
    public String toString() {
        return "Client{" +
                "cvr='" + cvr + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNum='" + streetNum + '\'' +
                ", zip=" + zip +
                ", countryCode='" + countryCode + '\'' +
                ", country='" + country + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                '}';
    }
}
