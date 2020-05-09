package model;

import java.time.LocalDate;

public class WorkTask {
    private int wTaskID;
    private double hoursWorked;
    private double quantity;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    private String status;
    private int wTypeID;
    private String workerCpr;

    public WorkTask(int wTaskID, double hoursWorked, double quantity, LocalDate dateStart, LocalDate dateEnd,
                    String status, int wTypeID, String workerCpr) {
        this.wTaskID = wTaskID;
        this.hoursWorked = hoursWorked;
        this.quantity = quantity;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.status = status;
        this.wTypeID = wTypeID;
        this.workerCpr = workerCpr;
    }

    public int getWorkTaskID() { return wTaskID; }
    public void setWorkTaskID(int wTaskID) { this.wTaskID = wTaskID; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public LocalDate getDateStart() { return dateStart; }
    public void setDateStart(LocalDate dateStart) { this.dateStart = dateStart; }

    public LocalDate getDateEnd() { return dateEnd; }
    public void setDateEnd(LocalDate dateEnd) { this.dateEnd = dateEnd; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getWorkTypeID() { return wTypeID; }
    public void setWorkTypeID(int wTypeID) { this.wTypeID = wTypeID; }

    public String getCpr() { return workerCpr; }
    public void setCpr(String cpr) { this.workerCpr = workerCpr; }

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
                ", cpr='" + workerCpr + '\'' +
                '}';
    }
}
