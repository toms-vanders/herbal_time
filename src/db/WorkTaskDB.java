package DB;

import Controller.DataAccessException;
import Model.WorkTask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkTaskDB implements WorkTaskDBIF {
    /**
     * Pre-made queries for the program
     */
    private static final String findAll = "SELECT * FROM WorkTask";
    private static final String findByID = "SELECT * FROM WorkTask WHERE workTaskID = ?";
    private static final String insertWorkTask = "INSERT INTO WorkTask VALUES(?,?,?,?,?,?,?,?)";
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
    private PreparedStatement PSfindAll;
    private PreparedStatement PSfindByID;
    private PreparedStatement PSinsertWorkTask;
    private PreparedStatement PSupdateWorkTask;

    public WorkTaskDB() throws DataAccessException{
        Connection con = DBConnection.getInstance().getConnection();
        try {
            PSfindAll = con.prepareStatement(findAll);
            PSfindByID = con.prepareStatement(findByID);
            PSinsertWorkTask = con.prepareStatement(insertWorkTask);
            PSupdateWorkTask = con.prepareStatement(updateWorkTask);
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
    public WorkTask findByID(Integer wTaskID, boolean fullAssociation) throws DataAccessException {
        WorkTask res = null;
        try {
            PSfindByID.setString(1,wTaskID.toString());
        }
        catch (SQLException e){
            throw new DataAccessException("resultset error", e);
        }
        return null;
    }

    @Override
    public Integer insertWorkTask(WorkTask workTask) throws DataAccessException {
        Integer genKey;
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
    public Integer updateWorkTask(Integer wTaskID, WorkTask workTask) throws DataAccessException {
        return null;
    }

    private List<WorkTask> buildObjects(ResultSet rs, boolean fullAssociation) {
        return null;
    }

    private WorkTask buildObject(ResultSet rs, boolean fullAssociation) {
        return null;
    }
}
