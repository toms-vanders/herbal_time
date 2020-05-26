package Model;

import java.util.ArrayList;

/**
 * Used to represent individual work sites of clients where seasonal workers go work
 */
public class WorkSite {

    private Integer wSiteID;
    private String name;
    private String description;
    private String streetName;
    private String streetNum;
    private String zip;
    private String country;
    private String countryCode;
    private String typeOfJob;
    private double pricePerWorker;
    private ArrayList<WorkType> workTypes;
    private ArrayList<SeasonalWorker> seasonalWorkers;

    public WorkSite() {
        this.workTypes = new ArrayList<>();
        this.seasonalWorkers = new ArrayList<>();
    }

    public WorkSite(String name, String description, String streetName, String streetNum, String zip,
                    String country, String countryCode, String typeOfJob, double pricePerWorker) {
        this.name = name;
        this.description = description;
        this.streetName = streetName;
        this.streetNum = streetNum;
        this.zip = zip;
        this.country = country;
        this.countryCode = countryCode;
        this.typeOfJob = typeOfJob;
        this.pricePerWorker = pricePerWorker;
        this.workTypes = new ArrayList<>();
        this.seasonalWorkers = new ArrayList<>();
    }

    public WorkSite(Integer wSiteID, String name, String description, String streetName, String streetNum, String zip,
                    String country, String countryCode, String typeOfJob, double pricePerWorker) {
        this.wSiteID = wSiteID;
        this.name = name;
        this.description = description;
        this.streetName = streetName;
        this.streetNum = streetNum;
        this.zip = zip;
        this.country = country;
        this.countryCode = countryCode;
        this.typeOfJob = typeOfJob;
        this.pricePerWorker = pricePerWorker;
        this.workTypes = new ArrayList<>();
        this.seasonalWorkers = new ArrayList<>();
    }

    public Integer getWorkSiteID() {
        return wSiteID;
    }
    public void setWorkSiteID(Integer wSiteID) {
        this.wSiteID = wSiteID;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getStreetNum() {
        return streetNum;
    }
    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public String getTypeOfJob() {
        return typeOfJob;
    }
    public void setTypeOfJob(String typeOfJob) {
        this.typeOfJob = typeOfJob;
    }

    public double getPricePerWorker() {
        return pricePerWorker;
    }
    public void setPricePerWorker(double pricePerWorker) {
        this.pricePerWorker = pricePerWorker;
    }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    public ArrayList<WorkType> getWorkTypes() { return workTypes; }
    public void setWorkTypes(ArrayList<WorkType> workTypes) { this.workTypes = workTypes; }

    public ArrayList<SeasonalWorker> getSeasonalWorkers() { return seasonalWorkers; }
    public void setSeasonalWorkers(ArrayList<SeasonalWorker> seasonalWorkers) {
        this.seasonalWorkers = seasonalWorkers;
    }

    @Override
    public String toString() {
        // Note: clientCvr is no longer a property of WorkSite
        String s1 = "WorkSiteID: " + wSiteID + "\n";
        String s2 = "description: " + description + "\n";
        String s3 = "streetName: " + streetName + "\n";
        String s4 = "streetNum: " + streetNum + "\n";
        String s5 = "zip: " + zip + "\n";
        String s6 = "country: " + country + "\n";
        String s7 = "countryCode: " + countryCode + "\n";
        String s8 = "typeOfJob: " + typeOfJob + "\n";
        String s9 = "pricePerWorker: " + pricePerWorker + "\n";

        return s1+s2+s3+s4+s5+s6+s7+s8+s9;

//                "WorkSite{" +
//                "wSiteID=" + wSiteID +
//                ", description='" + description + '\'' +
//                ", streetName='" + streetName + '\'' +
//                ", streetNum='" + streetNum + '\'' +
//                ", zip=" + zip +
//                ", country='" + country + '\'' +
//                ", countryCode='" + countryCode + '\'' +
//                ", typeOfJob='" + typeOfJob + '\'' +
//                ", pricePerWorker=" + pricePerWorker +
//                ", clientCvr=" + clientCvr +
//                '}';
    }
}
