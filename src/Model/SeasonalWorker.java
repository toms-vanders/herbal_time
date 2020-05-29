package Model;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Used to represent the seasonal workers employed by CS Works to perform tasks for clients
 *
 * @author Daniel Zoltan Ban
 * @author Mikuláš Dobrodej
 * @author Adrian Mihai Dohot
 * @author Damian Hrabąszcz
 * @author Toms Vanders
 * @version 1.0 (29.05.2020)
 *
 * Date: 29.05.2020
 */
public class SeasonalWorker extends Person{

    private String passportNum;
    private String swift;
    private String iban;
    private String ssn;
    private boolean workedBefore;
    private SeasonalWorker leadBy;
    private ArrayList<WorkTask> workTasks;

    /**
     * Constructor of SeasonalWorker
     * assigns all values inherited from Person class to null except for CPR number
     * @param cpr CPR number of seasonal worker
     */
    public SeasonalWorker(String cpr) {
        super(cpr, null, null, null, null, null, null, null,
                null, null, null, null);
    }

    /**
     * Constructor of SeasonalWorker
     * @param cpr CPR number of seasonal worker
     * @param fname first name of seasonal worker
     * @param lname last name of seasonal worker
     * @param dob date of birth of seasonal worker
     * @param sex sex of seasonal worker
     * @param email email of seasonal worker
     * @param phoneNum phone number of seasonal worker
     * @param streetName street name of seasonal worker
     * @param streetNum street number of seasonal worker
     * @param zip zip code of seasonal worker
     * @param countryCode ISO country code of seasonal worker
     * @param country country of seasonl worker
     * @param passportNum passport number of seasonal worker
     * @param swift swift bank code of seasonal worker
     * @param iban IBAN code of seasonal worker
     * @param ssn social security number of seasonal worker
     * @param workedBefore specifies whether seasonal worker used to work in the previous fiscal year
     */
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
//        this.leadBy = null;
        workTasks = new ArrayList<>();
//        this.wSiteID = wSiteID;
    }

    /**
     * Only makes the Interface (Person) part of SeasonalWorker
     *
     * @param cpr CPR number of seasonal worker
     * @param fname first name of seasonal worker
     * @param lname last name of seasonal worker
     * @param dob date of birth of seasonal worker
     * @param sex sex of seasonal worker
     * @param email email of seasonal worker
     * @param phoneNum phone number of seasonal worker
     * @param streetName street name of seasonal worker
     * @param streetNum street number of seasonal worker
     * @param zip zip code of seasonal worker
     * @param countryCode ISO country code of seasonal worker
     * @param country country of seasonal worker
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

    // This is a leftover from when leadBy used to be a foreign key reference, not used anymore.
//    public String getLeadBy() { return leadBy; }
//    public void setLeadBy(String leadBy) { this.leadBy = leadBy; }

    public ArrayList<WorkTask> getWorkTasks() { return workTasks; }
    public void setWorkTasks(ArrayList<WorkTask> workTasks) { this.workTasks = workTasks; }

    public SeasonalWorker getLeadBy() { return leadBy; }
    public void setLeadBy(SeasonalWorker leadBy) { this.leadBy = leadBy; }

    @Override
    public String toString() {
        String leadByCPR = null;
        if (getLeadBy()!=null) {
            leadByCPR = getLeadBy().getCpr();
        }
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
                ", leadBy='" + leadByCPR + '\'' +
                ")}";
    }
}
