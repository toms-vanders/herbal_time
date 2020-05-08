package model;

public class WorkSite {

    private Integer wSiteID;
    private String description;
    private String streetNum;
    private String streetName;
    private Integer zip;
    private String country;
    private String typeOfJob;
    private double pricePerWorker;
    private Integer clientID;

    public WorkSite(Integer wSiteID, String description, String streetNum, String streetName, Integer zip,
                    String country, String typeOfJob, double pricePerWorker, Integer clientID) {
        this.wSiteID = wSiteID;
        this.description = description;
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.zip = zip;
        this.country = country;
        this.typeOfJob = typeOfJob;
        this.pricePerWorker = pricePerWorker;
        this.clientID = clientID;
    }

    public Integer getwSiteID() {
        return wSiteID;
    }

    public void setwSiteID(Integer wSiteID) {
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

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    @Override
    public String toString() {
        return "WorkSite{" +
                "wSiteID=" + wSiteID +
                ", description='" + description + '\'' +
                ", streetNum='" + streetNum + '\'' +
                ", streetName='" + streetName + '\'' +
                ", zip=" + zip +
                ", country='" + country + '\'' +
                ", typeOfJob='" + typeOfJob + '\'' +
                ", pricePerWorker=" + pricePerWorker +
                ", clientID=" + clientID +
                '}';
    }
}
