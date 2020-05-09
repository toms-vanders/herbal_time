package model;

import java.time.LocalDate;

public class SeasonalWorker extends Person{

    private String passportNum;
    private String swift;
    private String iban;
    private String ssn;
    private boolean workedBefore;
    private String leadBy;
    private String wSiteID;

    public SeasonalWorker(String cpr, String fname, String lname, LocalDate dob, char sex, String email,
                          String phoneNum, String streetNum, String streetName, Integer zip, String country,
                          String passportNum, String swift, String iban, String ssn, boolean workedBefore,
                          String leadBy, String wSiteID) {
        super(cpr, fname, lname, dob, sex, email, phoneNum, streetNum, streetName, zip, country);
        this.passportNum = passportNum;
        this.swift = swift;
        this.iban = iban;
        this.ssn = ssn;
        this.workedBefore = workedBefore;
        this.leadBy = leadBy;
        this.wSiteID = wSiteID;
    }

    public String getPassportNum() { return passportNum; }
    public void setPassportNum(String passportNum) { this.passportNum = passportNum; }

    public String getSwift() { return swift; }
    public void setSwift(String swift) { this.swift = swift; }

    public String getIban() { return iban; }
    public void setIban(String iban) { this.iban = iban; }

    public String getSsn() { return ssn; }
    public void setSsn(String ssn) { this.ssn = ssn; }

    public boolean isWorkedBefore() { return workedBefore; }
    public void setWorkedBefore(boolean workedBefore) { this.workedBefore = workedBefore; }

    public String getLeadBy() { return leadBy; }
    public void setLeadBy(String leadBy) { this.leadBy = leadBy; }

    public String getWorkSiteID() { return wSiteID; }
    public void setWorkSiteID(String wSiteID) { this.wSiteID = wSiteID; }

    @Override
    public String toString() {
        return "SeasonalWorker{" +
                "passportNum='" + passportNum + '\'' +
                ", swift='" + swift + '\'' +
                ", iban='" + iban + '\'' +
                ", ssn='" + ssn + '\'' +
                ", workedBefore=" + workedBefore +
                ", leadBy='" + leadBy + '\'' +
                ", wSiteID='" + wSiteID + '\'' +
                '}';
    }
}
