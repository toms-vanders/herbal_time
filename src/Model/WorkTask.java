package Model;

import java.sql.Date;

/**
 * Used to represent work tasks performed by seasonal workers. Workers are to register
 * their shifts as work tasks performed (hence the hoursWorked field)
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
public class WorkTask {
    private int wTaskID;
    private double hoursWorked;
    private double quantity;
    private Date dateStart;
    private Date dateEnd;
    private String status;
    private WorkType workType;
//    private String workerCpr;

    /**
     * Constructor of WorkTask
     */
    public WorkTask() {

    }

    /**
     * Constructor of WorkTask.
     *
     * @param wTaskID ID number of work task
     * @param hoursWorked report hours worked on the task
     * @param quantity measurement of collected produce
     * @param dateStart start date of executing work task
     * @param dateEnd end date of executing work task
     * @param status status of work task
     * @param workType type of work
     */
    public WorkTask(int wTaskID, double hoursWorked, double quantity, Date dateStart, Date dateEnd,
                    String status, WorkType workType) {
        this.wTaskID = wTaskID;
        this.hoursWorked = hoursWorked;
        this.quantity = quantity;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.status = status;
        this.workType = workType;
//        this.workerCpr = workerCpr;
//        this.wTypeID = wTypeID;
    }

    public int getWorkTaskID() { return wTaskID; }
    public void setWorkTaskID(int wTaskID) { this.wTaskID = wTaskID; }

    public double getHoursWorked() { return hoursWorked; }
    public void setHoursWorked(double hoursWorked) { this.hoursWorked = hoursWorked; }

    public double getQuantity() { return quantity; }
    public void setQuantity(double quantity) { this.quantity = quantity; }

    public Date getDateStart() { return dateStart; }
    public void setDateStart(Date dateStart) { this.dateStart = dateStart; }

    public Date getDateEnd() { return dateEnd; }
    public void setDateEnd(Date dateEnd) { this.dateEnd = dateEnd; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    //    public int getWorkTypeID() { return workType.getWorkTypeID(); }
    // public void setWorkTypeID(int wTypeID) { this.wTypeID = wTypeID; }

//    public String getCpr() { return workerCpr; }
//    public void setCpr(String cpr) { this.workerCpr = workerCpr; }

    public WorkType getWorkType() { return workType; }
    public void setWorkType(WorkType workType) { this.workType = workType; }

    @Override
    public String toString() {
        // Note: wTypeID and workerCpr are no longer properties of WorkTask
        return "WorkTask{" +
                "wTaskID=" + wTaskID +
                ", hoursWorked=" + hoursWorked +
                ", quantity=" + quantity +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", status='" + status + '\'' +
//                ", wTypeID=" + workType.getWorkTypeID() +
//                ", cpr='" + workerCpr + '\'' +
                '}';
    }
}
