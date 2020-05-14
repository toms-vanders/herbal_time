package Model;

public class WorkType {

    private int wTypeID;
    private String descOfJob;
    private String typeOfProduce;
    private String salaryType;
    private double pay;
    private int wSiteID;

    public WorkType() {}

    public WorkType(String descOfJob, String typeOfProduce, String salaryType,
                    double pay, int wSiteID) {
        this.descOfJob = descOfJob;
        this.typeOfProduce = typeOfProduce;
        this.salaryType = salaryType;
        this.pay = pay;
        this.wSiteID = wSiteID;
    }

    public WorkType(int wTypeID, String descOfJob, String typeOfProduce, String salaryType,
                    double pay, int wSiteID) {
        this.wTypeID = wTypeID;
        this.descOfJob = descOfJob;
        this.typeOfProduce = typeOfProduce;
        this.salaryType = salaryType;
        this.pay = pay;
        this.wSiteID = wSiteID;
    }

    public int getWorkTypeID() {
        return wTypeID;
    }
    public void setWorkTypeID(int wTypeID) {
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

    public int getWorkSiteID() {
        return wSiteID;
    }
    public void setWorkSiteID(int wSiteID) {
        this.wSiteID = wSiteID;
    }

    @Override
    public String toString() {
        String s1 = "WorkTypeID: " + wTypeID + "\n";
        String s2 = "descOfJob: " + descOfJob + "\n";
        String s3 = "typeOfProduce: " + typeOfProduce + "\n";
        String s4 = "salaryType: " + salaryType + "\n";
        String s5 = "pay: " + pay + "\n";
        String s6 = "wSiteID: " + wSiteID + "\n";
        return s1+s2+s3+s4+s5+s6;
//        return "WorkType" +
//                "wTypeID=" + wTypeID +
//                ", descOfJob='" + descOfJob + '\'' +
//                ", typeOfProduce='" + typeOfProduce + '\'' +
//                ", salaryType='" + salaryType + '\'' +
//                ", pay=" + pay +
//                ", wSiteID=" + wSiteID +
//                '}';

    }
}
