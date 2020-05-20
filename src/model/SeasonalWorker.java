package Model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonalWorker extends Person{

    private String passportNum;
    private String swift;
    private String iban;
    private String ssn;
    private boolean workedBefore;
//    private String leadBy;
    private SeasonalWorker leadBy;
//    private int wSiteID;
    private ArrayList<WorkTask> workTasks;

    public SeasonalWorker(String cpr) {
        super(cpr, null, null, null, null, null, null, null,
                null, null, null, null);
    }

    public SeasonalWorker(String cpr, String fname, String lname, Date dob, char sex, String email,
                          String phoneNum, String streetName, String streetNum, String zip, String countryCode,
                          String country, String passportNum, String swift, String iban, String ssn,
                          boolean workedBefore) {
        super(cpr, fname, lname, dob, sex, email, phoneNum, streetName, streetNum, zip, countryCode, country);
        this.passportNum = passportNum;
        this.swift = swift;
        this.iban = iban;
        this.ssn = ssn;
        this.workedBefore = workedBefore;
//        this.leadBy = leadBy;
        workTasks = new ArrayList<>();
//        this.wSiteID = wSiteID;
    }

    /**
     * essentially an empty constructor, only makes the Person aspects of SeasonalWorker
     */
    public SeasonalWorker(String cpr, String fname, String lname, Date dob, char sex, String email,
                          String phoneNum, String streetName, String streetNum, String zip, String countryCode,
                          String country) {
        super(cpr, fname, lname, dob, sex, email, phoneNum, streetName, streetNum, zip, countryCode, country);
        workTasks = new ArrayList<>();
        leadBy = null;
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

//    public String getLeadBy() { return leadBy; }
//    public void setLeadBy(String leadBy) { this.leadBy = leadBy; }

//    public int getWorkSiteID() { return wSiteID; }
//    public void setWorkSiteID(int wSiteID) { this.wSiteID = wSiteID; }

    public ArrayList<WorkTask> getWorkTasks() { return workTasks; }
    public void setWorkTasks(ArrayList<WorkTask> workTasks) { this.workTasks = workTasks; }

    public SeasonalWorker getLeadBy() { return leadBy; }
    public void setLeadBy(SeasonalWorker leadBy) { this.leadBy = leadBy; }

    @Override
    public String toString() {
        // Note: wSiteID is no longer a property of SeasonalWorker
        return "{Person: (" +
                "cpr='" + getCpr() + '\'' +
                ", fname='" + getFname() + '\'' +
                ", lname='" + getLname() + '\'' +
                ", dob=" + getDob() +
                ", sex=" + getSex() +
                ", email='" + getEmail() + '\'' +
                ", phoneNum='" + getPhoneNum() + '\'' +
                ", streetName='" + getStreetName() + '\'' +
                ", streetNum='" + getStreetNum() + '\'' +
                ", zip=" + getZip() +
                ", countryCode='" + getCountryCode() + '\'' +
                ", country='" + getCountry() + '\'' +
                "), " +
                "SeasonalWorker: (" +
                "passportNum='" + passportNum + '\'' +
                ", swift='" + swift + '\'' +
                ", iban='" + iban + '\'' +
                ", ssn='" + ssn + '\'' +
                ", workedBefore=" + workedBefore +
                ", leadBy='" + getLeadBy().getCpr() + '\'' +
//                ", wSiteID='" + wSiteID + '\'' +
                ")}";
    }
}
