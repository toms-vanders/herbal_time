package model;

public class WorkType {

    private Integer wTypeID;
    private String descOfJob;
    private String typeOfProduce;
    private String salaryType;
    private double pay;
    private Integer wSiteID;

    public WorkType(Integer wTypeID, String descOfJob, String typeOfProduce, String salaryType,
                    double pay, Integer wSiteID) {
        this.wTypeID = wTypeID;
        this.descOfJob = descOfJob;
        this.typeOfProduce = typeOfProduce;
        this.salaryType = salaryType;
        this.pay = pay;
        this.wSiteID = wSiteID;
    }

    public Integer getWorkTypeID() {
        return wTypeID;
    }
    public void setWorkTypeID(Integer wTypeID) {
        this.wTypeID = wTypeID;
    }

    public String getDescOfJob() {
        return descOfJob;
    }
    public void setDescOfJob(String descOfJob) {
        this.descOfJob = descOfJob;
    }

    public String getTypeOfProduce() {
        return typeOfProduce;
    }
    public void setTypeOfProduce(String typeOfProduce) {
        this.typeOfProduce = typeOfProduce;
    }

    public String getSalaryType() {
        return salaryType;
    }
    public void setSalaryType(String salaryType) {
        this.salaryType = salaryType;
    }

    public double getPay() {
        return pay;
    }
    public void setPay(double pay) {
        this.pay = pay;
    }

    public Integer getWorkSiteID() {
        return wSiteID;
    }
    public void setWorkSiteID(Integer wSiteID) {
        this.wSiteID = wSiteID;
    }

    @Override
    public String toString() {
        return "WorkType{" +
                "wTypeID=" + wTypeID +
                ", descOfJob='" + descOfJob + '\'' +
                ", typeOfProduce='" + typeOfProduce + '\'' +
                ", salaryType='" + salaryType + '\'' +
                ", pay=" + pay +
                ", wSiteID=" + wSiteID +
                '}';
    }
}
