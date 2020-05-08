package model;

import java.time.LocalDate;

public class WorkTask {
    private Integer wTaskID;
    private double hoursWorked;
    private double quantity;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String status;
    private Integer wTypeID;
    private String passportNum;

    public WorkTask(Integer wTaskID, double hoursWorked, double quantity, LocalDate dateStart, LocalDate dateEnd,
                    String status, Integer wTypeID, String passportNum) {
        this.wTaskID = wTaskID;
        this.hoursWorked = hoursWorked;
        this.quantity = quantity;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.status = status;
        this.wTypeID = wTypeID;
        this.passportNum = passportNum;
    }

    public Integer getwTaskID() {
        return wTaskID;
    }

    public void setwTaskID(Integer wTaskID) {
        this.wTaskID = wTaskID;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getwTypeID() {
        return wTypeID;
    }

    public void setwTypeID(Integer wTypeID) {
        this.wTypeID = wTypeID;
    }

    public String getPassportNum() {
        return passportNum;
    }

    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    @Override
    public String toString() {
        return "WorkTask{" +
                "wTaskID=" + wTaskID +
                ", hoursWorked=" + hoursWorked +
                ", quantity=" + quantity +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", status='" + status + '\'' +
                ", wTypeID=" + wTypeID +
                ", passportNum='" + passportNum + '\'' +
                '}';
    }
}
