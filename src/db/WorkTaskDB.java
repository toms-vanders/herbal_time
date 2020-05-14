package DB;

import Controller.DataAccessException;
import Model.WorkTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkTaskDB implements WorkTaskDBIF {
    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM WorkTask";
    private static final String findByID = "SELECT * FROM WorkTask WHERE workTaskID = ?";
    private static final String insertWorkTask = "INSERT INTO WorkTask VALUES(?,?,?,?,?,?,?,?)";
    private static final String removeWorkTask = "DELETE FROM WorkTask WHERE workTaskID = ?";
    private static final String updateWorkTask = "UPDATE WorkTask SET "
            + "hoursWorked = ? "
            + "quantity = ? "
            + "dateStart = ? "
            + "dateEnd = ? "
            + "taskStatus = ? "
            + "workTypeID = ? "
            + "workerCPR = ? "
            + "WHERE workTaskID = (SELECT workTaskID FROM WorkTask WHERE workTaskID = ?)";

    /**
     * Prepared statement declaration for the above queries
     */
    private final PreparedStatement PSfindAll;
    private final PreparedStatement PSfindByID;
    private final PreparedStatement PSinsertWorkTask;
    private final PreparedStatement PSupdateWorkTask;
    private final PreparedStatement PSremoveWorkTask;

    public WorkTaskDB() throws DataAccessException{
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
            PSfindByID = con.prepareStatement(findByID);
            PSinsertWorkTask = con.prepareStatement(insertWorkTask);
            PSupdateWorkTask = con.prepareStatement(updateWorkTask);
            PSremoveWorkTask = con.prepareStatement(removeWorkTask);
        } catch (SQLException e) {
            throw new DataAccessException("WorkTaskDB error.", e);
        }
    }

    @Override
    public List<WorkTask> findAll(boolean fullAssociation) throws DataAccessException {
        ResultSet rs;
        try {
            rs = this.PSfindAll.executeQuery();
        } catch (SQLException e) {
            throw new DataAccessException("resultset error", e);
        }
        List<WorkTask> res = buildObjects(rs,fullAssociation);
        return res;
    }

    @Override
    public WorkTask findByID(Integer id, boolean fullAssociation) throws DataAccessException {
        WorkTask res = null;
        try {
            PSfindByID.setString(1,id.toString());
        }
        catch (SQLException e){
            throw new DataAccessException("resultset error", e);
        }
        return null;
    }

    @Override
    public Integer insertWorkTask(WorkTask workTask) throws DataAccessException {
        Integer genKey = null;
        try {
            PSinsertWorkTask.setString(1,String.valueOf(workTask.getWorkTaskID()));
            PSinsertWorkTask.setString(2,String.valueOf(workTask.getHoursWorked()));
            PSinsertWorkTask.setString(3,String.valueOf(workTask.getQuantity()));
            PSinsertWorkTask.setString(4,workTask.getDateStart().toString());
            PSinsertWorkTask.setString(5,workTask.getDateEnd().toString());
            PSinsertWorkTask.setString(6,workTask.getStatus());
            PSinsertWorkTask.setString(7,String.valueOf(workTask.getWorkTypeID()));
            PSinsertWorkTask.setString(8,String.valueOf(workTask.getCpr()));
        }
        catch (SQLException e){
            throw new DataAccessException("There was a problem with the workTask being inserted into DB.", e);
        }
        try
        {
            PSinsertWorkTask.executeQuery();
            genKey = PSinsertWorkTask.getGeneratedKeys().getInt(1);
        }
        catch (SQLException e){
            throw new DataAccessException("There was a problem with inserting workTask into DB.",e);
        }
        return genKey;
    }

    @Override
    public Integer updateWorkTask(WorkTask workTask) throws DataAccessException {
        Integer genKey = null;
        try {
            PSupdateWorkTask.setString(1,String.valueOf(workTask.getHoursWorked()));
            PSupdateWorkTask.setString(2,String.valueOf(workTask.getQuantity()));
            PSupdateWorkTask.setString(3,workTask.getDateStart().toString());
            PSupdateWorkTask.setString(4,workTask.getDateEnd().toString());
            PSupdateWorkTask.setString(5,workTask.getStatus());
            PSupdateWorkTask.setString(6,String.valueOf(workTask.getWorkTypeID()));
            PSupdateWorkTask.setString(7,String.valueOf(workTask.getCpr()));
            PSupdateWorkTask.setString(8,String.valueOf(workTask.getWorkTaskID()));
        }
        catch (SQLException e) {
            throw new DataAccessException("There was a problem with the workTask being updated in DB.",e);
        }
        try
        {
            PSupdateWorkTask.executeQuery();
            genKey = PSupdateWorkTask.getGeneratedKeys().getInt(1);
        }
        catch (SQLException e){
            throw new DataAccessException("There was a problem with updating workTask in DB.",e);
        }

        return genKey;
    }

    @Override
    public void removeWorkTask(Integer id) throws DataAccessException {
        try {
            PSremoveWorkTask.setString(1, id.toString());
        }
        catch (SQLException e) {
            throw new DataAccessException("There was a problem with the id of the workTask being deleted from DB.",e);
        }
        try
        {
            PSremoveWorkTask.executeQuery();
        }
        catch (SQLException e){
            throw new DataAccessException("There was a problem with deleting workTask from DB.",e);
        }
    }

    private List<WorkTask> buildObjects(ResultSet rs, boolean fullAssociation) throws DataAccessException {
        List<WorkTask> res = new ArrayList<>();
        try {
            while(rs.next()) {
                WorkTask currentWorkTask = buildObject(rs,fullAssociation);
                res.add(currentWorkTask);
            }
        } catch (SQLException e) {
            throw new DataAccessException("buildObjects: problem with resultset", e);

        }
        return res;
    }

    private WorkTask buildObject(ResultSet rs, boolean fullAssociation) throws DataAccessException {
        WorkTask workTask = null;
        try {
            workTask = new WorkTask(rs.getInt("workTaskID"),rs.getDouble("hoursWorked"),
                    rs.getDouble("quantity"), rs.getDate("dateStart").toLocalDate(),
                    rs.getDate("dateEnd").toLocalDate(),rs.getString("taskStatus"),
                    rs.getInt("workTypeID"),rs.getString("workerCPR"));

        }
        catch (SQLException e) {
            throw new DataAccessException("buildObject: Error.", e);
        }
        return workTask;
    }
}