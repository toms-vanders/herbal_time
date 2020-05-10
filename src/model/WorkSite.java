package model;

public class WorkSite {

    private Integer wSiteID;
    private String description;
    private String streetName;
    private String streetNum;
    private Integer zip;
    private String country;
    private String countryCode;
    private String typeOfJob;
    private double pricePerWorker;
    private Integer clientCvr;

    public WorkSite(Integer wSiteID, String description, String streetName, String streetNum, Integer zip,
                    String country, String countryCode, String typeOfJob, double pricePerWorker, Integer clientCvr) {
        this.wSiteID = wSiteID;
        this.description = description;
        this.streetName = streetName;
        this.streetNum = streetNum;
        this.zip = zip;
        this.country = country;
        this.countryCode = countryCode;
        this.typeOfJob = typeOfJob;
        this.pricePerWorker = pricePerWorker;
        this.clientCvr = clientCvr;
    }

    public Integer getWorkSiteID() {
        return wSiteID;
    }
    public void setWorkSiteID(Integer wSiteID) {
        this.wSiteID = wSiteID;
    }

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

    public Integer getZip() {
        return zip;
    }
    public void setZip(Integer zip) {
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

    public Integer getClientCvr() {
        return clientCvr;
    }
    public void setClientCvr(Integer clientCvr) {
        this.clientCvr = clientCvr;
    }

    public String getCountryCode() { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

    @Override
    public String toString() {
        return "WorkSite{" +
                "wSiteID=" + wSiteID +
                ", description='" + description + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNum='" + streetNum + '\'' +
                ", zip=" + zip +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", typeOfJob='" + typeOfJob + '\'' +
                ", pricePerWorker=" + pricePerWorker +
                ", clientCvr=" + clientCvr +
                '}';
    }
}
